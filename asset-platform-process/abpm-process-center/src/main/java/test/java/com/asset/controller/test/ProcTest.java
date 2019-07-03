package com.asset.controller.test;

import com.asset.FlowableApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 测试流程模型、流程部署、流程实例相关的内容
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class ProcTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    protected ModelService modelService;
    /**
     * 测试由流程部署ID创建流程实例
     */
    @Test
    public void testProc1() throws XMLStreamException, UnsupportedEncodingException {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();

        Model model = repositoryService.newModel();

        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));

        String modelId = "canvas";

        Model model1 = repositoryService.getModel(modelId);

//        String a = "{\"modelId\":\"549c3523-8e72-11e9-82d9-2e15a8856301\",\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"properties\":{\"process_id\":\"解放军流程KEY\",\"name\":\"解放军流程NAME\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.flowable.org/processdef\",\"process_historylevel\":\"\",\"isexecutable\":true,\"dataproperties\":\"\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\",\"process_potentialstarteruser\":\"\",\"process_potentialstartergroup\":\"\",\"iseagerexecutionfetch\":\"false\"},\"childShapes\":[{\"resourceId\":\"startEvent1\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formreference\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-68B29E92-A3F2-4772-B1BC-97359C10AE48\"}],\"bounds\":{\"lowerRight\":{\"x\":130,\"y\":193},\"upperLeft\":{\"x\":100,\"y\":163}},\"dockers\":[]},{\"resourceId\":\"sid-AF81F49E-333D-4F63-9836-87602C045799\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":\"\",\"formkeydefinition\":\"\",\"formreference\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\",\"skipexpression\":\"\",\"categorydefinition\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-3779BB3C-FA9F-4168-B743-9A82C0688B90\"}],\"bounds\":{\"lowerRight\":{\"x\":275,\"y\":218},\"upperLeft\":{\"x\":175,\"y\":138}},\"dockers\":[]},{\"resourceId\":\"sid-68B29E92-A3F2-4772-B1BC-97359C10AE48\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\",\"skipexpression\":\"\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-AF81F49E-333D-4F63-9836-87602C045799\"}],\"bounds\":{\"lowerRight\":{\"x\":174.15625,\"y\":178},\"upperLeft\":{\"x\":130.609375,\"y\":178}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-AF81F49E-333D-4F63-9836-87602C045799\"}},{\"resourceId\":\"sid-55134C26-9C30-4F8D-8A1F-DD83CFFC8045\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\"},\"stencil\":{\"id\":\"ThrowNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":350,\"y\":193},\"upperLeft\":{\"x\":320,\"y\":163}},\"dockers\":[{\"x\":335,\"y\":178}]},{\"resourceId\":\"sid-3779BB3C-FA9F-4168-B743-9A82C0688B90\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\",\"skipexpression\":\"\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-55134C26-9C30-4F8D-8A1F-DD83CFFC8045\"}],\"bounds\":{\"lowerRight\":{\"x\":319.390625,\"y\":178},\"upperLeft\":{\"x\":275.84375,\"y\":178}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":15,\"y\":15}],\"target\":{\"resourceId\":\"sid-55134C26-9C30-4F8D-8A1F-DD83CFFC8045\"}}],\"stencil\":{\"id\":\"BPMNDiagram\"},\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\",\"url\":\"../editor/stencilsets/bpmn2.0/bpmn2.0.json\"}}";
//        BpmnModel model = com.asset.controller.test.Test.convertToBpmnModel(a);
//        //查询当前执行到的任务
//        TaskService taskService = engine.getTaskService();
//        HistoryService historyService = engine.getHistoryService();
//
//        BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
//        ProcessDefinition definition = engine.getRepositoryService().
////        String processDefinitionId= "a190613Key:1:fcffdc4f-8da4-11e9-83dd-2e15a8856301";
//        String processDefinitionId= "549c3523-8e72-11e9-82d9-2e15a8856301";
////        BpmnModel bpmnModel = engine.getRepositoryService().getBpmnModel(processDefinitionId);
//        BpmnModel bpmnModel = (BpmnModel) engine.getRepositoryService().getBpmnModel(processDefinitionId);
////                getBpmnModel(processDefinitionId);
    }


    /**
     * 从流程模型到实例运行
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        String modelId = "549c3523-8e72-11e9-82d9-2e15a8856301";
//        ModelRepresentation modelRep = modelService.getModelRepresentation(modelId);
        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(modelId);
//        byte[] bytes = modelService.getBpmnXML(modelData);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);

        DeploymentBuilder builder = repositoryService.createDeployment();
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        String processName = modelData.getName()+".bpmn20.xml";
        //部署
        Deployment dep = builder.addBpmnModel("myCode",bpmnModel).
                name("MyCodeDeploy").
                key("MyCodeKey").
                addBytes(processName,bpmnBytes).   //必须加这个，否则流程定义文件会为空
                deploy();
        //获取流程定义
        ProcessDefinition def = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        //创建流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(def.getId());
        //获取要执行的任务节点
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();
        //执行当前任务
        taskService.complete(tasks.get(0).getId());

    }
}
