package com.asset.service;

import com.asset.dao.ActTypeMapper;
import com.asset.dao.AsProcInstMapper;
import com.asset.dao.FlowableMapper;
import com.asset.dao.FormInstMapper;
import com.asset.entity.AsTask;
import com.asset.form.FormJson;
import org.dom4j.*;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.api.io.InputStreamProvider;
import org.flowable.common.engine.impl.util.io.InputStreamSource;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class ProcInstService {
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    protected ModelService modelService;
    @Autowired
    FormInstService formInstService;
    @Autowired
    FormInstMapper formInstMapper;
    @Autowired
    ActTypeMapper actTypeMapper;
    @Autowired
    AsProcInstMapper procInstMapper;

    public ProcessInstance getProcInst(String procInstId){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        return runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
    }

    /**
     * 当前任务节点完成之后，会自动流转到下一个任务节点，数据库表中会生成这个没有被完成的任务节点的信息，
     * 这里是在as_proc_inst表中写入这个信息
     */
    public void saveUnCompleteTask( String procInstId,
                                    String formModelId,
                                    String formInstValue) {
        FormJson unCompleteFormJson = formInstService.getFormJson(formModelId);
        //获取当前执行到的任务节点
        String[] taskIDs = getTaskIDs(procInstId);
        //如果下面还有任务节点要处理，就在as_proc_inst中新建这个表单实例字段（每一个TaskId对应一个新的表单实例）
        for (int i=0;i<taskIDs.length;i++){
            String a = formInstMapper.getTaskId(taskIDs[i]);
            if (!taskIDs[i].isEmpty()&&
                    a== null )  //当前要存的taskID不能是已经有的的，否则重复保存了
                formInstService.createFormInst(unCompleteFormJson,
                        procInstId,
                        "",
                        formModelId,
                        formInstValue,
                        taskIDs[i]);
        }
    }

    public List<AsTask> getCurTasks(String userID) {
        return flowableMapper.getTaskInfos(userID);
    }

    public String getProcModelIdByProc(String procInstId) {
        return procInstMapper.getProcModelId(procInstId);
    }

    public Integer getActType(String procModelId, String actId) {
        return actTypeMapper.getActType(procModelId,actId);
    }

    /**
     * 结束当前的任务
     * @param taskID
     */
    public void completeCurTask(String taskID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = engine.getTaskService();

        taskService.complete(taskID);
    }

    /**
     * 获取当前流程实例的正在运行的任务节点ID
     * @param procInstID
     * @return
     */
    public String[] getTaskIDs(String procInstID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(procInstID).list();
        String[] taskIDs = new String[tasks.size()];
        for (int i=0;i<tasks.size();i++)
            taskIDs[i]=tasks.get(i).getId();
        return taskIDs;
    }

    public String getExecutionId(String procInstID) {
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

    public String getActId(String taskId) {
        return flowableMapper.getActId(taskId);
    }

    /**
     * 直接由流程模型ID创建相应的流程实例
     * @param procModelId
     * @param defID 传进来的可以为空，会在创建实例后更新该值
     * @param deployID 传进来的可以为空，会在创建实例后更新该值
     * @return
     */
    public HashMap<String,Object> createProcInstance(String procModelId,String defID,String deployID) {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();


        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);


        //判断是否包含会签功能
        if(contains())
        {
            try {
                Document document = modelToDocument(bpmnModel);
                Document document1 = addJointlySign(procModelId,document);
                bpmnModel = documentToModel(document1);
            } catch (DocumentException e) {

            }
        }

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

        HashMap<String,Object> map = new HashMap<>();
        map.put("inst",instance);
        map.put("defID",defID);
        map.put("deployID",deployID);

        return map;
    }

    private BpmnModel documentToModel(Document document1) {
        String str = document1.asXML();
        InputStream   inputStream   =   new   ByteArrayInputStream(str.getBytes());
        //创建转换器
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        InputStreamProvider inputStreamProvider  = new InputStreamSource(inputStream);
        return bpmnXMLConverter.convertToBpmnModel(inputStreamProvider,
                true,
                false,
                "UTF-8");
    }

    private Document modelToDocument(BpmnModel bpmnModel) throws DocumentException {
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        //转成string格式的xml数据
        String bytes=new String(bpmnBytes);
        //解析，添加会签功能
        return DocumentHelper.parseText(bytes);
    }

    /**
     * 这个方法用来判断当前要发起的流程模型是不是包含会签功能
     * @return
     */
    private boolean contains() {
        return true;
    }

    /**
     * 添加会签用标签
     * @param document
     * @return
     */
    public Document addJointlySign(String procModelId,Document document) throws DocumentException {
        Element definitions = document.getRootElement();
        Element process=definitions.element("process");
        List nodes = process.elements("userTask");

        Iterator iter = nodes.iterator();
        while (iter.hasNext()) {
            Element element = (Element) iter.next();
            Attribute attribute=element.attribute("id");
            if(attribute.getData().equals("task1")){
                Element child = element.addElement("multiInstanceLoopCharacteristics");
                child.addAttribute("isSequential","true");

                Element grandChild = child.addElement("loopCardinality");
                grandChild.addText("2");
            }
        }

        return document;

//        //获取部署资源输入流
//        InputStream inputStream = new ByteArrayInputStream(bytes.getBytes());;

    }

    public void completeAll() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> lists = query.active().list();

        for(int j = 0 ; j<lists.size() ; j++) {
            ProcessInstance instance = lists.get(j);

            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
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

}
