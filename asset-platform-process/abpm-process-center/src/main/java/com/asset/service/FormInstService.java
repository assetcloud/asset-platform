package com.asset.service;

import
        com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.command.*;
import com.asset.converter.FormConverter;
import com.asset.entity.*;
import com.asset.exception.*;
import com.asset.filter.DuplicateFilter;
import com.asset.javabean.*;
import com.asset.javabean.form.ColumnItem;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.FormSheet;
import com.asset.dto.*;
import com.asset.mapper.AsTaskMapper;
import com.asset.service.impl.ActRuVariableService;
import com.asset.step.LoadUncompleteTasksCommand;
import com.asset.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dom4j.DocumentException;
import org.flowable.bpmn.model.*;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.ui.modeler.service.ModelServiceImpl;
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
    GetTaskCommand getTaskCommand;
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
    AsTaskMapper asTaskMapper;

    @Autowired
    ModelServiceImpl modelService;

    @Autowired
    DuplicateFilter duplicateFilter;
    @Autowired
    ProcUtils procUtils;
    @Autowired
    RollbackCommand rollbackCommand;
    @Autowired
    ShowTaskCommand showTaskCommand;

    @Autowired
    UpdateTaskStatusCommand updateTaskStatusCommand;
    @Autowired
    LoadUncompleteTasksCommand loadUncompleteTasksCommand;
    @Autowired
    HandleTaskExecuteCommand handleTaskExecuteCommand;

    @Autowired
    ConstructCommand constructCommand;
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
    public String[] commitNewFormInst(FormInstRecCreate dto) throws DocumentException, FlowableException, DatabaseException, InterruptedException {
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



        AsTaskDO inst = constructCommand.constructCompleteTaskRecord(dto.getForm_inst_sheet(),
                procInst.getProcessInstanceId(),
                dto.getEditor(),
                dto.getForm_model_id(),
                dto.getForm_inst_value(),
                taskIDs[0]);

        asTaskMapper.insert(inst);

        String[] hasAddedNodeId = new String[taskIDs.length];
        int c = 0;
        hasAddedNodeId[c] = flowableService.getNodeId(taskIDs[0]);

        //2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        for (int i = 1; i < taskIDs.length; i++) {
            String curNodeId = flowableService.getNodeId(taskIDs[i]);
            if (!CommonUtils.isStringArrayContain(hasAddedNodeId, curNodeId)) {
                AsTaskDO instUncomplete = constructCommand.constructCompleteTaskRecord(dto.getForm_inst_sheet(),
                        procInst.getProcessInstanceId(),
                        null,
                        dto.getForm_model_id(),
                        dto.getForm_inst_value(),
                        taskIDs[i]);
                asTaskMapper.insert(instUncomplete);
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
    public List<CommitFormInstDO> listComFormInst(String userID,
                                                  Integer taskType,
                                                  String curSelectSceneId,
                                                  String sectionId) throws InfoException, ProcException, FormException {

        List<CommitFormInstDO> tasks = flowableService.listComFormInst1(userID, curSelectSceneId);
        // ArrayList<AsTaskVO> formInstVOs = new ArrayList<>();
        //formInstVOs.add(tasks);
        //return formInstVOs;
        return tasks;


    }

/**
     * 业务入口
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     *
     * @param userID
     * @param taskType
     * @param curSelectSceneId 当前用户选择的工作场景Id
     * @param sectionId
     * @return
     * @throws InfoException
     * @throws ProcException
     * @throws FormException
     */
    public List<AsTaskVO> listFormInst(String userID,
                                       Integer taskType,
                                       String curSelectSceneId,
                                       String sectionId) throws InfoException, ProcException, FormException {
        ArrayList<AsTaskVO> asTaskVOS = showTaskCommand.showTasks(taskType, userID, curSelectSceneId, sectionId);
        return asTaskVOS;
    }


    /**
     * 业务入口
     * 对审批节点进行审批
     *
     * @param dto
     */
    public String[] approveNode(FormInstRecApprove dto) throws Exception {
        //找到当前传入的表单实例对应的流程实例的ID，注意和TaskID进行区分！！一次执行中，TaskId会一直变化，但是流程实例ID是不会变的
//        String procInstID = formInstMapper.getProcInstID(dto.getForm_inst_id());
        String procInstID = dto.getProc_inst_id();
        String procModelId = formModelService.getProcModelID(dto.getForm_model_id());

        //审批节点还可以对表单内容进行修改,这里的代码在updateFormInst方法里面写了
        //当前填写表单数据 对数据库进行更新
        handleTaskExecuteCommand.handleApproveTask(dto);

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
            handleTaskExecuteCommand.handleApproveTask(dto);
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
        } else if (dto.getApprove_result() == Constants.APPROVE_DISAGREE) {
            String[] strings = null;

            //注意，每个审批节点在流程模型设计阶段都会设置一个驳回属性：approveType，即当点击“不同意”按钮之后实例发生的变化
            //但是，现在流程模型设计阶段并没有对approve_type这个属性进行操作（还没做，后续需要你做）
            ProcNodeDO nodeDO = procNodeService.getNodeDO(formModelService.getProcModelID(dto.getForm_model_id()), flowableService.getNodeId(dto.getTask_id()));
            Integer approveType = nodeDO.getApproveType();
            //一个审批节点默认的驳回属性是：结束整个流程，即CANCEL
            if (approveType == null)
                approveType = Constants.NODE_APPROVE_CANCEL;

            switch (approveType) {
                //当前审批节点的驳回属性设置为：直接结束整个流程
                case Constants.NODE_APPROVE_CANCEL:
                    ProcUtils.completeProcInstForRejected(dto.getTask_id());
                    break;
                //当前审批节点的驳回属性设置为：回滚到上一个经办节点处
                case Constants.NODE_APPROVE_ROLLBACK:
                    String rollbackNodeId;
                    AsSimpleTask simpleTask = new AsSimpleTask.Builder()
                            .executionId(executionId)
                            .taskId(dto.getTask_id())
                            .procInstId(procInstID).build();

                    dto.setModelEditorJson(flowableService.getModelEditorJson(procModelId));

                    //先判断当前实例中是否包含并行分支，如果是包含并行分支的，需要根据当前任务节点的位置，选择正确的回滚点位置
                    if (dto.containParallel())
                    {
                        //回滚+更新被回滚任务状态码+加载新生成的任务节点
                        HashMap<String, Object> hashMap = rollbackCommand.rollbackParallelProc(procInstID, procModelId, dto.getTask_id());
                        rollbackNodeId = (String) hashMap.get("rollback");
                        HashMap<String, AsExecution> allExes = (HashMap<String, AsExecution>)hashMap.get("allExes");
                        updateTaskStatusCommand.updateRollbackTaskStatus(rollbackNodeId,allExes);
                        strings = loadUncompleteTasksCommand.loadUncompleteTasks(procInstID,dto.getForm_model_id());
                    }
                    //不包含并行分支，直接进行回滚
                    else
                    {
                        //回滚+更新被回滚任务状态码+加载新生成的任务节点
                        rollbackNodeId = rollbackCommand.rollbackNormalProc(simpleTask);
                        updateTaskStatusCommand.updateRollbackTaskStatus(simpleTask,rollbackNodeId);
                        strings = loadUncompleteTasksCommand.loadUncompleteTasks(procInstID,dto.getForm_model_id());
                    }

                    break;
                //当前审批节点的驳回属性设置为：高级回滚，可以指定回滚到任意经办节点，这个功能暂时不做
                case Constants.NODE_APPROVE_ROLLBACK_PLUS:
                    throw new ProcException("暂时不支持该回滚类型！请检查node：" + nodeDO.getNodeId() + " 的属性");
            }
            return strings;
        }
        return generateUrls(procInstID);
    }





    /**
     * 获取第index个经办节点
     *
     * @param index
     * @return
     */
    public AsTask getApproveTask(String procModelId, int index, ArrayList<AsTask> executions) {
        int i = 1;

        //遍历executions，找到第index个经办任务，i用来计数
        for (int f = 0; f < executions.size(); f++) {
            //限定只对userTask进行处理
            if (!executions.get(f).getActType().equals("userTask"))
                continue;

            if (procNodeService.getNodeType(procModelId, executions.get(f).getActId()) == Constants.AS_NODE_APPLY) {
                if (i == index)
                    return executions.get(f);
                else
                    i++;
            }
        }

        return null;
    }





    /**
     * @param historicActsAsc
     * @param modelFlowMents
     * @param runnningExecutions
     * @param curExecution
     * @param i
     * @param procModelId
     * @param isVisited
     * @param nodeContainsIn
     * @throws Exception
     */
    public void constructExecutions(List<HistoricActivityInstance> historicActsAsc,
                                    ArrayList<FlowElement> modelFlowMents,
                                    HashMap<String, ProcExecution> runnningExecutions,
                                    ProcExecution curExecution,
                                    int i,
                                    String procModelId,
                                    HashMap<String, Boolean> isVisited,
                                    HashMap<String, String[]> nodeContainsIn) throws Exception {
        //先从头遍历，构建多条执行序列
        for (; i < modelFlowMents.size(); i++) {
            FlowElement flowElement = modelFlowMents.get(i);
            String curNodeExecutionId = getExecutionId(flowElement.getId(), historicActsAsc);
            //这边认为模型与实例是一一对应好的，不存在模型与实例元素不匹配的情况
            //当前模型中遍历的元素在执行序列中没有记录，可以认为当前元素没有被执行过，所以跳过
            //如果这个元素已经被访问过了，也不能够继续被访问
            if (curNodeExecutionId == null || isVisited.get(flowElement.getId())) {
                continue;
//                throw new ProcException("流程执行序列出错，实例:"+historicActsAsc.get(0).getProcessInstanceId()+"与对应模型中元素："+flowElement.getId()+" 无法匹配，当前审批任务无法完成回滚！");
            }


            //接着判断当前元素是不是属于当前的执行序列，如果不是，需要找到对应的执行序列，添加进去
            boolean isMatch = curExecution.match(curNodeExecutionId);
            //当前遍历的元素不在当前执行序列上，先去执行序列表中找是否有这么一条序列，如果有的话，切换到这条序列上；如果没有，创建新的序列，增加该元素
            if (!isMatch) {
                //获取正确的执行序列
                ProcExecution execution = runnningExecutions.get(curNodeExecutionId);
//                ProcExecution execution = getExecution(curNodeExecutionId, runnningExecutions);
                //找不到，说明该执行序列没有添加到执行序列表中，需要新建，元素不是在这个时候加，需要先判断类型
                if (execution == null) {
                    ProcExecution newExecution = new ProcExecution(curNodeExecutionId);
                    newExecution.setExecutionId(curNodeExecutionId);
                    runnningExecutions.put(curNodeExecutionId, newExecution);
                    curExecution = newExecution;
                } else {
                    curExecution = execution;
                }
            }

            //看是否需要新增一条executions
            if (flowElement instanceof StartEvent) {
                curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_START, curNodeExecutionId));
            } else if (flowElement instanceof UserTask) {
                Integer nodeTyep = procNodeService.getNodeType(procModelId, flowElement.getId());
                curExecution.add(new ProcNode(flowElement.getId(), nodeTyep, curNodeExecutionId));
            } else if (flowElement instanceof ParallelGateway) {
                ParallelGateway gateway = (ParallelGateway) flowElement;
                List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
                List<SequenceFlow> incomingFlows = gateway.getIncomingFlows();
                //出度为1，那么就是join，即end
                if (outgoingFlows.size() == 1)
                    curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_PARALLEL_end, curNodeExecutionId));
                else {
                    curExecution.add(new ProcNode(flowElement.getId(), Constants.AS_NODE_PARALLEL_start, curNodeExecutionId));

                    //所有的出度，每一个出度建立一个execution,然后把原有的代替掉
                    for (int n = 0; n < outgoingFlows.size(); n++) {
                        SequenceFlow curSequence = outgoingFlows.get(n);
                        UserTask userTask = (UserTask) curSequence.getTargetFlowElement();
                        String matchExecutionId = getExecutionId(userTask.getId(), historicActsAsc);

                        if (runnningExecutions.get(matchExecutionId) == null) {
                            ProcExecution newExecution = new ProcExecution();
                            BeanUtils.copyProperties(newExecution, curExecution);
                            newExecution.setExecutionId(matchExecutionId);
                            runnningExecutions.put(matchExecutionId, newExecution);
                        }
                    }
                }
            }
        }
    }

//    public ArrayList<String> getLastNode(FlowElement flowElement) {
//        ArrayList<String> lastNodes = new ArrayList<>();
//        if (flowElement instanceof UserTask) {
//            List<SequenceFlow> incomingFlows = ((UserTask) flowElement).getIncomingFlows();
//            for (SequenceFlow sequenceFlow : incomingFlows) {
//                lastNodes.add(sequenceFlow.getSourceRef());
//            }
//
//        } else if (flowElement instanceof ParallelGateway) {
//
//        }
//    }

    private ProcExecution getExecution(String executionId, ArrayList<ProcExecution> executions) {
        ProcExecution procExecution = null;

        for (ProcExecution execution : executions) {
            if (execution.getExecutionId().equals(executionId)) {

                if (procExecution == null) {
                    procExecution = execution;
                } else {
                    if (execution.getProcNodes().size() > procExecution.getProcNodes().size())
                        procExecution = execution;
                }
            }
        }
        return procExecution;
    }

    /**
     * 根据nodeId获取对应的执行序列中的executionId
     *
     * @param nodeId
     * @param historicActsAsc
     * @return
     */
    private String getExecutionId(String nodeId, List<HistoricActivityInstance> historicActsAsc) {
        for (HistoricActivityInstance hi : historicActsAsc) {
            if (hi.getActivityId().equals(nodeId))
                return hi.getExecutionId();
        }

        return null;
    }

    public String getProcInstId(String taskId) {
        AsTaskDO taskDO = asTaskMapper.selectById(taskId);
        return taskDO.getProcInstId();
    }

    /**
     * 业务入口
     * 对经办节点进行处理
     *
     * @param rec
     */
    public String[] applyNode(FormInstRecApproval rec) throws FlowableException {
        //当前填写表单数据 对数据库进行更新
        handleTaskExecuteCommand.handleApprovalTask(rec);
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
        handleTaskExecuteCommand.handleReadTask(rec);
        //完成当前抄送任务,抄送任务之后也不需要考虑对当前结果的处理意见进行一个保存
        //抄送任务结束之后，默认后面是没有任务节点了！！所以这里不需要再保存未完成任务节点
        ProcUtils.completeTask(rec.getTask_id());
        return generateUrls(rec.getProc_inst_id());
    }

    /**
     * 入口方法
     * 统计各个类型的表单实例数目分别是多少
     *
     * @param userID
     * @return
     */
    public List<TaskCount> getFormInstsCounts(String userID, String sceneId, String sectionId) throws Exception {
        TaskCount toDoCount;    //nfq:这个是统计待办的
        TaskCount toReadCount;   //nfq:这个是统计待阅的
        try {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, listFormInst(userID, Constants.TASK_TO_DO, sceneId, sectionId).size());
        } catch (SizeNullException e) {
            toDoCount = new TaskCount(Constants.TASK_TO_DO, 0);
        }

        try {
            toReadCount = new TaskCount(Constants.TASK_TOBE_READ, listFormInst(userID, Constants.TASK_TOBE_READ, sceneId, sectionId).size());
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
    public List<TaskCount> getcommitFormCounts(String userID, String sceneId, String sectionId) throws Exception {
        TaskCount comformCount; // 统计提交表单数量
        try {
            comformCount = new TaskCount(Constants.COMMIT_TASK, listComFormInst(userID, Constants.COMMIT_TASK, sceneId, sectionId).size());
        } catch (SizeNullException e) {
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
    public AsTaskVO getShareLinkTask(String taskId,
                                     String userId,
                                     String sectionId) throws ProcException, FormException {
        return getTaskCommand.getShareLinkTask(taskId,userId,sectionId);
    }






    public String[] generateUrls(String procInstId) {
        String[] curTaskIds = ProcUtils.getTaskIDs(procInstId);
        String[] urls = new String[curTaskIds.length];

        for (int i = 0; i < curTaskIds.length; i++) {
            urls[i] = Constants.REQUEST_URL_PREFIX + "/form_inst/share_link?task_id=" + curTaskIds[i];
        }
        return urls;
    }

    public String getFormModelId(String taskId) {
        AsTaskDO taskDO = asTaskMapper.selectById(taskId);
        return taskDO.getFormModelId();
    }

    public void saveUnCompleteFormInst(AsTaskDO taskDO) {
        asTaskMapper.insert(taskDO);
    }



    /**
     * 获取指定实例Id的所有任务节点信息
     *
     * @param procInstId
     * @return
     */
    public List<AsTaskDO> listFormInst(String procInstId) {
        QueryWrapper<AsTaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTaskDO::getProcInstId, procInstId);
        List<AsTaskDO> formInstDOs = asTaskMapper.selectList(queryWrapper);
        return formInstDOs;
    }

    /**
     * 判断某一个实例所属的taskId是不是被正常执行了，还是这个实例被终止了，导致这个task_id在as_form_inst表中没有得到记录
     *
     * @return
     */
    public boolean isFormInstExecuted(String taskId) {
        QueryWrapper<AsTaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTaskDO::getId, taskId);
        List<AsTaskDO> formInstDOs = asTaskMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 这里传入的task如果是被拒绝的，返回true；其他情况都是false，
     *
     * @param taskId
     * @return
     */
    public boolean isRejected(String taskId) {
        QueryWrapper<AsTaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTaskDO::getId, taskId)
                .eq(AsTaskDO::getApproveResult, Constants.APPROVE_DISAGREE);
        List<AsTaskDO> formInstDOs = asTaskMapper.selectList(queryWrapper);
        return formInstDOs.size() != 0;
    }

    /**
     * 如果是一个会签任务的话，可能会有多个记录
     *
     * @param procInstId
     * @return
     */
    public List<AsTaskDO> getExecutorId(String procInstId, String executor) {
        QueryWrapper<AsTaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(AsTaskDO::getProcInstId, procInstId)
                .eq(AsTaskDO::getExecutor, executor);
        List<AsTaskDO> formInstDOs = asTaskMapper.selectList(queryWrapper);
        return formInstDOs;
    }
}
