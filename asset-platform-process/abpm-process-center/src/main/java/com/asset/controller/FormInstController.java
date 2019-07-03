package com.asset.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.javabean.ActType;
import com.asset.javabean.AsFormInstPlus;
import com.asset.javabean.FormJson;
import com.asset.javabean.RespBean;
import com.asset.rec.FormInstRecApprove;
import com.asset.rec.FormInstRecCreate;
import com.asset.rec.FormInstRecHandle;
import com.asset.rec.FormInstRecReadle;
import com.asset.service.FormInstService;
import com.asset.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.concurrent.Task;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
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
    AsFormInstMapper asFormInstMapper;
    @Autowired
    AsFormModelMapper asFormModelMapper;

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
        String procModelID = asFormModelMapper.getProcModelID(rec.getForm_model_id());
        String defID = null;
        String deployID = null;
        //直接由流程模型后台创建流程实例(还没持久化)
        ProcessInstance procInst = formInstService.createProcInstance(procModelID,defID,deployID);
        String[] taskIDs = formInstService.getTaskIDs(procInst.getProcessInstanceId());

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
        formInstService.completeCurTask(taskIDs[0]);
        formInstService.saveUnCompleteTask(rec.getForm_inst_json(),procInst.getProcessInstanceId(),rec.getForm_model_id(),rec.getForm_inst_value());

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
        //map2是TaskId和ActType的Map
        HashMap<String,Integer> map2 =new HashMap<String, Integer>();

        //1、先获取流转到该用户对应的procInstId\taskID\actId的集合
        List<TaskInst> taskInfos = flowableMapper.getTaskInfos(userID);
        //2、对上面集合进行遍历，与List<ActType>进行比对，确定节点类型，与输入的taskType比对，是不是要显示的节点
        for(int i =0;i<taskInfos.size();i++)
        {
            TaskInst curTaskInst = taskInfos.get(i);
            //2.1 获取流程实例更多的信息，这里我们主要要的是proc_model_id
            AsProcInst asProcInst = procInstMapper.selectByPrimaryKey(curTaskInst.getProcInstId());
            if (asProcInst==null)
                return RespBean.error("有流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            String procModelId = asProcInst.getProcModelId();
            //2.2 根据流程模型获取对应的ActType的list
            AsProcModel asProcModel = procModelMapper.selectByPrimaryKey(procModelId);
            String asJson = asProcModel.getAsJson();
            JSONArray array = JSONArray.parseArray(asJson);

            List<ActType> actTypes = JSONObject.parseArray(array.toJSONString(), ActType.class);

            //2.3 构造hashmap，方便查找
            HashMap<String,Integer> map = new HashMap<String, Integer>();
            int curActType;

            for(int j= 0;j<actTypes.size();j++)
            {
                map.put(actTypes.get(j).getAct_id(),
                        actTypes.get(j).getAct_type());
            }

            //这里注意一个问题，就是如果你在流程模型创建阶段输入的ACT_TYPE有错误，那么这里map就获取不到这个curActType，这里会抛出异常，
            //这里应该对这个异常进行处理！！
            //查看当前procInstIDs.get(i)的TASK_ID是什么类型的节点
            try {
                curActType = map.get(curTaskInst.getActId());
            }
            catch (NullPointerException e){
                logger.error("流程中间层生成的流程模型出错！没有如下ActID:{}",curTaskInst.getActId());
                return RespBean.error("流程中间层生成的流程模型出错！");
            }

            if(!formInstService.match(curActType,taskType))
            {
                taskInfos.remove(i);
                i--;
            }
            else {
                map2.put(curTaskInst.getTaskId(),curActType);
            }

        }
        //3、把所有流程实例的taskID都取出来，然后获取对应的表单实例列表
        String[] procTaskIds = new String[taskInfos.size()];
        int k=0;
        for(int i= 0;i<taskInfos.size();i++)
        {
            procTaskIds[k++] = taskInfos.get(i).getTaskId();
        }
        if (taskInfos.size()==0)
            return RespBean.ok("表单实例为空");

        //这里得到的procTaskIds是当前该用户执行到的任务节点TaskID
        ArrayList<AsFormInst> asFormInsts = (ArrayList<AsFormInst>) asFormInstMapper.getFormInsts(procTaskIds);
        ArrayList<AsFormInstPlus> asFormInstPluses = new ArrayList<>();
        //如果当前要显示的是待办页面，需要对 经办节点和审批节点进行区分
        if(taskType == Constants.TASK_TO_DO)
        {
            for(int i = 0;i<asFormInsts.size();i++)
            {
                AsFormInstPlus asFormInstPlus = new AsFormInstPlus(asFormInsts.get(i));
                asFormInstPlus.setNodeType(map2.get(asFormInstPlus.getTaskId()));
                asFormInstPluses.add(asFormInstPlus);
            }
            return RespBean.ok("",asFormInstPluses);
        }

        return RespBean.ok("",asFormInsts);
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
        String procInstID = asFormInstMapper.getProcInstID(rec.getForm_inst_id());
        //当前审批节点同意申请，先把当前新加了 同意 这个信息的 新表单 放入数据库，并完成当前任务节点
        if(rec.getApprove_status() == Constants.APPROVE_AGREE)
        {
            formInstService.updateFormInst(rec);
            formInstService.completeCurTask(rec.getTask_id());
            formInstService.saveUnCompleteTask(new FormJson(),
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
        formInstService.completeCurTask(rec.getTask_id());
        formInstService.saveUnCompleteTask(rec.getForm_inst_json(),rec.getProc_inst_id(),rec.getForm_model_id(),rec.getForm_inst_value());

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
        formInstService.completeCurTask(rec.getTask_id());

        return RespBean.ok("");
    }

    /**
     * 在执行上面的流程测试的时候需要保证现有的流程实例都已经跑完了，否则会造成错误
     */
    @RequestMapping(value = "/completeAll",method = RequestMethod.GET)
    public RespBean completeAll()
    {
        formInstService.completeAll();
        return RespBean.ok("");

    }

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
