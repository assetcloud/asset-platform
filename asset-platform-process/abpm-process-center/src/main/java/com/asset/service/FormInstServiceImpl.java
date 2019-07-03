package com.asset.service.impl;

import com.asset.dao.*;
import com.asset.entity.AsFormInst;
import com.asset.entity.AsFormProcInstBind;
import com.asset.entity.ProcInst;
import com.asset.rec.BaseFormInstRec;
import com.asset.rec.FormInstApproveRec;
import com.asset.rec.FormInstCommitRec;
import com.asset.service.FormInstService;
import com.asset.utils.Constants;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 以下对流程实例的执行均只考虑非并行分支的情况
 */
@Service
public class FormInstServiceImpl implements FormInstService {
    @Autowired
    AsFormModelMapper asFormModelMapper;
    @Autowired
    ProcInstMapper procInstMapper;
    @Autowired
    AsFormInstMapper asFormInstMapper;
    @Autowired
    AsFormProcInstBindMapper asFormProcInstBindMapper;
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    AsProcModelMapper procModelMapper;



    @Autowired
    protected ModelService modelService;
    /**
     * 这边根据表单模型需要：
     * 1、创建流程实例
     * 2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
     * 3、流程实例与表单实例绑定，存入as_proc_relation表中
     *
     * 注意：表单的填写内容对流程的执行没有影响！只需要展示即可！对流程执行产生影响的只有“同意”“拒绝”这两个操作！
     * @param rec
     * @return
     */
    @Override
    public int commitFormInst(FormInstCommitRec rec) {
        //1、先获取与表单模型唯一绑定的流程模型ID
        String procModelID = asFormModelMapper.getProcModelID(rec.getForm_model_id());
        String defID = null;
        String deployID = null;
        //直接由流程模型后台创建流程实例
        ProcessInstance procInst = createProcInstance(procModelID,defID,deployID);

        //2、创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
        AsFormInst inst = saveCurTask(procInst,rec);


        //3、流程实例与表单实例绑定，存入as_proc_relation表中
        AsFormProcInstBind asProcRelation =  new AsFormProcInstBind(
                rec.getForm_model_id(),
                procModelID,
                defID,
                deployID,
                procInst.getProcessInstanceId(),
                inst.getId(),
                rec.getEditor()
        );
        asFormProcInstBindMapper.insertSelective(asProcRelation);

        //至此，相当于把第一个任务节点要填的表单内容存进数据库了，当前流程应当流转到下个任务节点上了
        completeCurTask(getTaskID(procInst.getProcessInstanceId()));

        return Constants.CODE_SUCCESS;
    }




    /**
     * 用户登录系统之后，根据选择点击的页面，显示不同类型的任务：待办（包含审批、经办）/待阅/全部
     * @param userID
     * @param taskType
     * @return
     */
    @Override
    public List<AsFormInst> getFormInsts(String userID, Integer taskType) {
        //先获取流转到该用户的所有流程实例ID
        List<String> procInstIDs = asFormInstMapper.getProcInstIDs(userID);
        //根据上面的ID把要找的AsFormInst对象都找出来，获取到的是流传到userID上的待处理的任务
        List<AsFormInst> formInsts = asFormInstMapper.getFormInsts(procInstIDs);

        //这边的逻辑是AsFormInst对象中存了表单实例对应的TaskID,取出来（TaskID），去act_hi_actinst去找对应的ACT_ID_，然后
        //再去as_proc_model表中找它的类型包含审批、经办、抄送，然后对应返回
        //审批和经办需要通知前台区分（这边先不区分，都是审批节点好了，具体怎么区分和前台商量）
        switch (taskType)
        {
            case Constants.TASK_TO_DO:
                formInsts = getTodoInsts(formInsts);
                break;
            case Constants.TASK_TOBE_READ:
                formInsts = getTobeReadInsts(formInsts);
                break;
            case Constants.TASK_ALL:
                //返回与这些流程实例ID绑定的表单实例的信息，不用处理，直接返回
                break;
        }

        return formInsts;
    }


    private List<AsFormInst> getTobeReadInsts(List<AsFormInst> formInsts) {
        List<ProcInst> procInsts = flowableMapper.getActIDs(formInsts);
//        AsProcModel model = procModelMapper.
        return null;
    }

    private List<AsFormInst> getTodoInsts(List<AsFormInst> formInsts) {
        return null;

    }

    /**
     * 审批节点对当前节点进行操作
     * 1、点“同意” 即代表当前任务节点会向后流转，同时当前填写的表单实例需要加上这个处理意见，给下一个节点过目
     * 2、点“拒绝” 那么就是流程需要回滚到申请节点那个位置（这边的处理逻辑变成 当前节点直接不可用）
     * @param rec
     */
    @Override
    public int approveFormInst(FormInstApproveRec rec) {
        //找到对应的流程实例的ID
        String procInstID = asFormProcInstBindMapper.getProcInstID(rec.getForm_inst_id());
        //当前审批节点同意申请，先把当前新加了 同意 这个信息的 新表单 放入数据库，并完成当前任务节点
        if(rec.getApprove_status() == Constants.APPROVE_AGREE)
        {
            saveCurTask(getProcInst(procInstID),rec);
            completeCurTask(getTaskID(procInstID));
        }
        //当前审批节点拒绝申请，那么就是直接回滚到上个申请节点（如果前一个是审批节点，前前一个是申请节点，那么就是回滚到前前节点的位置）
        else if(rec.getApprove_status() == Constants.APPROVE_DISAGREE)
        {
            //正常做法应该是从数据库中找到上一个申请节点，这里先直接指定，因为对流程引擎还没改造完全
//            String rollbackActID = procInstMapper.getApplyNode(procInstID);
            String rollbackActID = "UserTask1";
            //在调用Flowable的方法进行回滚之前，还需要对as_form_inst表进行修改，把申请节点和审批节点中间的这些节点的信息都置为不可用状态
//            procInstMapper.deleteNode(procInstID);
            //调用Flowable的方法进行回滚
            rollback(procInstID,rollbackActID);
        }
        return Constants.CODE_SUCCESS;
    }

    /**
     * 从当前审批节点回滚到上一个申请节点
     * @param procInstID
     * @param rollbackActID 上一个申请节点的ActivityID
     */
    private void rollback(String procInstID,String rollbackActID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();

        runtimeService.createChangeActivityStateBuilder().processInstanceId(procInstID)
                .moveExecutionToActivityId(getExecutionId(procInstID), rollbackActID).changeState();
    }

    //创建表单实例，表单实例信息（包含了流程实例的一些信息）存入as_form_inst表中
    private AsFormInst saveCurTask(ProcessInstance procInst, BaseFormInstRec rec){
        String executionID = getExecutionId(procInst.getProcessInstanceId());
        String taskID = getTaskID(procInst.getProcessInstanceId());
        String formInstJson = rec.getForm_inst_json();

        AsFormInst inst = new AsFormInst(
                rec.getForm_model_id(),
                procInst.getProcessInstanceId(),
                executionID,
                taskID,
                rec.getEditor(),
                formInstJson
        );
        asFormInstMapper.insertSelective(inst);
        return inst;
    }

    private ProcessInstance getProcInst(String procInstId){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        return runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
    }


    private void completeCurTask(String taskID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        taskService.complete(taskID);
    }

    private String getTaskID(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstID).list();
        return tasks.get(0).getId();
    }

    private String getExecutionId(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = engine.getRuntimeService();

        ExecutionQuery executionQuery = runtimeService.createExecutionQuery().processInstanceId(procInstID);
        List<Execution> executions = executionQuery.list();

        Execution temp = null;
        for (int j = 0; j < executions.size(); j++) {
            temp = executions.get(j);
            if (temp.getActivityId() == null)
                continue;
            else
                break;
        }
        return temp.getId();
    }

    //直接由流程模型ID创建相应的流程实例
    private ProcessInstance createProcInstance(String procModelId,String defID,String deployID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();


        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);

        DeploymentBuilder builder = repositoryService.createDeployment();
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        String processXMLName = modelData.getName()+".bpmn20.xml";
        String depResourceName = modelData.getName()+"ResName";
        String depName = modelData.getName()+"DepName";
        String depKey = modelData.getName()+"DepKey";
        //部署
        Deployment dep = builder.addBpmnModel(depResourceName,bpmnModel).
                name(depName).
                key(depKey).
                addBytes(processXMLName,bpmnBytes).   //必须加这个，否则流程定义文件会为空
                deploy();
        //获取流程定义
        deployID = dep.getId();
        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        defID = def.getId();
        //创建流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(def.getId());
        return instance;
    }

}
