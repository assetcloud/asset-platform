package com.asset.controller;

import com.asset.base.BaseController;
import com.asset.dao.ExamCopyProcMapper;
import com.asset.utils.ExamProcModelUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.rest.app.ModelResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 批量完成流程迁移操作
 * @author yby
 * @time 190522之前某一天
 */
@Controller
public class ExamDozenController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelResource.class);

    @Autowired
    ExamCopyProcMapper mapper;

    //这里是计算执行单条串行流程实例所花的时间
    @GetMapping("/copyTest")
    public void createProcModel1(@RequestParam Integer times) {
        copyProcNode(times);
    }


    /**
     *
     * @param nodesNum 表示这个流程中应该总共有几个节点
     * @param percents 表示并行结构的节点有几个
     */
    @GetMapping("/parallelTest")
    public void createProcModel(@RequestParam(value = "num") Integer nodesNum,
                                @RequestParam(value = "per") Integer percents) {
        branchProc(nodesNum,percents);
    }


    /**
     * 对并行分支复杂度、总结点数进行配置的方法
     * @param nodesNum
     * @param percents
     */
    private void branchProc(Integer nodesNum,double percents) {
        double per = percents/100;
        int parallelNum = (int) (nodesNum * per);
        int normalNum = nodesNum - parallelNum;
        //在最后运行的时候，需要遍历的次数
        int times = normalNum + 1;


        //实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        process.setId("process1");
        bpmnModel.addProcess(process);

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";

        int index = 1;

        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr, forkStr));
        for( ; index <= parallelNum;index++)
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + index, userTaskStr + index));

        process.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr, joinStr));

        for( ; index <= nodesNum;index++)
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + index, userTaskStr + index));

        process.addFlowElement(ExamProcModelUtils.createEndEvent());

        //开始添加flow
        int flowIndex = 2;
        index = 1;

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr, forkStr, flowStr + 1));

        for( ; index <= parallelNum;index++)
        {
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr, userTaskStr + index, flowStr + flowIndex));
            flowIndex++;
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + index, joinStr, flowStr + flowIndex));
            flowIndex++;
        }
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr, userTaskStr+index, flowStr + flowIndex));
        flowIndex++;
        for( ; index < nodesNum;index++)
        {
            int t = index + 1;
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr+index, userTaskStr+t, flowStr + flowIndex));
            flowIndex++;
        }

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr+index, endStr, flowStr + flowIndex));


        executeParallel(bpmnModel,times,1);

    }



    //执行并行结构的
    private void executeParallel(BpmnModel bpmnModel,int times,int testTime) {
        double allTime = 0;

        for(int ff = 0; ff<testTime;ff++)
        {
            //初始化
            ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
            RepositoryService repositoryService = engine.getRepositoryService();
            RuntimeService runtimeService = engine.getRuntimeService();

            //部署
            Deployment dep = repositoryService.createDeployment()
                    .addBpmnModel("dynamic-model.bpmn", bpmnModel).name("Dynamic process deployment").deploy();

            //运行
            ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
            TaskService taskService = engine.getTaskService();
            ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());

            System.out.println(instance.getId());


            for(int i = 0;i<times;i++)
            {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                //遍历，然后完成
                for (Task task : tasks) {
                    taskService.complete(task.getId());
//                System.out.println(i+"  "+task.getName());
                }
            }

            allTime = allTime + calTime(instance.getId());
        }

        System.out.println("每组测"+testTime+"次，平均值："+allTime/testTime);

    }




    //从数据库中查运行的时间
    private long calTime(String instanceID) {
        String startTime = mapper.getStartTime(instanceID);
        String endTime = mapper.getEndTime(instanceID);
        System.out.println(startTime);
        System.out.println(endTime);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date start = df.parse(startTime);
            Date end = df.parse(endTime);
            long diff = end.getTime() - start.getTime();//这样得到的差值是毫秒级别
//            long days = diff / (1000 * 60 * 60 * 24);

//            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
//            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);

            System.out.println("耗时:" + diff + "毫秒");
            return diff;
        } catch (Exception e) {
        }

        return 0;

    }


    /**
     * @param times 添加多少个节点
     */
    public void copyProcNode(int times) {

        CreateNewModel(times);
    }

    private void executeProc() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        RuntimeService runtimeService = engine.getRuntimeService();
        Deployment dep = repositoryService.createDeployment().addClasspathResource("BPMN2.xml").deploy();
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        TaskService taskService = engine.getTaskService();


        ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

        //遍历，然后完成
        for (Task task : tasks)
            taskService.complete(task.getId());

    }

    private void CreateNewModel(int times) {
        //实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
        //开始节点的属性
        StartEvent startEvent = new StartEvent();
        String startEventStr = "startEvent";
        startEvent.setId(startEventStr);
        startEvent.setName(startEventStr);

        //结束节点属性
        EndEvent endEvent = new EndEvent();
        String endEventStr = "endEvent";
        endEvent.setId(endEventStr);
        endEvent.setName(endEventStr);

        //第一个UserTask节点
        UserTask firstUserTask = new UserTask();
        String firstUserTaskStr = "userTask1";
        firstUserTask.setId(firstUserTaskStr);
        firstUserTask.setName(firstUserTaskStr);

        Process process = new Process();
        process.setId("process1");

        //连线信息,startEvent到第一个UserTask肯定不变
        List<SequenceFlow> flows1 = new ArrayList<SequenceFlow>();
        List<SequenceFlow> flows2 = new ArrayList<SequenceFlow>();
        SequenceFlow s1 = new SequenceFlow();
        String startToFirstStr = "flow1";
        s1.setId(startToFirstStr);
        s1.setName(startToFirstStr);
        s1.setSourceRef(startEventStr);
        s1.setTargetRef(firstUserTaskStr);
        flows1.add(s1);

        startEvent.setOutgoingFlows(flows1);
        firstUserTask.setIncomingFlows(flows1);

        process.addFlowElement(startEvent);
        process.addFlowElement(s1);


        //开始——》userTask1——》结束
        if (times == 1) {
            SequenceFlow s2 = new SequenceFlow();
            String flowStr = "flow2";
            s2.setId(flowStr);
            s2.setName(flowStr);
            s2.setSourceRef("userTask1");
            s2.setTargetRef("endEvent");
            flows2.add(s2);

            firstUserTask.setOutgoingFlows(flows2);
            endEvent.setIncomingFlows(flows2);


            process.addFlowElement(firstUserTask);
            process.addFlowElement(s2);
            process.addFlowElement(endEvent);
        }

        UserTask tempTask = firstUserTask;

        //如果中间有多个userTask
        for (int i = 2; i <= times; i++) {
            flows1.clear();
            flows2.clear();

            int t = i - 1;
            int p = i + 1;

            //如果还要添加节点，此时
            SequenceFlow s2 = new SequenceFlow();
            String flowStr = "flow" + i;
            s2.setId(flowStr);
            s2.setName(flowStr);
            s2.setSourceRef("userTask" + t);
            s2.setTargetRef("userTask" + i);

            flows2.add(s2);

            //创建当前UserTask
            UserTask curTask = new UserTask();
            String curUserTaskStr = "userTask" + i;
            curTask.setId(curUserTaskStr);
            curTask.setName(curUserTaskStr);

            //对创建的flow进行绑定
            tempTask.setOutgoingFlows(flows2);
            curTask.setIncomingFlows(flows2);


            //Process对象
            process.addFlowElement(tempTask);
            process.addFlowElement(s2);


            tempTask = curTask;

        }

        //等于1的情况，都已经被写在上面了
        if (times > 1) {
            SequenceFlow toEndSquence = new SequenceFlow();
            int temp = times + 1;
            String flowStr = "flow" + temp;
            toEndSquence.setId(flowStr);
            toEndSquence.setName(flowStr);
            toEndSquence.setSourceRef(tempTask.getName());
            toEndSquence.setTargetRef(endEventStr);

            flows2.clear();
            flows2.add(toEndSquence);

            endEvent.setIncomingFlows(flows2);


            process.addFlowElement(tempTask);
            process.addFlowElement(toEndSquence);
            process.addFlowElement(endEvent);
        }


//        bpmnModel.addProcess(process);
//
//        BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
//
//        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
//
//        String bytes=new String(convertToXML);


        //初始化
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();

        RuntimeService runtimeService = engine.getRuntimeService();

        //部署
        Deployment dep = repositoryService.createDeployment()
                .addBpmnModel("dynamic-model.bpmn", bpmnModel).name("Dynamic process deployment").deploy();


        //运行
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        TaskService taskService = engine.getTaskService();

        ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());
        System.out.println(instance.getId());


        while (times != 0) {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

            //遍历，然后完成
            for (Task task : tasks) {
                taskService.complete(task.getId());
            }

            times--;
        }


        calTime(instance.getId());


//        //存储xml   //存储这里有BUG，这里也不强求需要存储
//        InputStream processBpmn = repositoryService
//                .getResourceAsStream(dep.getId(), "dynamic-model.bpmn");
//
//        try {
//            FileUtils.copyInputStreamToFile(processBpmn, new File("target/process.bpmn20.xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }


    /**
     * 这里构造我们要放在model_editor_json字段中的内容
     *
     * @param nodeID
     * @param flowID
     */
    public void createNewNode(String nodeID, String flowID, int i) {

        BpmnModel model = null;
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();

        //json
        String jsonOld = mapper.getProcJson("modelBound");

        System.out.println("json:" + jsonOld);

        String jsonToXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\t<modelId>7b54a30f-5ab7-11e9-ac0c-2e15a8856301</modelId>\n" +
                "\t<bounds>\n" +
                "\t\t<lowerRight>\n" +
                "\t\t\t<x>1200</x>\n" +
                "\t\t\t<y>1050</y>\n" +
                "\t\t</lowerRight>\n" +
                "\t\t<upperLeft>\n" +
                "\t\t\t<x>0</x>\n" +
                "\t\t\t<y>0</y>\n" +
                "\t\t</upperLeft>\n" +
                "\t</bounds>\n" +
                "\t<properties>\n" +
                "\t\t<process_id>new</process_id>\n" +
                "\t\t<name>new</name>\n" +
                "\t\t<documentation>new</documentation>\n" +
                "\t\t<process_author></process_author>\n" +
                "\t\t<process_version></process_version>\n" +
                "\t\t<process_namespace>http://www.flowable.org/processdef</process_namespace>\n" +
                "\t\t<process_historylevel></process_historylevel>\n" +
                "\t\t<isexecutable>true</isexecutable>\n" +
                "\t\t<dataproperties></dataproperties>\n" +
                "\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t<eventlisteners></eventlisteners>\n" +
                "\t\t<signaldefinitions></signaldefinitions>\n" +
                "\t\t<messagedefinitions></messagedefinitions>\n" +
                "\t\t<process_potentialstarteruser></process_potentialstarteruser>\n" +
                "\t\t<process_potentialstartergroup></process_potentialstartergroup>\n" +
                "\t\t<iseagerexecutionfetch>false</iseagerexecutionfetch>\n" +
                "\t</properties>\n" +
                "\t<childShapes>\n" +
                "\t\t<resourceId>startEvent1</resourceId>\n" +
                "\t\t<properties>\n" +
                "\t\t\t<overrideid>start</overrideid>\n" +
                "\t\t\t<name></name>\n" +
                "\t\t\t<documentation></documentation>\n" +
                "\t\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t\t<initiator></initiator>\n" +
                "\t\t\t<formkeydefinition></formkeydefinition>\n" +
                "\t\t\t<formreference></formreference>\n" +
                "\t\t\t<formproperties></formproperties>\n" +
                "\t\t</properties>\n" +
                "\t\t<stencil>\n" +
                "\t\t\t<id>StartNoneEvent</id>\n" +
                "\t\t</stencil>\n" +
                "\t\t<outgoing>\n" +
                "\t\t\t<resourceId>sid-26FE35A9-7DE5-4203-B755-83032F9E03BB</resourceId>\n" +
                "\t\t</outgoing>\n" +
                "\t\t<bounds>\n" +
                "\t\t\t<lowerRight>\n" +
                "\t\t\t\t<x>130</x>\n" +
                "\t\t\t\t<y>193</y>\n" +
                "\t\t\t</lowerRight>\n" +
                "\t\t\t<upperLeft>\n" +
                "\t\t\t\t<x>100</x>\n" +
                "\t\t\t\t<y>163</y>\n" +
                "\t\t\t</upperLeft>\n" +
                "\t\t</bounds>\n" +
                "\t</childShapes>\n" +
                "\t<childShapes>\n" +
                "\t\t<resourceId>sid-F0C9158B-DB01-440E-B8D8-A8E1DF4BFD69</resourceId>\n" +
                "\t\t<properties>\n" +
                "\t\t\t<overrideid>user1</overrideid>\n" +
                "\t\t\t<name></name>\n" +
                "\t\t\t<documentation></documentation>\n" +
                "\t\t\t<asynchronousdefinition>false</asynchronousdefinition>\n" +
                "\t\t\t<exclusivedefinition>false</exclusivedefinition>\n" +
                "\t\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t\t<multiinstance_type>None</multiinstance_type>\n" +
                "\t\t\t<multiinstance_cardinality></multiinstance_cardinality>\n" +
                "\t\t\t<multiinstance_collection></multiinstance_collection>\n" +
                "\t\t\t<multiinstance_variable></multiinstance_variable>\n" +
                "\t\t\t<multiinstance_condition></multiinstance_condition>\n" +
                "\t\t\t<isforcompensation>false</isforcompensation>\n" +
                "\t\t\t<usertaskassignment>\n" +
                "\t\t\t\t<assignment>\n" +
                "\t\t\t\t\t<type>idm</type>\n" +
                "\t\t\t\t\t<initiatorCanCompleteTask>true</initiatorCanCompleteTask>\n" +
                "\t\t\t\t\t<idm>\n" +
                "\t\t\t\t\t\t<type>initiator</type>\n" +
                "\t\t\t\t\t</idm>\n" +
                "\t\t\t\t</assignment>\n" +
                "\t\t\t</usertaskassignment>\n" +
                "\t\t\t<formkeydefinition></formkeydefinition>\n" +
                "\t\t\t<formreference></formreference>\n" +
                "\t\t\t<duedatedefinition></duedatedefinition>\n" +
                "\t\t\t<prioritydefinition></prioritydefinition>\n" +
                "\t\t\t<formproperties></formproperties>\n" +
                "\t\t\t<tasklisteners></tasklisteners>\n" +
                "\t\t\t<skipexpression></skipexpression>\n" +
                "\t\t\t<categorydefinition></categorydefinition>\n" +
                "\t\t</properties>\n" +
                "\t\t<stencil>\n" +
                "\t\t\t<id>UserTask</id>\n" +
                "\t\t</stencil>\n" +
                "\t\t<outgoing>\n" +
                "\t\t\t<resourceId>sid-B261DFFE-9799-4D4C-8844-B40C7933880F</resourceId>\n" +
                "\t\t</outgoing>\n" +
                "\t\t<bounds>\n" +
                "\t\t\t<lowerRight>\n" +
                "\t\t\t\t<x>460</x>\n" +
                "\t\t\t\t<y>218</y>\n" +
                "\t\t\t</lowerRight>\n" +
                "\t\t\t<upperLeft>\n" +
                "\t\t\t\t<x>360</x>\n" +
                "\t\t\t\t<y>138</y>\n" +
                "\t\t\t</upperLeft>\n" +
                "\t\t</bounds>\n" +
                "\t</childShapes>\n" +
                "\t<childShapes>\n" +
                "\t\t<resourceId>sid-C70A90B3-C4E9-4137-88B7-3C4D4C534B9F</resourceId>\n" +
                "\t\t<properties>\n" +
                "\t\t\t<overrideid>end</overrideid>\n" +
                "\t\t\t<name></name>\n" +
                "\t\t\t<documentation></documentation>\n" +
                "\t\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t</properties>\n" +
                "\t\t<stencil>\n" +
                "\t\t\t<id>ThrowNoneEvent</id>\n" +
                "\t\t</stencil>\n" +
                "\t\t<bounds>\n" +
                "\t\t\t<lowerRight>\n" +
                "\t\t\t\t<x>690</x>\n" +
                "\t\t\t\t<y>193</y>\n" +
                "\t\t\t</lowerRight>\n" +
                "\t\t\t<upperLeft>\n" +
                "\t\t\t\t<x>660</x>\n" +
                "\t\t\t\t<y>163</y>\n" +
                "\t\t\t</upperLeft>\n" +
                "\t\t</bounds>\n" +
                "\t\t<dockers>\n" +
                "\t\t\t<x>675</x>\n" +
                "\t\t\t<y>178</y>\n" +
                "\t\t</dockers>\n" +
                "\t</childShapes>\n" +
                "\t<childShapes>\n" +
                "\t\t<resourceId>sid-26FE35A9-7DE5-4203-B755-83032F9E03BB</resourceId>\n" +
                "\t\t<properties>\n" +
                "\t\t\t<overrideid>flow1</overrideid>\n" +
                "\t\t\t<name></name>\n" +
                "\t\t\t<documentation></documentation>\n" +
                "\t\t\t<conditionsequenceflow></conditionsequenceflow>\n" +
                "\t\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t\t<defaultflow>false</defaultflow>\n" +
                "\t\t\t<skipexpression></skipexpression>\n" +
                "\t\t</properties>\n" +
                "\t\t<stencil>\n" +
                "\t\t\t<id>SequenceFlow</id>\n" +
                "\t\t</stencil>\n" +
                "\t\t<outgoing>\n" +
                "\t\t\t<resourceId>sid-F0C9158B-DB01-440E-B8D8-A8E1DF4BFD69</resourceId>\n" +
                "\t\t</outgoing>\n" +
                "\t\t<bounds>\n" +
                "\t\t\t<lowerRight>\n" +
                "\t\t\t\t<x>359.44921875</x>\n" +
                "\t\t\t\t<y>178</y>\n" +
                "\t\t\t</lowerRight>\n" +
                "\t\t\t<upperLeft>\n" +
                "\t\t\t\t<x>130.98046875</x>\n" +
                "\t\t\t\t<y>178</y>\n" +
                "\t\t\t</upperLeft>\n" +
                "\t\t</bounds>\n" +
                "\t\t<dockers>\n" +
                "\t\t\t<x>15</x>\n" +
                "\t\t\t<y>15</y>\n" +
                "\t\t</dockers>\n" +
                "\t\t<dockers>\n" +
                "\t\t\t<x>50</x>\n" +
                "\t\t\t<y>40</y>\n" +
                "\t\t</dockers>\n" +
                "\t\t<target>\n" +
                "\t\t\t<resourceId>sid-F0C9158B-DB01-440E-B8D8-A8E1DF4BFD69</resourceId>\n" +
                "\t\t</target>\n" +
                "\t</childShapes>\n" +
                "\t<childShapes>\n" +
                "\t\t<resourceId>sid-B261DFFE-9799-4D4C-8844-B40C7933880F</resourceId>\n" +
                "\t\t<properties>\n" +
                "\t\t\t<overrideid>flow2</overrideid>\n" +
                "\t\t\t<name></name>\n" +
                "\t\t\t<documentation></documentation>\n" +
                "\t\t\t<conditionsequenceflow></conditionsequenceflow>\n" +
                "\t\t\t<executionlisteners></executionlisteners>\n" +
                "\t\t\t<defaultflow>false</defaultflow>\n" +
                "\t\t\t<skipexpression></skipexpression>\n" +
                "\t\t</properties>\n" +
                "\t\t<stencil>\n" +
                "\t\t\t<id>SequenceFlow</id>\n" +
                "\t\t</stencil>\n" +
                "\t\t<outgoing>\n" +
                "\t\t\t<resourceId>sid-C70A90B3-C4E9-4137-88B7-3C4D4C534B9F</resourceId>\n" +
                "\t\t</outgoing>\n" +
                "\t\t<bounds>\n" +
                "\t\t\t<lowerRight>\n" +
                "\t\t\t\t<x>659.5078125</x>\n" +
                "\t\t\t\t<y>178</y>\n" +
                "\t\t\t</lowerRight>\n" +
                "\t\t\t<upperLeft>\n" +
                "\t\t\t\t<x>460.6875</x>\n" +
                "\t\t\t\t<y>178</y>\n" +
                "\t\t\t</upperLeft>\n" +
                "\t\t</bounds>\n" +
                "\t\t<dockers>\n" +
                "\t\t\t<x>50</x>\n" +
                "\t\t\t<y>40</y>\n" +
                "\t\t</dockers>\n" +
                "\t\t<dockers>\n" +
                "\t\t\t<x>15</x>\n" +
                "\t\t\t<y>15</y>\n" +
                "\t\t</dockers>\n" +
                "\t\t<target>\n" +
                "\t\t\t<resourceId>sid-C70A90B3-C4E9-4137-88B7-3C4D4C534B9F</resourceId>\n" +
                "\t\t</target>\n" +
                "\t</childShapes>\n" +
                "\t<stencil>\n" +
                "\t\t<id>BPMNDiagram</id>\n" +
                "\t</stencil>\n" +
                "\t<stencilset>\n" +
                "\t\t<namespace>http://b3mn.org/stencilset/bpmn2.0#</namespace>\n" +
                "\t\t<url>../editor/stencilsets/bpmn2.0/bpmn2.0.json</url>\n" +
                "\t</stencilset>\n" +
                "\t\n";
        XMLInputFactory factory = XMLInputFactory.newFactory();
        InputStream inputStream = new ByteArrayInputStream(jsonToXml.getBytes());

        XMLStreamReader reader = null;

        try {
            reader = factory.createXMLStreamReader(inputStream);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


        //获取Model
        model = bpmnXMLConverter.convertToBpmnModel(reader);








   /*
        //拆分,插入我们要的内容
        StringBuilder stringBuilder = new StringBuilder(jsonOld);
        //childShapes":[ 总共有14个字符，index获得的是child前面的那个下标
        int index = stringBuilder.indexOf("childShapes\":[")+14;

        String insertInfoNode = "";
        String insertInfoFlow = "";

        stringBuilder.insert(index,insertInfoNode+insertInfoFlow);

        mapper.updateProcJson(stringBuilder.toString(),"modelBound");*/
    }


}
