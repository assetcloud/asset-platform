package com.asset.utils;

import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * 与具体业务无关，用来处理Flowable流程
 *
 * @author YBY
 */
public class FlowableProcUtils {

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
    public static void completeTask(String taskID) {
        taskService.complete(taskID);
    }

    public static void rollback(String executionId, String rollbackActID, String procInstID) {
        runtimeService.createChangeActivityStateBuilder().processInstanceId(procInstID)
                .moveExecutionToActivityId(executionId, rollbackActID).changeState();
    }

    public static ProcessInstance createProcInstByXml(String fileName) {
        //部署
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.addClasspathResource(fileName + ".bpmn");
        deployment.addClasspathResource(fileName + ".png");
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

    public static boolean containParral(String defId, String lastApplyNodeId) {
        BpmnModel bpmnModel = repositoryService.getBpmnModel(defId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for (FlowElement flowElement : flowElements) {
            if (flowElement instanceof UserTask) {
                UserTask u = (UserTask) flowElement;
                if (u.getId().equals(lastApplyNodeId)) {
                    List<SequenceFlow> outgoingFlows = u.getOutgoingFlows();
                    if (outgoingFlows.size() > 1)
                        return true;
                }
            }
        }
        return false;
    }


}
