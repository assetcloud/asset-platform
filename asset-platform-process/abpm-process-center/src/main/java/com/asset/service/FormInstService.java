package com.asset.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.converter.FormConverter;
import com.asset.dao.*;
import com.asset.entity.*;
import com.asset.exception.*;
import com.asset.filter.DuplicateFilter;
import com.asset.filter.SceneFilter;
import com.asset.filter.UserIdFilter;
import com.asset.javabean.*;
import com.asset.javabean.form.ColumnItem;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.FormSheet;
import com.asset.dto.*;
import com.asset.service.impl.ActRuVariableService;
import com.asset.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 以下对流程实例的执行均只考虑非并行分支的情况
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FormInstService implements IFormInstService {
    Logger logger = LoggerFactory.getLogger(FormInstService.class);

    @Autowired
    FlowableService flowableService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    ActRuVariableService actRuVariableService;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    AsFormInstMapper asFormInstMapper;

    /**
     * 业务入口
     * 选中某个表单模型项，显示该表单第一个节点的formSheet信息
     *
     * @param formModelId
     * @return
     */
    public JSONObject showNewFormSheet(String formModelId) throws InfoException {
        //获取对应流程模型ID以及流程模型中第一个节点ID
        String procModelId = formModelService.getProcModelID(formModelId);
        String firstNodeId = procNodeService.getFirstNodeId(procModelId);

        String formSheetStr = formModelService.getModelSheetStr(formModelId);
        FormSheet sheet = FormConverter.jsonToEntity(formSheetStr);

        List<FormItem> items = sheet.getList();
        for (int j = 0; j < items.size(); j++) {
            FormItem cur = items.get(j);
            //说明是普通的表单项元素，不是栅格元素
            if (!cur.getType().equals("grid")) {
                //获取当前Authority
                Integer curAuthority = authorityService.getCurAuthority(procModelId, firstNodeId, cur.getKey());
                //添加权限信息
                authorityService.handleItemAuthority(cur, curAuthority);
            } else {
                handleGridColumn(cur.getColumns(), procModelId, firstNodeId);
            }
        }


        formSheetStr = FormConverter.entityToJson(sheet);

        HashMap<String, String> map = new HashMap<>();
        map.put("form_json", formSheetStr);
        JSONObject object = JSON.parseObject(JSON.toJSONString(map));
        return object;
    }

    private void handleGridColumn(List<JSONObject> columns, String procModelId, String nodeId) {
        for (int m = 0; m < columns.size(); m++) {
            //这个object就是最外面一层grid中的元素列表
            JSONObject object = columns.get(m);
            //从这里由 object 变为 columnItem ，至此之后对columnItem的操作都不会再传导到object对象，需要手动进行传导
            ColumnItem columnItem = JSON.parseObject(JSON.toJSONString(object), ColumnItem.class);
            List<FormItem> list = columnItem.getList();
            for (int n = 0; list.size() != 0 && n < list.size(); n++) {
                FormItem cur = list.get(n);
                if (!cur.getType().equals("grid")) {
                    //获取当前Authority
                    Integer curAuthority = authorityService.getCurAuthority(procModelId, nodeId, cur.getKey());
                    //添加权限信息
                    authorityService.handleItemAuthority(cur, curAuthority);
                } else {
                    handleGridColumn(cur.getColumns(), procModelId, nodeId);
                }
            }

            for (Map.Entry<String, Object> set : object.entrySet()) {
                if (set.getKey().equals("list"))
                    set.setValue(list);
            }

        }


    }

    /**
     * 业务入口
     * 发起一个新的表单实例
     *
     * @param dto
     * @return
     */
    public String[] commitNewFormInst(FormInstRecCreate dto) throws DocumentException, FlowableException,DatabaseException,InterruptedException{
        String procInst = commitFormInst(dto);

        //生成一个或多个外链，当前待办的任务节点的执行人会受到这个URL，执行人点击这个URL就会跳转到相应的页面进行登录
        return generateUrls(procInst);
    }

    //发起实例
    public String commitFormInst(FormInstRecCreate dto) throws DocumentException, DatabaseException, InterruptedException {
        //1、先获取与表单模型唯一绑定的流程模型ID
        String procModelID = formModelService.getProcModelID(dto.getForm_model_id());
        //直接由流程模型后台创建流程实例
        HashMap<String, Object> map = procInstService.createProcInstance(procModelID);

        ProcessInstance procInst = (ProcessInstance) map.get("inst");
        String defID = (String) map.get("defID");
        String deployID = (String) map.get("deployID");

        //发起一个实例时，第一个节点可能是会签节点，所以会有多个taskId，需要执行完成一个，然后剩余的
        String[] taskIDs = ProcUtils.getTaskIDs(procInst.getProcessInstanceId());

        FormInstDO inst = createFormInst(dto.getForm_inst_sheet(),
                procInst.getProcessInstanceId(),
                dto.getEditor(),
                dto.getForm_model_id(),
                dto.getForm_inst_value(),
                taskIDs[0]);
        formInstMapper.insert(inst);

        String[] hasAddedNodeId = new String[taskIDs.length];
        int c = 0;
        hasAddedNodeId[c] = flowableService.getNodeId(taskIDs[0]);

        //2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        for (int i = 1; i < taskIDs.length; i++) {
            String curNodeId = flowableService.getNodeId(taskIDs[i]);
            if (!CommonUtils.isStringArrayContain(hasAddedNodeId, curNodeId)) {
                FormInstDO instUncomplete = createFormInst(dto.getForm_inst_sheet(),
                        procInst.getProcessInstanceId(),
                        null,
                        dto.getForm_model_id(),
                        dto.getForm_inst_value(),
                        taskIDs[i]);
                formInstMapper.insertSelective(instUncomplete);
                c++;
                hasAddedNodeId[c] = flowableService.getNodeId(taskIDs[i]);
            }
        }


        //3、数据库表新建流程实例条目,注意这里还需要存储一个叫form_inst_all_value的字段
        ProcInstDO asProcInstDO = new ProcInstDO.Builder()
                .procInstId(procInst.getProcessInstanceId())
                .procModelId(procModelID)
                .procDefId(defID)
                .procDeployId(deployID)
                .committer(dto.getEditor())
                .status(Constants.PROC_INST_ENABLE)
                .formInstAllValue(dto.getForm_inst_value()).build();

        procInstService.insertProcInst(asProcInstDO);

        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(taskIDs[0]);
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(procInst.getProcessInstanceId())
                .form_inst_value(dto.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);
        //至此，相当于把第一个任务节点要填的表单内容存进数据库了，而且绑定的流程实例也存进了数据库，当前流程应当流转到下个任务节点上了
        Thread.sleep(1000);
        ProcUtils.completeTask(taskIDs[0]);

        //在as_form_inst表中创建下一个还没被执行的任务节点的TASK条目
        procInstService.saveUnCompleteTask(
                procInst.getProcessInstanceId(),
                dto.getForm_model_id());

        return procInst.getProcessInstanceId();
    }
   /*
   * nfq:2019/10/11
   * 这个方法的目的就是把获取的真正的表单实例放到一个集合里
   * */
   @Override
   public  List<CommitFormInstDO> listComFormInst(String userID,
                                           Integer taskType,
                                           String curSelectSceneId,
                                           String sectionId)throws InfoException, ProcException, FormException{

       List<CommitFormInstDO> tasks = flowableService.listComFormInst1(userID,curSelectSceneId);
      // ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
       //formInstVOs.add(tasks);
       //return formInstVOs;
       return  tasks;


   }



    @Override
    public ArrayList<AdminTaskVO> listTaskInfo(String procInstId) {
       QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper();
       queryWrapper.lambda().eq(AsFormInstDO::getProcInstId,procInstId);

        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        ArrayList<AdminTaskVO> VOs = new ArrayList<>();
        for (int i = 0; i < formInstDOs.size(); i++) {
            AdminTaskVO vo = new AdminTaskVO();
            BeanUtils.copyProperties(formInstDOs.get(i), vo);

            //手动赋值
            vo.setFormInstId(formInstDOs.get(i).getId());
            if (formInstDOs.get(i).getExecuteTime() != null)
                vo.setExecutorTime(formInstDOs.get(i).getExecuteTime().getTime());
            vo.setInstName(formModelService.getFormName(formInstDOs.get(i).getFormModelId()));

            VOs.add(vo);
        }
        return VOs;
    }

    /**
     * 业务入口
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     *
     * @return
     * @throws InfoException
     * @throws ProcException
     * @throws FormException
     */
    public List<FormInstVO> listFormInst(FormInstListDTO dto) throws InfoException, ProcException, FormException {
        /*nfq1010:首先是获取流转用户的taskID  （是通过用户的ID来获取流转到该用户的task    这里是获取了多个 说明一个用户可能有多个task）？？？
        * */
        //1、先获取流转到该用户对应的FlowableTaskDO
        List<FlowableTaskDO> tasks = flowableService.listCurTasks(dto.getUserId());
        if (tasks.size() == 0)
            return new ArrayList<>();

        List<TaskBO> taskBOs = constructTaskBO(tasks);

        //2、对上面集合进行遍历，从as_proc_node中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        taskBOs = dressTaskByType(taskBOs, dto.getTaskType());
        if (taskBOs.size() == 0)
            return new ArrayList<>();

        //3、获取真正的表单实例表
        ArrayList<FormInstDO> formInstDOs = (ArrayList<FormInstDO>) getFormInsts(taskBOs);
        ArrayList<FormInstBO> formInstBOs = new ArrayList<>();

        //从数据库中找到的所有任务节点信息
        for (FormInstDO doo : formInstDOs) {
            String procModelId = procInstService.getProcModelId(doo.getProcInstId());
            String nodeId = flowableService.getNodeId(doo.getTaskId());
            ProcNodeDO nodeDO = procNodeService.getNodeDO(procModelId, nodeId);

            FormInstBO boo = new FormInstBO.Builder()
                    .curUserId(dto.getUserId())
                    .curTaskType(dto.getTaskType())
                    .procModelId(procModelId)
                    .nodeId(nodeId)
                    .ifJointSign(nodeDO.getIfJointSign())
                    .sceneId(formModelService.getSceneId(doo.getFormModelId())).build();
            BeanUtils.copyProperties(doo, boo);
            if (!StringUtils.isEmpty(nodeDO.getCandidateUser()))
                boo.setCandidateUser(nodeDO.getCandidateUser().split("\\|"));
            if (!StringUtils.isEmpty(nodeDO.getCandidateGroup()))
                boo.setCandidateGroup(nodeDO.getCandidateGroup().split("\\|"));

            boo.setCommitter(procInstService.getCommitter(doo.getTaskId()));

            formInstBOs.add(boo);
//            formInstBOs.add(new FormInstBO(doo,userID,taskType,nodeDO,
//                    formModelService.getSceneId(doo.getFormModelId())));
        }

        //4、对所有表单实例的表单Sheet进行表单项操作权限设置
        for (int i = 0; i < formInstBOs.size(); i++) {
            FormInstDO inst = formInstBOs.get(i);
            authorityService.handleFormSheetAuthority(inst);
        }

        UserIdFilter userIdFilter = new UserIdFilter();
        DuplicateFilter duplicateFilter = new DuplicateFilter();
        SceneFilter sceneFilter = new SceneFilter();
        //对不属于当前用户的表单任务进行筛选
        ArrayList<FormInstBO> filtrate1 = userIdFilter.filtrate(formInstBOs, dto.getSectionId(),dto.getCurSectionUsers());
        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
        ArrayList<FormInstBO> filtrate2 = duplicateFilter.filtrate(filtrate1);
        //过滤工作场景
        ArrayList<FormInstBO> filtrate3 = sceneFilter.filtrate(filtrate2, dto.getSceneId());


        ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
        for (int i = 0; i < filtrate3.size(); i++) {
            FormInstBO boo = filtrate3.get(i);
//            String committer = procInstService.getCommitter(filtrate3.get(i).getTaskId());
            String formInstValue = procInstService.getFormInstAllValue(boo.getProcInstId());
            Date commitTime = procInstService.getCommitTime(boo.getProcInstId());   // 这个指的是流程实例发起的时间
            FormInstVO voo = new FormInstVO.Builder()
                    .commitTime(commitTime.getTime())
                    .title(formModelService.getFormName(boo.getFormModelId()))   //title应该是对应的表单模型的名称
                    .build();
            BeanUtils.copyProperties(boo, voo);
            voo.setFormInstValue(formInstValue);

            //content那一项内容应该是 根据需求显示表单模板中的一些字段信息（这个字段信息的动态设置应该是放在as_form_model表中），如果没有对这个进行设置的话，就显示这个任务的类型
            //现在暂时写死
            String zichanyijiaoFormModelId = "660d7d6f-d83f-11e9-a9e8-0242ac120006";
            if (boo.getFormModelId().equals(zichanyijiaoFormModelId)) {
                JSONObject jsonObject = JSON.parseObject(formInstValue);
                String assetName = (String) jsonObject.get("input_1566366027958");
                String assetNum = (String) jsonObject.get("input_1566366061170");
//                String committerForContent = (String) jsonObject.get("input_1568450723142");
                String committerForContent = "金伟刚";
//                String committerSection = (String) jsonObject.get("input_1568450690311");
                String committerSection = (String) jsonObject.get("input_1568450690311");
                String taker = (String) jsonObject.get("input_1566958355545");
                String takerSection = (String) jsonObject.get("input_1566960206187");
                voo.setContent("资产名称：" + assetName + " 数量：" + assetNum + " 申请人：" + committerForContent + " 所在部门：" + committerSection + " 接收人：" + taker + " 所在部门：" + takerSection);
            } else {
                if (boo.getNodeType() == Constants.AS_NODE_APPLY)
                    voo.setContent("经办节点");
                else if (boo.getNodeType() == Constants.AS_NODE_APPROVE)
                    voo.setContent("审批节点");
                else if (boo.getNodeType() == Constants.AS_NODE_CC)
                    voo.setContent("抄送节点");
                else
                    voo.setContent("其他节点");
            }

//            FormInstVO voo = filtrate3.get(i).transToVO(procInstService.getCommitter(formInstDOs.get(i).getTaskId()));
            formInstVOs.add(voo);
        }

        //当前端没有传相应的num数据的时候，num值为空，当获取一个用户所有收集到的所有任务时，传进来的num参数是-1
        int num = dto.getNum();
        if(dto.getNum()!=-1)
            formInstVOs.subList(0,--num);

        return formInstVOs;
    }

    /**
     * 获取所有表单实例，不作筛选
     *
     * @return
     */
    public List<FormInstDO> listFormInst() {
        ArrayList<FormInstDO> formInstDOs = (ArrayList<FormInstDO>) formInstMapper.listFormInsts();
        return formInstDOs;
    }

    /**
     * 对当前用户以及当前拿到的任务节点进行匹配，看是不是当前用户可以执行的任务
     *
     * @param inst
     * @param userId
     * @return
     */
    private Boolean userMatch(FormInstDO inst, String userId) {
        String curNodeId = flowableService.getNodeId(inst.getTaskId());
        ProcNodeDO nodeDO = procNodeService.getNodeDO(procInstService.getProcModelId(inst.getProcInstId()),
                curNodeId);
        nodeDO.getCandidateUser();

        //默认匹配
        return true;
    }

    /**
     * 一个是节点类型，一个是页面显示的任务类型，看两者是不是匹配
     *
     * @param curActType 节点类型：经办节点、审批节点、抄送节点
     * @param taskType   任务类型：待办、待阅
     * @return
     */
    public boolean nodeTypeMatch(int curActType, Integer taskType) {
        if (taskType == Constants.TASK_TO_DO) {
            if (curActType == Constants.AS_NODE_APPLY
                    || curActType == Constants.AS_NODE_APPROVE)
                return true;
            else
                return false;
        }
        if (taskType == Constants.TASK_TOBE_READ && curActType == Constants.AS_NODE_CC)
            return true;

        return false;
    }


    private ArrayList<FormInstVO> transToVO(ArrayList<FormInstDO> formInstDOs) {
        ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
        for (int i = 0; i < formInstDOs.size(); i++) {
            formInstVOs.add(new FormInstVO(formInstDOs.get(i),
                    procInstService.getCommitter(formInstDOs.get(i).getTaskId())));
        }
        return formInstVOs;
    }

    /**
     * 业务入口
     * 对审批节点进行审批
     *
     * @param dto
     */
    public String[] approveNode(FormInstRecApprove dto) throws ProcException, FlowableException {
        //找到当前传入的表单实例对应的流程实例的ID，注意和TaskID进行区分！！一次执行中，TaskId会一直变化，但是流程实例ID是不会变的
//        String procInstID = formInstMapper.getProcInstID(dto.getForm_inst_id());
        String procInstID = dto.getProc_inst_id();

        //审批节点还可以对表单内容进行修改,这里的代码在updateFormInst方法里面写了
        //当前填写表单数据 对数据库进行更新
        updateFormInst(dto);

        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(dto.getTask_id());
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(dto.getProc_inst_id())
                .form_inst_value(dto.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);


        //当前审批节点同意申请，先把当前新加了 同意 这个信息的 新表单 放入数据库，并完成当前任务节点
        if (dto.getApprove_result() == Constants.APPROVE_AGREE) {
            //同意审批，as_form_inst表中输入approve_result,sheet和value、executor、execute_time值
            updateFormInst(dto);
            //这里审批节点之后就算存在分支结构，也不会是对这个审批意见进行处理，因为如果是对在之前任务节点上的表单项填写内容进行分支，那个时候就已经写入了runVariable数据，这里不需要再写
            //但是这里也可以写，如果以后需要对整个流程进行监控，就是比如在哪个节点干了什么事情这种
//            //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
//            String executionId = ProcUtils.getExecutionId(dto.getTask_id());
//            ActRuVariableBO boo = new ActRuVariableBO.Builder()
//                    .executionId(executionId)
//                    .procInstId(procInstID)
//                    .form_inst_value("approve").build();
//            actRuVariableService.saveRunVariable(boo);
            ProcUtils.completeTask(dto.getTask_id());
            procInstService.saveUnCompleteTask(
                    procInstID,
                    dto.getForm_model_id());
        }
        // 当前审批节点拒绝申请，应当是当前流程被强行结束
        // 那么就是直接回滚到上个申请节点（如果前一个是审批节点，前前一个是申请节点，那么就是回滚到前前节点的位置）
        // 回滚流程
        // 1、找到上一个经办节点
        // 2、回滚
        // 3、在form_inst表中把晚于这个节点执行时间且相同procInstId的项目的状态置为 已被回滚
        // 这里现在还有一个问题就是如果存在多个分支同时执行的话，另一个分支上的execution是没有回滚的！
        // 这里现在的思路是在回滚前把当前流程实例的其他任务节点状态统一设成 已被回滚，同时回滚之后还要把新生成的form_inst加入数据库
        else if (dto.getApprove_result() == Constants.APPROVE_DISAGREE) {
            ProcUtils.completeProcInstForRejected(dto.getTask_id());
            String[] strings = {""};
            return strings;
//            String lastApplyNode = getLastApplyNode(getProcInstId(dto.getTask_id()));
//            if(lastApplyNode.equals(""))
//                throw new ProcException("无法找到上一个经办节点，无法完成回滚，当前审批意见无法执行！");
//            procInstService.rollback(procInstID,lastApplyNode);
            // 上面这段注释代码是对的，这里暂时先把审批拒绝的逻辑改为当前流程被抛弃
            // 1、这里在流程回滚之后会生成新的任务节点，需要也存入数据库
            // 2、表单内容也要重置
            // 3、
            //在act_hi_actinst表中读取相同inst_id且END_time为空的条目，将其封装存入as_form_inst表中
            //在as_form_inst表中创建下一个还没被执行的任务节点的TASK条目
//            saveRollbackTask(procInstID, dto.getForm_model_id());
//
//            saveRollbackTask();
//            saveUnCompleteFormInst();
//            updateFormInstAfterRollback(dto.getTask_id(),lastApplyNode);
        }
        return generateUrls(procInstID);
    }

//    public void saveRollbackTask(String procInstId,String formModelId){
//        FormSheet originalFormSheet = formModelService.getModelSheet(formModelId);
//        //获取当前执行到的任务节点
//        String[] taskIDs = ProcUtils.getTaskIDs(procInstId);
//        //如果下面还有任务节点要处理，就在as_proc_inst中新建这个表单实例字段（每一个TaskId对应一个新的表单实例）
//        for (int i = 0; i < taskIDs.length; i++) {
//            boolean isNotContain = formInstMapper.getTaskId(taskIDs[i]) == null ? true : false;
//            //当前要存的taskID不能是已经有的的，否则重复保存了
//            if (isNotContain) {
//                String formInstAllValue = getFormInstAllValue(procInstId);
//                FormInstDO formInst = formInstService.createFormInst(originalFormSheet,
//                        procInstId,
//                        null,
//                        formModelId,
//                        formInstAllValue,
//                        taskIDs[i]);
//                String procModelId = formModelService.getProcModelID(formModelId);
//                String nodeId = flowableService.getNodeId(taskIDs[i]);
//                //在存进去之前，就必须要求这个节点的节点类型是确定的
//                formInst.setNodeType(procModelService.getNodeType(procModelId, nodeId));
//                formInstService.saveUnCompleteFormInst(formInst);
//            }
//        }
//    }

    public String getProcInstId(String taskId) {
        return formInstMapper.getProcInstId(taskId);
    }

    /**
     * 这块逻辑太复杂了，先暂时不搞了
     * 回滚结束后，在form_inst表中把晚于这个节点执行时间且相同procInstId的项目的状态置为 已被回滚
     * 当前的处理逻辑是：
     * 1、先判断要回滚到的上一个经办节点位置是不是有两个出度（即是否是分支节点）
     * 2、如果不是，那么就是从curTaskId开始在数据库中遍历相同procInstId的条目直到lastApplyNodeId，把这些条目都置为 已回滚
     * 如果是，先把as_form_inst表中的指定proc_inst_id的条目都取出来，构造<task_id,act_id>的哈希表（需两次读取数据库表，先拿task_id，再由task_id拿act_id），然后
     * 获取经办节点的所有出度，遍历所有act_id，直到endNode，出现了的就把这一项的task_id取出来去as_form_inst中找到这个条目置为 已回滚
     */
    private void updateFormInstAfterRollback(String curTaskId, String lastApplyNodeId) {
        String procInstId = getProcInstId(curTaskId);
        String defId = procInstService.getDefId(procInstId);
        if (ProcUtils.containParral(defId, lastApplyNodeId)) {

        }
        //不包含分支结构
        else {
//            Date lastlastApplyNodeExecuteTime = getExecuteTime(lastApplyNodeId);
        }

    }

    private void getExecuteTime(String lastApplyNodeId) {
//        return formInstMapper.getFormInst(lastApplyNodeId);
    }


    /**
     * 业务入口
     * 对经办节点进行处理
     *
     * @param rec
     */
    public String[] applyNode(FormInstRecHandle rec) throws FlowableException {
        //当前填写表单数据 对数据库进行更新
        updateFormInst(rec);
        //在flowable流程引擎完成任务之前，需要确保表单项的值写入了act_ru_variable表中，否则分支结构的流程不能正常运行,第一个节点填写的
        String executionId = ProcUtils.getExecutionId(rec.getTask_id());
        ActRuVariableBO boo = new ActRuVariableBO.Builder()
                .executionId(executionId)
                .rev(1)
                .procInstId(rec.getProc_inst_id())
                .form_inst_value(rec.getForm_inst_value()).build();
        actRuVariableService.saveRunVariable(boo);
        //完成当前经办节点
        ProcUtils.completeTask(rec.getTask_id());
        procInstService.saveUnCompleteTask(
                rec.getProc_inst_id(),
                rec.getForm_model_id());
        return generateUrls(rec.getProc_inst_id());
    }

    /**
     * 业务入口
     * 对抄送节点进行处理
     *
     * @param rec
     */
    public String[] pendingNode(FormInstRecReadle rec) throws FlowableException {
        //保存当前抄送节点的已阅信息
        updateFormInst(rec);
        //完成当前抄送任务,抄送任务之后也不需要考虑对当前结果的处理意见进行一个保存
        //抄送任务结束之后，默认后面是没有任务节点了！！所以这里不需要再保存未完成任务节点
        ProcUtils.completeTask(rec.getTask_id());
        return generateUrls(rec.getProc_inst_id());
    }

    /**
     * 入口方法
     * 统计各个类型的表单实例数目分别是多少
     *
     * @return
     */
    public List<TaskCount> getFormInstsCounts(FormInstCountDTO dto) throws Exception {
        TaskCount toDoCount;    //nfq:这个是统计待办的
        TaskCount toReadCount;   //nfq:这个是统计待阅的

        FormInstListDTO dto2 = new FormInstListDTO();
        BeanUtils.copyProperties(dto2,dto);
        dto2.setNum(-1);
        dto2.setTaskType(Constants.TASK_TO_DO);

        try {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, listFormInst(dto2).size());
        } catch (SizeNullException e) {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, 0);
        }

        dto2.setTaskType(Constants.TASK_TOBE_READ);

        try {
            toReadCount = new TaskCount(Constants.TASK_TOBE_READ, listFormInst(dto2).size());
        } catch (SizeNullException e) {
            toReadCount = new TaskCount(Constants.TASK_TOBE_READ, 0);
        }

        List<TaskCount> taskCounts = new ArrayList<>();
        taskCounts.add(toDoCount);
        taskCounts.add(toReadCount);
        return taskCounts;
    }
    /*nfq:2019/10/11
    * */
    public List<TaskCount> getcommitFormCounts(String userID, String sceneId, String sectionId ) throws Exception{
        TaskCount comformCount; // 统计提交表单数量
        try {
            comformCount = new TaskCount(Constants.COMMIT_TASK,listComFormInst(userID, Constants.COMMIT_TASK, sceneId, sectionId).size());
        } catch (SizeNullException e){
           comformCount = new TaskCount(Constants.COMMIT_TASK, 0);
        }
        List<TaskCount> taskCounts1 = new ArrayList<>();
        taskCounts1.add(comformCount);
        return taskCounts1;
    }
    /**
     * 入口方法
     * 点击外链之后,显示当前待执行节点的表单sheet
     *
     * @param taskId
     */
    public FormInstVO getShareLinkTask(String taskId,
                                       String userId,
                                       String sectionId) throws ProcException, FormException {
        FormInstDO formInstDO = getFormInst(taskId);

//        这边就是，生成的这个外链复制给别人，然后别人点击，通过用户验证之后就可以去执行了
//        if(!userService.validateFormInst(formInstDO.getTaskId(),"?????"))
//            return RespBean.error("当前任务节点不属于该用户！");

        formInstDO = authorityService.handleFormSheetAuthority(formInstDO);

        String procModelId = formModelService.getProcModelID(formInstDO.getFormModelId());
        if (procModelId.equals(Constants.REGISTER_PROC_ID) ||
                procModelId.equals(Constants.SCENE_SELECT_PROC_ID)) {
            formInstDO.setNodeType(Constants.AS_NODE_APPROVE);
        } else {
            Integer nodeType = formInstDO.getNodeType();
            if (nodeType == null) {
                logger.error("流程中间层生成的流程模型出错！procModelId为:{}", procModelId);
                throw new ProcException("流程中间层生成的流程模型出错！");
            }
        }
        FormInstBO boo = new FormInstBO();
        BeanUtils.copyProperties(formInstDO, boo);

        boo.setCurUserId(userId);
        boo.setCurTaskType(Constants.TASK_ALL);
        boo.setProcModelId(procInstService.getProcModelId(formInstDO.getProcInstId()));
        boo.setNodeId(flowableService.getNodeId(taskId));

        ProcNodeDO nodeDO = procNodeService.getNodeDO(procModelId, boo.getNodeId());
        if (!StringUtils.isEmpty(nodeDO.getCandidateUser()))
            boo.setCandidateUser(nodeDO.getCandidateUser().split("\\|"));
        if (!StringUtils.isEmpty(nodeDO.getCandidateGroup()))
            boo.setCandidateGroup(nodeDO.getCandidateGroup().split("\\|"));

        boo.setIfJointSign(nodeDO.getIfJointSign());

        boo.setSceneId(formModelService.getSceneId(formInstDO.getFormModelId()));
//        FormInstBO formInstBO = new FormInstBO(formInstDO,userId,Constants.TASK_ALL);

        UserIdFilter userFilter = new UserIdFilter();
        DuplicateFilter duplicateFilter = new DuplicateFilter();

        //对不属于当前用户的表单任务进行筛选
        FormInstBO formInstBO1 = userFilter.shareLinkFiltrate(boo, sectionId);
        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
        FormInstBO formInstBO2 = duplicateFilter.shareLinkFiltrate(formInstBO1);

        FormInstVO voo = formInstBO2.transToVO(procInstService.getCommitter(formInstBO2.getTaskId()));

        return voo;
    }

    /**
     * 这里的获取任务节点信息都没有经过筛选，不完整
     *
     * @param userID
     * @param taskType
     * @param sceneId
     * @param sectionId
     * @return
     * @throws InfoException
     * @throws ProcException
     */
    public TaskCount getCount(String userID,
                              int taskType,
                              String sceneId,
                              String sectionId) throws InfoException, ProcException {
        //1、先获取流转到该用户对应的FlowableTaskDO
        List<FlowableTaskDO> tasks = flowableService.listCurTasks(userID);
        if (tasks.size() == 0)
            return new TaskCount(taskType, 0);

        List<TaskBO> taskBOs = constructTaskBO(tasks);

        //2、对上面集合进行遍历，从as_proc_node中取出ActType进行比对，确定节点类型，与输入的taskType比对，看是不是要显示的节点
        taskBOs = dressTaskByType(taskBOs, taskType);

//        //对用户、组织筛选
//        UserIdFilter userIdFilter = new UserIdFilter();
//        DuplicateFilter duplicateFilter = new DuplicateFilter();
//        SceneFilter sceneFilter = new SceneFilter();
//        //对不属于当前用户的表单任务进行筛选
//        ArrayList<FormInstBO> filtrate1 = userIdFilter.filtrate(formInstBOs,sectionId);
//        //如果当前任务节点是会签节点，那么需要过滤当前用户已经执行过该会签任务节点
//        ArrayList<FormInstBO> filtrate2 = duplicateFilter.filtrate(filtrate1);
//        //过滤工作场景
//        ArrayList<FormInstBO> filtrate3 = sceneFilter.filtrate(filtrate2,sceneId);

        return new TaskCount(taskType, taskBOs.size());
    }


    /**
     * 需要根据修改的节点类型的不同更新as_form_inst表中的不同字段
     */
    public FormInstDO updateFormInst(FormInstRecBase recBase) {
        FormInstDO inst = null;
        //审批表单实例,更新approve_result、executor、execute_time值
        //sheet、value不用更新
        if (recBase instanceof FormInstRecApprove) {
            String formInstSheet = JSONObject.toJSONString(((FormInstRecApprove) recBase).getForm_inst_sheet());
            inst = new FormInstDO.Builder()
                    .id(((FormInstRecApprove) recBase).getForm_inst_id())
                    .approveResult(((FormInstRecApprove) recBase).getApprove_result())
                    .executor(recBase.getEditor())
                    .procInstId(((FormInstRecApprove) recBase).getProc_inst_id())
                    .formInstValue(((FormInstRecApprove) recBase).getForm_inst_value())
                    .formInstSheetStr(formInstSheet)
                    .build();
            formInstMapper.approveFormInst(inst);
            procInstService.updateFormValueForAll(((FormInstRecApprove) recBase).getForm_inst_value(), inst.getProcInstId());
        }
        // 经办节点,更新executor、execute_time、sheet、value值
        // 注意这里还需要对as_proc_inst表的eform_inst_all_value字段进行更新
        else if (recBase instanceof FormInstRecHandle) {
            String formInstSheet = JSONObject.toJSONString(((FormInstRecHandle) recBase).getForm_inst_sheet());
            inst = new FormInstDO(
                    ((FormInstRecHandle) recBase).getProc_inst_id(),
                    ((FormInstRecHandle) recBase).getForm_inst_id(),
                    formInstSheet,
                    ((FormInstRecHandle) recBase).getForm_inst_value(),
                    recBase.getEditor()
            );
            formInstMapper.handleFormInst(inst);
            procInstService.updateFormValueForAll(((FormInstRecHandle) recBase).getForm_inst_value(), inst.getProcInstId());
        }
        // 抄送节点，更新executor、execute_time值
        else if (recBase instanceof FormInstRecReadle) {
            inst = new FormInstDO(
                    ((FormInstRecReadle) recBase).getForm_inst_id(),
                    recBase.getEditor()
            );
            formInstMapper.readFormInst(inst);
        }
        return inst;
    }

    /**
     * 业务入口
     * 这里根据Task信息返回对应的表单实例信息，但是注意这里表单实例信息中的formValue值来自as_proc_inst表中
     * formSheet值来自as_form_model
     *
     * @param taskBOs
     * @return
     */
    private List<FormInstDO> getFormInsts(List<TaskBO> taskBOs) {
        List<FormInstDO> formInstsByTasks = formInstMapper.getFormInstsByTasks(taskBOs);
        for (int i = 0; i < formInstsByTasks.size(); i++) {
            FormInstDO formInstDO = formInstsByTasks.get(i);
            String originFormSheet = JSON.toJSONString(formModelService.getModelSheet(formInstDO.getFormModelId()));
            formInstDO.setFormInstSheetStr(originFormSheet);
        }
        return formInstsByTasks;
    }

    /**
     * 由flowable数据库取出来的TASK信息加上当前task的节点类型信息，然后返回
     *
     * @param tasks
     */
    private List<TaskBO> constructTaskBO(List<FlowableTaskDO> tasks) throws ProcException {
        List<TaskBO> taskBOs = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            FlowableTaskDO cur = tasks.get(i);
            String procModelId = procInstService.getProcModelId(cur.getProcInstId());
            //这里说明我们在as_proc_inst表中找不到这个在ac_hi_actinst表中存在的流程实例，说明数据库中存在脏的流程实例数据
            if (StringUtils.isEmpty(procModelId)) {
                logger.error("在as_proc_inst表中找不到这个在act_hi_actinst表中存在的流程实例!没有如下ProcInstID:{}", cur.getProcInstId());
                throw new ProcException("有运行中流程实例没有与流程中间层绑定，请调用/completeAll，完成所有流程实例后，再重新尝试执行！");
            }

            Integer nodeType = null;

            if (procModelId.equals(Constants.REGISTER_PROC_ID) ||
                    procModelId.equals(Constants.SCENE_SELECT_PROC_ID)) {
                nodeType = Constants.AS_NODE_APPROVE;  // 节点设置为审批节点
            } else {
                nodeType = procNodeService.getNodeType(procModelId, cur.getActId());
                if (nodeType == null) {
                    logger.error("流程中间层生成的流程模型出错！procModelId为:{},没有如下NodeId:{}", procModelId, cur.getActId());
                    throw new ProcException("流程中间层生成的流程模型出错！");
                }
            }

            TaskBO taskBO = new TaskBO(cur, nodeType);
            taskBOs.add(taskBO);
        }
        return taskBOs;
    }

    /**
     * 对当前得到的Task进行筛选，看是否符合前台请求的任务类型（待办、待阅、全部）
     *
     * @param tasks
     * @param taskType
     * @return
     * @throws ProcException
     */
    private List<TaskBO> dressTaskByType(List<TaskBO> tasks, Integer taskType) throws ProcException {
        for (int i = 0; i < tasks.size(); i++) {
            TaskBO curTaskBO = tasks.get(i);
            //判断是不是符合当前页面要找的节点类型
            if (!nodeTypeMatch(curTaskBO.getNodeType(), taskType)) {
                tasks.remove(i);
                i--;
            }
        }
        return tasks;
    }

    /**
     * 独立功能模块
     * 创建表单实例
     *
     * @param formSheet
     * @param procInstId
     * @param editor
     * @param formModelId
     * @param formInstValue
     * @param taskId
     * @return 返回创建好的表单实例
     */
    public FormInstDO createFormInst(FormSheet formSheet,
                                     String procInstId,
                                     String editor,
                                     String formModelId,
                                     String formInstValue,
                                     String taskId) {
        String executionID = procInstService.getExecutionId(procInstId);
        String formInstJson = JSONObject.toJSONString(formSheet);
        FormInstDO inst = new FormInstDO.Builder()
                .formModelId(formModelId)
                .procInstId(procInstId)
                .executionId(executionID)
                .taskId(taskId)
                .executor(editor)
                .formInstValue(formInstValue)
                .formInstSheetStr(formInstJson).build();

        String procModelID = formModelService.getProcModelID(formModelId);

        if (procModelID.equals(Constants.REGISTER_PROC_ID) ||
                procModelID.equals(Constants.SCENE_SELECT_PROC_ID))
            inst.setNodeType(Constants.AS_NODE_APPLY);
        else {
            String firstNodeId = procNodeService.getFirstNodeId(procModelID);
            inst.setNodeType(procNodeService.getNodeType(procModelID, firstNodeId));
        }

        return inst;
    }


    /**
     * 获取流程实例中的上一个申请节点
     * 1、用comingflow那个操作获取上一个节点
     * 2、查看它的node——type是不是经办节点类型
     * 3、如果不是，回到1；如果是，返回该节点
     *
     * @param procInstId 当前待执行的Task所属的流程实例Id
     * @return
     */
    public String getLastApplyNode(String procInstId) {
        List<HistoricActivityInstance> historicActs = ProcUtils.getHistoricActs(procInstId);
        String procModelId = procInstService.getProcModelId(procInstId);
        for (HistoricActivityInstance instance : historicActs) {
            String actId = instance.getActivityId();
            if (procNodeService.getNodeType(procModelId, actId) == Constants.AS_NODE_APPLY) ;
            return actId;
        }
        return "";
    }


    public String[] generateUrls(String procInstId) {
        String[] curTaskIds = ProcUtils.getTaskIDs(procInstId);
        String[] urls = new String[curTaskIds.length];

        for (int i = 0; i < curTaskIds.length; i++) {
            urls[i] = Constants.REQUEST_URL_PREFIX + "/form_inst/share_link?task_id=" + curTaskIds[i];
        }
        return urls;
    }

    /**
     * 用户点击这个外链，自动跳转到相应的待办 或者 待阅 页面对任务节点进行处理
     *
     * @param taskId
     */
    public FormInstDO getFormInst(String taskId) {
        return formInstMapper.getFormInst(taskId);
    }

    public String getFormModelId(String taskId) {
        return formInstMapper.getFormModelIdByTaskId(taskId);
    }

    public void saveUnCompleteFormInst(FormInstDO formInst) {
        formInstMapper.saveUnCompleteFormInst(formInst);
    }


    public List<String> getAlreadyCompleteTask(String curUserId, String procInstId) throws FlowableException {
        return formInstMapper.getAlreadyCompleteTask(curUserId, procInstId);
    }

    /**
     * 获取指定实例Id的所有任务节点信息
     *
     * @param procInstId
     * @return
     */
    public List<AsFormInstDO> listFormInst(String procInstId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getProcInstId, procInstId);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs;
    }

    /**
     * 判断某一个实例所属的taskId是不是被正常执行了，还是这个实例被终止了，导致这个task_id在as_form_inst表中没有得到记录
     *
     * @return
     */
    public boolean isFormInstExecuted(String taskId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getTaskId, taskId);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 这里传入的task如果是被拒绝的，返回true；其他情况都是false，
     *
     * @param taskId
     * @return
     */
    public boolean isRejected(String taskId) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getTaskId, taskId)
                .eq(AsFormInstDO::getApproveResult, Constants.APPROVE_DISAGREE);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 如果是一个会签任务的话，可能会有多个记录
     *
     * @param procInstId
     * @return
     */
    public List<AsFormInstDO> getExecutorId(String procInstId, String executor) {
        QueryWrapper<AsFormInstDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsFormInstDO::getProcInstId, procInstId)
                .eq(AsFormInstDO::getExecutor, executor);
        List<AsFormInstDO> formInstDOs = asFormInstMapper.selectList(queryWrapper);
        return formInstDOs;
    }
}
