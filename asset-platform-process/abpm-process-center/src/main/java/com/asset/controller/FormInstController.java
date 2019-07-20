package com.asset.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.converter.FormConverter;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.form.FormItem;
import com.asset.form.FormJsonEntity;
import com.asset.javabean.FormInstPlus;
import com.asset.javabean.RespBean;
import com.asset.dto.*;
import com.asset.service.FormInstService;
import com.asset.service.ProcInstService;
import com.asset.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 表单实例的创建、修改，表单项值在流程引擎中的使用
 * @author YBY
 * @time 190610_1040
 * @
 */
@RestController
@Api(value = "表单实例创建")
public class FormInstController {

    Logger logger = LoggerFactory.getLogger(FormInstController.class);

    @Autowired
    FormInstService formInstService;
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    AsProcInstMapper procInstMapper;
    @Autowired
    AsProcModelMapper procModelMapper;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    FormModelMapper formModelMapper;
    @Autowired
    ProcInstService procInstService;


    /**
     * 应用下选中一个表单之后，右侧再点击 新增 按钮，此时表单内容需要向服务器请求（即第一个节点所需要展示的表单内容）
     * @return
     */
    @RequestMapping(value = "/form/inst/showNew",method = RequestMethod.GET)
    public RespBean showNew(@RequestBody FormModelShowRec rec){
        //获取对应流程模型ID以及流程模型中第一个节点ID
        String procModelId = formInstService.getProcModelIdByForm(rec.getForm_model_id());
        String firstAct = "task1";
        //获取对应表单json
        String modeljson = formInstService.getFormById(rec.getForm_model_id());
        FormJsonEntity entity = FormConverter.jsonToEntity(modeljson);

        List<FormItem> items = entity.getList();
        for(int j=0;j<items.size();j++)
        {
            //获取当前Authority
            Integer curAuthority = formInstService.getCurAuthority(procModelId,firstAct,items.get(j).getKey());
            //添加权限信息
            formInstService.handleAuthority(items,j,curAuthority);
        }


        String formJsonNew = FormConverter.entityToJson(entity);

        HashMap<String,String> map = new HashMap<>();
        map.put("form_json",formJsonNew);

        JSONObject object = JSON.parseObject(JSON.toJSONString(map));

        return RespBean.ok("",object);
    }


    /**
     * 用户登录应用界面，填写表单，发起一个新的表单流程
     * 这边根据表单模型需要：
     * 1、创建流程实例
     * 2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
     *
     * 注意：表单的填写内容对流程的执行没有影响！只需要展示即可！对流程执行产生影响的只有“同意”“拒绝”这两个操作！
     * @param rec
     * @return
     */
    @ApiOperation(value = "在用户的应用界面，选择该应用下一个表单，点新增之后，填写完表单，接着点击 提交 发送的请求" ,
            notes = "" , httpMethod = "POST")
    @RequestMapping(value = "/form/inst/commit", method = RequestMethod.POST)
    public RespBean createFormInst(@RequestBody FormInstRecCreate rec){
        //1、先获取与表单模型唯一绑定的流程模型ID
        String procModelID = formModelMapper.getProcModelID(rec.getForm_model_id());
        String defID = null;
        String deployID = null;
        //直接由流程模型后台创建流程实例(还没持久化)
        HashMap<String,Object> map = procInstService.createProcInstance(procModelID,defID,deployID);

        ProcessInstance procInst = (ProcessInstance) map.get("inst");
        defID = (String) map.get("defID");
        deployID = (String) map.get("deployID");

        String[] taskIDs = procInstService.getTaskIDs(procInst.getProcessInstanceId());

        //2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        for(int i=0;i<taskIDs.length;i++)
        {
            formInstService.createFormInst(rec.getForm_inst_json(),
                    procInst.getProcessInstanceId(),
                    rec.getEditor(),
                    rec.getForm_model_id(),
                    rec.getForm_inst_value(),
                    taskIDs[i]);
        }

        //3、持久化流程实例
        AsProcInst asProcInst = new AsProcInst(
                procInst.getProcessInstanceId(),
                procModelID,
                defID,
                deployID,
                rec.getEditor());
        procInstMapper.insert(asProcInst);

        //至此，相当于把第一个任务节点要填的表单内容存进数据库了，而且绑定的流程实例也存进了数据库，当前流程应当流转到下个任务节点上了
        procInstService.completeCurTask(taskIDs[0]);

        //在数据库创建下一个还没执行的任务节点的条目
        procInstService.saveUnCompleteTask(
                procInst.getProcessInstanceId(),
                rec.getForm_model_id(),
                rec.getForm_inst_value());

        return RespBean.ok("");
    }

    /**
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     * 这里的全部节点信息还包含了历史的处理信息，这里先不考虑
     * @param userID
     * @param taskType
     * @return
     */
    @RequestMapping(value = "/form/inst/show",method = RequestMethod.GET)
    public RespBean getFormInsts(@RequestParam(value = "user_id") String userID,
                              @RequestParam(value = "task_type") Integer taskType)
    {
        //map是TaskId和ActType的Map
        HashMap<String,Integer> map =new HashMap<String, Integer>();

        //1、先获取流转到该用户对应的procInstId\taskID\actId的集合
        List<AsTask> tasks = procInstService.getCurTasks(userID);
        if (tasks.size()==0)
            return RespBean.ok("表单实例为空");

        //2、对上面集合进行遍历，从数据库中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        for(int i =0;i<tasks.size();i++)
        {
            AsTask curAsTask = tasks.get(i);
            //2.1 由流程实例ID获取流程模型ID
            String procModelId = procInstService.getProcModelIdByProc(curAsTask.getProcInstId());
            //这里说明我们在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例，说明数据库中存在脏的流程实例数据
            if (procModelId.isEmpty()){
                logger.error("在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例!没有如下ProcInstID:{}", curAsTask.getProcInstId());
                return RespBean.error("有运行中流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            }

            //2.2 根据流程模型ID、节点ID 获取对应的ActType，然后决定要不要把该taskInfos中的这一项给去掉
            Integer actType = procInstService.getActType(procModelId, curAsTask.getActId());
            if (actType == null)
            {
                logger.error("流程中间层生成的流程模型出错！procModelId为:{},没有如下ActID:{}",procModelId, curAsTask.getActId());
                return RespBean.error("流程中间层生成的流程模型出错！");
            }
            //判断是不是符合当前页面要找的节点类型
            if(!formInstService.match(actType,taskType))
            {
                tasks.remove(i);
                i--;
            }
            else {
                map.put(curAsTask.getTaskId(),actType);
            }
        }

        //3、把所有流程实例的taskID都取出来，然后获取对应的表单实例列表
        String[] procTaskIds = new String[tasks.size()];
        int k=0;
        for(int i= 0;i<tasks.size();i++)
        {
            procTaskIds[k++] = tasks.get(i).getTaskId();
        }
        if (procTaskIds.length==0)
            return RespBean.ok("",null);
        //这里得到的procTaskIds是当前该用户执行到的任务节点TaskID
        ArrayList<FormInst> formInsts = (ArrayList<FormInst>) formInstMapper.getFormInsts(procTaskIds);
        ArrayList<FormInstPlus> asFormInstPluses = new ArrayList<>();


//        //2.3、如果是经办节点，还需要对表单操作权限进行设置
//        if(asFormInstPlus.getNodeType() == Constants.AS_NODE_APPLY)
        //这里是对所有表单实例的表单模板进行一个操作权限的修正
        for(int i = 0; i< formInsts.size(); i++)
        {
            FormInst inst = formInsts.get(i);
            //先转换
            String formJson = inst.getFormInstJson();
            FormJsonEntity entity = FormConverter.jsonToEntity(formJson);
            List<FormItem> items = entity.getList();
            for(int j=0;j<items.size();j++)
            {
                String procModelId = procInstService.getProcModelIdByProc(inst.getProcInstId());
                String actId = procInstService.getActId(inst.getTaskId());
                //获取当前Authority
                Integer curAuthority = formInstService.getCurAuthority(procModelId,actId,items.get(j).getKey());
                if(curAuthority==null)
                    return RespBean.error("权限信息为空，请重新添加！");
                //添加权限信息
                formInstService.handleAuthority(items,j,curAuthority);
            }

            String formJsonNew = FormConverter.entityToJson(entity);
            inst.setFormInstJson(formJsonNew);
        }

        //如果当前要显示的是待办页面，需要对 经办节点和审批节点进行区分
        if(taskType == Constants.TASK_TO_DO)
        {
            for(int i = 0; i< formInsts.size(); i++)
            {
                FormInstPlus asFormInstPlus = new FormInstPlus(formInsts.get(i));
                asFormInstPlus.setNodeType(map.get(asFormInstPlus.getTaskId()));
                //啥都搞完了，添加进最后名单
                asFormInstPluses.add(asFormInstPlus);
            }
            return RespBean.ok("",asFormInstPluses);
        }

        return RespBean.ok("", formInsts);
    }


    /**
     * 用户登录系统，对审批节点进行处理，点击 同意 或 拒绝
     * 审批节点对当前节点进行操作
     * 1、点“同意” 即代表当前任务节点会向后流转，同时当前填写的表单实例需要加上这个处理意见，给下一个节点过目
     * 2、点“拒绝” 那么就是流程需要回滚到申请节点那个位置（这边的处理逻辑变成 当前节点直接不可用）
     *
     */
    @RequestMapping(value = "/form/inst/approve",method = RequestMethod.POST)
    public RespBean approveFormInst(@RequestBody FormInstRecApprove rec)
    {
        //找到当前传入的表单实例对应的流程实例的ID，注意和TaskID进行区分！！一次执行中，TaskId会一直变化，但是流程实例ID是不会变的
        String procInstID = formInstMapper.getProcInstID(rec.getForm_inst_id());
        //当前审批节点同意申请，先把当前新加了 同意 这个信息的 新表单 放入数据库，并完成当前任务节点
        if(rec.getApprove_status() == Constants.APPROVE_AGREE)
        {
            formInstService.updateFormInst(rec);
            procInstService.completeCurTask(rec.getTask_id());
            procInstService.saveUnCompleteTask(
                    procInstID,
                    rec.getForm_model_id(),
                    rec.getForm_inst_value());
        }
        //当前审批节点拒绝申请，那么就是直接回滚到上个申请节点（如果前一个是审批节点，前前一个是申请节点，那么就是回滚到前前节点的位置）
        else if(rec.getApprove_status() == Constants.APPROVE_DISAGREE)
        {
            //获取流程实例中的上一个申请节点
            String rollbackActID = formInstService.getLastApplyNode(procInstID);
//            String rollbackActID = "UserTask1";
            //在调用Flowable的方法进行回滚之前，还需要对as_form_inst表进行修改，把申请节点和审批节点中间的这些节点的信息都置为不可用状态
//            procInstMapper.deleteNode(procInstID);
            //调用Flowable的方法进行回滚
            formInstService.rollback(procInstID,rollbackActID);
        }
        return RespBean.ok("");
    }


    /**
     * 用户登录系统，对经办节点进行处理，填写表单，然后提交
     */
    @RequestMapping(value = "/form/inst/apply",method = RequestMethod.POST)
    public RespBean handleNode(@RequestBody FormInstRecHandle rec)
    {
        //当前填写表单数据 对数据库进行更新
        formInstService.updateFormInst(rec);

        //完成当前经办节点
        procInstService.completeCurTask(rec.getTask_id());
        procInstService.saveUnCompleteTask(
                rec.getProc_inst_id(),
                rec.getForm_model_id(),
                rec.getForm_inst_value());

        return RespBean.ok("");
    }


    /**
     * 待阅节点，点击 已阅
     */
    @RequestMapping(value = "/form/inst/read",method = RequestMethod.POST)
    public RespBean handleReadleNode(@RequestBody FormInstRecReadle rec)
    {
        //保存当前抄送节点的已阅信息
        formInstService.updateFormInst(rec);

        //完成当前抄送任务
        //抄送任务结束之后，默认后面是没有任务节点了！！所以这里不需要再保存未完成任务节点
        procInstService.completeCurTask(rec.getTask_id());

        return RespBean.ok("");
    }

    /**
     * 在执行上面的流程测试的时候需要保证现有的流程实例都已经跑完了，否则会造成错误
     */
    @RequestMapping(value = "/completeAll",method = RequestMethod.GET)
    public RespBean completeAll()
    {
        procInstService.completeAll();
        return RespBean.ok("");

    }

    /**
     * 返回当前用户有多少的待办任务，待阅任务
     * @param userID
     * @return
     */
    @RequestMapping(value = "/form/inst/count")
    public RespBean count(@RequestParam(value = "user_id")String userID){
        TaskCount toDoCount = null;
        TaskCount toReadCount = null;
        try {
            toDoCount = formInstService.getCount(userID,Constants.TASK_TO_DO);
            toReadCount = formInstService.getCount(userID,Constants.TASK_TOBE_READ);
        }catch (Exception e){
            return RespBean.error(e.getMessage());
        }
        List<TaskCount> taskCounts = new ArrayList<>();
        taskCounts.add(toDoCount);
        taskCounts.add(toReadCount);

        return RespBean.ok("",taskCounts);
    }



}
