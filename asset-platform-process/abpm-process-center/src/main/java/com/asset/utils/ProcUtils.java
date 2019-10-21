package com.asset.utils;

import com.asset.exception.ProcException;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.RepositoryServiceImpl;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 与具体业务无关，用来处理Flowable流程
 *
 * @author YBY
 */
public class ProcUtils {

    @Autowired
    static ModelService modelService;

    static ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static RepositoryService repositoryService = engine.getRepositoryService();
    static RuntimeService runtimeService = engine.getRuntimeService();
    static TaskService taskService = engine.getTaskService();
    static HistoryService historyService = engine.getHistoryService();

    public static void completeAll() {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> lists = query.active().list();

        for (int j = 0; j < lists.size(); j++) {
            ProcessInstance instance = lists.get(j);

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                if (tasks.size() == 0)
                    break;

                //遍历，然后完成
                for (Task task : tasks) {
                    taskService.complete(task.getId());
                }
            }
        }
    }


    /**
     * 获取当前流程实例的正在运行的任务节点ID
     *
     * @param procInstID
     * @return
     */
    public static String[] getTaskIDs(String procInstID) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstID).list();
        String[] taskIDs = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++)
            taskIDs[i] = tasks.get(i).getId();
        return taskIDs;
    }

    /**
     * 结束当前的任务
     *
     * @param taskID
     */
    public static void completeTask(String taskID) throws FlowableException {
        taskService.complete(taskID);
    }


    public static void rollback(String executionId, String rollbackActID, String procInstID) {
        runtimeService.createChangeActivityStateBuilder().
                processInstanceId(procInstID)
                .moveExecutionToActivityId(executionId, rollbackActID).
                changeState();
    }

    public static ProcessInstance createProcInstByXml(String fileName) {
        //部署
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.addClasspathResource("diagram/" + fileName + ".bpmn");
        deployment.addClasspathResource("diagram/" + fileName + ".png");
        deployment.name("fileName");
        deployment.deploy();

        //这里是流程模型的ID值
        final String key = fileName;

        //启动流程并返回实例
        RuntimeService runtimeService = engine.getRuntimeService();
        return runtimeService.startProcessInstanceByKey(key);
    }

    public static ProcessInstance createProcInstByModelId(String procModelId) {
        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);

        DeploymentBuilder builder = repositoryService.createDeployment();
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        String processXMLName = modelData.getName() + ".bpmn20.xml";
        String depResourceName = modelData.getName() + "ResName";
        String depName = modelData.getName() + "DepName";
        String depKey = modelData.getName() + "DepKey";
        //部署
        Deployment dep = builder.addBpmnModel(depResourceName, bpmnModel).
                name(depName).
                key(depKey).
                addBytes(processXMLName, bpmnBytes).   //必须加这个，否则流程定义文件会为空
                deploy();

        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        //创建流程实例
        return runtimeService.startProcessInstanceById(def.getId());
    }

    /**
     * 挂起一个流程实例
     *
     * @param procInstId
     */
    public static void suspendProcInst(String procInstId) throws Exception {
        if (isFinished(procInstId))
            throw new ProcException("流程实例: " + procInstId + "已执行完毕，无法挂起！");
        //根据一个流程实例的id挂起该流程实例
        runtimeService.suspendProcessInstanceById(procInstId);

    }

    /**
     * 激活一个流程实例
     *
     * @param procInstId
     */
    public static void activateProcInst(String procInstId) throws Exception {
        if (isFinished(procInstId))
            throw new ProcException("流程实例: " + procInstId + "已执行完毕，无法执行激活操作！");

        runtimeService.activateProcessInstanceById(procInstId);
    }

    /**
     * 获取流程定义Id
     *
     * @param taskId
     * @return
     */
    public static String getProcDefIdByTaskId(String taskId) {
        ProcessDefinition procDef = getProcessDefinitionByTaskId(taskId);
        return procDef.getId();
    }

    /**
     * 由任务节点Id获取当前流程定义
     *
     * @param taskId
     * @return
     */
    public static ProcessDefinition getProcessDefinitionByTaskId(String taskId) {
        // 1. 得到task
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2. 通过task对象的pdid获取流程定义对象
        ProcessDefinition pd = repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        return pd;
    }

    /**
     * @param procInstanceID
     * @return 这个方法是返回当前执行的流程实例中影响域最前端的前面一个节点的信息
     */
    public static List<HistoricActivityInstance> getHistoricActs(String procInstanceID) {

        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(procInstanceID)
                .finished()//已经结束的活动节点
                .orderByHistoricActivityInstanceEndTime().desc().list();

        return historicActivityInstanceList;
    }

    /**
     * 判断nodeId这个节点之后是不是一个分支结构
     *
     * @param defId
     * @param nodeId
     * @return
     */
    public static boolean containParral(String defId, String nodeId) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(defId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask u = (UserTask) flowElement;
                if (u.getId().equals(nodeId)) {
                    List<SequenceFlow> outgoingFlows = u.getOutgoingFlows();
                    if (outgoingFlows.size() > 1)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断一个流程实例是不是已经被执行完了
     * 可用于完成流程图显示，如果流程完后使用历史服务查询，流程未结束使用运行时服务查询
     *
     * @param processInstanceId
     * @return
     */
    public static boolean isFinished(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().finished()
                .processInstanceId(processInstanceId).count() > 0;
    }

    /**
     * 删除流程
     *
     * @param procInstId
     */
    public static void deleteProcInst(String procInstId) {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
                .processInstanceId(procInstId) // 使用流程实例ID查询
                .singleResult();
        if (pi == null) {
            //该流程实例已经完成了
            historyService.deleteHistoricProcessInstance(procInstId);
        } else {
            //该流程实例未结束的
            runtimeService.deleteProcessInstance(procInstId, "");
            historyService.deleteHistoricProcessInstance(procInstId);//(顺序不能换)
        }
    }


    public static String getExecutionId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return task.getExecutionId();
    }

    /**
     * 审批节点点击“不同意”，导致实例结束
     */
    public static void completeProcInstForRejected(String taskId) throws FlowableException {
//        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(procInstId) // 使用流程实例ID查询
//                .singleResult();
//
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();
//
//            if (tasks.size() == 0)
//                break;
//
//            //遍历，然后完成
//            for (Task task : tasks) {
        taskService.complete(taskId);
//            }
//        }

    }

    public static void completeInst(String procInstId) {
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(procInstId) // 使用流程实例ID查询
                .singleResult();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

            if (tasks.size() == 0)
                break;

            //遍历，然后完成
            for (Task task : tasks) {
                taskService.complete(task.getId());
            }
        }
    }


//    /**
//     * 终止正在运行的流程实例
//     * @author:kaka
//     * @param dkey:流程定义key,businessKey:业务id
//     */
//    public void stopRunProcessInstance( String dkey,String businessKey) {
//        if( null != businessKey ) {
//            HistoricProcessInstance processInstanceId = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey( businessKey ).singleResult();
//            Task task = taskService.createTaskQuery().processInstanceId( processInstanceId.getId() ).singleResult();
//            if(null != task){
//                // 直接跳转到结束
//                String eventActivityId = getEndEventActivityId(dkey);
//                taskService.getCommandExecutor().execute( new JumpTaskCmdService( task.getExecutionId(), eventActivityId) );
//            }
//        }
//
//    }
//
//    public String getEndEventActivityId(String dkey){
//        String endActivityId = null;
//        List<ActivityImpl> allActivities = getAllActivities(dkey);
//        for(ActivityImpl activity : allActivities){
//            String type = activity.getProperty("type").toString();
//            if(type.equals("endEvent")){
//                endActivityId = activity.getId();
//                break;
//            }
//        }
//        return endActivityId;
//    }
//
//    //存放流程对应的所有activity
//    private static Map<String,List<ActivityImpl>> processActivitiMap = new HashMap<>();
//
//    public List<ActivityImpl> getAllActivities(String dkey) {
//        List<ActivityImpl> activities = processActivitiMap.get(dkey);
//        if(activities == null){
//            RepositoryService repositoryService = factory.getRepositoryService();
//            ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(dkey).singleResult();
//            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
//                    .getDeployedProcessDefinition(definition.getId());
//
//            //获取所有的activity
//            activities = processDefinition.getActivities();
//            processActivitiMap.put(dkey,activities);
//        }
//
//        return activities;
//    }

}
