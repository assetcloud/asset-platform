package com.asset.test;

import com.asset.FlowableApplication;
import com.asset.service.ProcInstService;
import org.dom4j.*;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Writer;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class Proc {

    @Autowired
    ProcInstService procInstService;

    /**
     * 测试流程会签功能
     */
    @Test
    public void test1(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();


        String procModelId = "37b2621e-a917-11e9-9643-2e15a8856301";
        String defId = null;
        String deployId = null;
        HashMap<String,Object> map = procInstService.createProcInstance(procModelId,defId,deployId);
        ProcessInstance instance = (ProcessInstance) map.get("inst");
        defId = (String) map.get("defID");
        deployId = (String) map.get("deployID");

        String[] taskIds = procInstService.getTaskIDs(instance.getProcessInstanceId());


        MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = new MultiInstanceLoopCharacteristics();
        // 配置节点人员办理顺序 串行:true 并行:false.
        multiInstanceLoopCharacteristics.setSequential(false);
        multiInstanceLoopCharacteristics.setLoopCardinality("2");

        // 根据任务ID获取当前任务对象.
        Task task = taskService.createTaskQuery().taskId(taskIds[0]).singleResult();
        if (null == task) {

        }

        // 根据流程定义ID获取流程bpmnModel.
        BpmnModel bpmnModel = repositoryService.getBpmnModel(defId);
        // 获取当前流程对象.
        Process process = bpmnModel.getProcesses().get(0);

        // 根据当前节点ID获取对应任务节点对象.
        UserTask currenUserTask = (UserTask) process.getFlowElement(task.getTaskDefinitionKey());

        currenUserTask.setLoopCharacteristics(multiInstanceLoopCharacteristics);

        // 获取流程引擎配置实现类.
        ProcessEngineConfigurationImpl processEngineConfiguration =
                (ProcessEngineConfigurationImpl) ProcessEngines.getDefaultProcessEngine().getProcessEngineConfiguration();
        // 创建新的任务实例.
        UserTaskActivityBehavior userTaskActivityBehavior =
                processEngineConfiguration.getActivityBehaviorFactory().createUserTaskActivityBehavior(currenUserTask);

        //当前节点是经办节点先处理结束了
        procInstService.completeCurTask(taskIds[0]);




//
//        List<String> candidate = new ArrayList<>();
//        candidate.add("user1");
//        candidate.add("user2");
//        candidate.add("user3");
//        currenUserTask.setCandidateUsers(candidate);


//        // 获取当前节点出线信息.
//        SequenceFlow sequenceFlow = currenUserTask.getOutgoingFlows().get(0);
//
//        // 根据当前节点出线信息获取下一节点元素
//        FlowElement flowElement = process.getFlowElement(sequenceFlow.getTargetRef());
//
//        // 判断下一节点元素是否为任务节点.
//        if (!(flowElement instanceof UserTask)) {
//
//        }

    }

    @Test
    public void completeAll(){
//        procInstService.completeAll();
        procInstService.completeCurTask("1e484ecc-a925-11e9-a14a-2e15a8856301");
    }

    @Test
    public void testDom() throws DocumentException {
        String a = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.flowable.org/processdef\">\n" +
                "  <process id=\"huiqiantest\" name=\"huiqiantest\" isExecutable=\"true\">\n" +
                "    <startEvent id=\"startEvent1\"></startEvent>\n" +
                "    <userTask id=\"task1\"></userTask>\n" +
                "    <sequenceFlow id=\"sid-1C65E743-B71F-4659-AAD6-471068933046\" sourceRef=\"startEvent1\" targetRef=\"task1\"></sequenceFlow>\n" +
                "    <userTask id=\"task2\"></userTask>\n" +
                "    <sequenceFlow id=\"sid-DC648D03-6B6C-474F-BA29-C722DD1C989C\" sourceRef=\"task1\" targetRef=\"task2\"></sequenceFlow>\n" +
                "    <endEvent id=\"sid-B83EA6FD-2790-47DC-814C-44F4E9874168\"></endEvent>\n" +
                "    <sequenceFlow id=\"sid-C91335DB-6C6F-4B69-B143-880B51AD511D\" sourceRef=\"task2\" targetRef=\"sid-B83EA6FD-2790-47DC-814C-44F4E9874168\"></sequenceFlow>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_huiqiantest\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"huiqiantest\" id=\"BPMNPlane_huiqiantest\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"startEvent1\" id=\"BPMNShape_startEvent1\">\n" +
                "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"100.0\" y=\"163.0\"></omgdc:Bounds>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"task1\" id=\"BPMNShape_task1\">\n" +
                "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"165.0\" y=\"135.0\"></omgdc:Bounds>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"task2\" id=\"BPMNShape_task2\">\n" +
                "        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"320.0\" y=\"138.0\"></omgdc:Bounds>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"sid-B83EA6FD-2790-47DC-814C-44F4E9874168\" id=\"BPMNShape_sid-B83EA6FD-2790-47DC-814C-44F4E9874168\">\n" +
                "        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"465.0\" y=\"163.0\"></omgdc:Bounds>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"sid-C91335DB-6C6F-4B69-B143-880B51AD511D\" id=\"BPMNEdge_sid-C91335DB-6C6F-4B69-B143-880B51AD511D\">\n" +
                "        <omgdi:waypoint x=\"419.94999999999874\" y=\"178.45\"></omgdi:waypoint>\n" +
                "        <omgdi:waypoint x=\"465.00031676481575\" y=\"178.85586158380852\"></omgdi:waypoint>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"sid-DC648D03-6B6C-474F-BA29-C722DD1C989C\" id=\"BPMNEdge_sid-DC648D03-6B6C-474F-BA29-C722DD1C989C\">\n" +
                "        <omgdi:waypoint x=\"264.9499999999882\" y=\"175.0\"></omgdi:waypoint>\n" +
                "        <omgdi:waypoint x=\"292.5\" y=\"175.0\"></omgdi:waypoint>\n" +
                "        <omgdi:waypoint x=\"292.5\" y=\"178.0\"></omgdi:waypoint>\n" +
                "        <omgdi:waypoint x=\"319.9999999999603\" y=\"178.0\"></omgdi:waypoint>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"sid-1C65E743-B71F-4659-AAD6-471068933046\" id=\"BPMNEdge_sid-1C65E743-B71F-4659-AAD6-471068933046\">\n" +
                "        <omgdi:waypoint x=\"131.93723886834255\" y=\"178.35405257111793\"></omgdi:waypoint>\n" +
                "        <omgdi:waypoint x=\"164.99999999999997\" y=\"177.0181818181818\"></omgdi:waypoint>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>";

        Document document = DocumentHelper.parseText(a);
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

        System.out.println(document.asXML());
        String docXmlText=document.asXML();
    }


}
