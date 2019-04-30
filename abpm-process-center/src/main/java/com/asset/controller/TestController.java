package com.asset.controller;

import com.asset.utils.ProcessUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    public void testProc() {
        BpmnModel bpmnModel = new BpmnModel();
        Process process = getProcess();
        bpmnModel.addProcess(process);

        //初始化
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();

        //部署
        Deployment dep = repositoryService.createDeployment()
                .addBpmnModel("dynamic-model.bpmn", bpmnModel).name("Dynamic process deployment").deploy();

        //运行
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        //查询当前执行到的任务
        TaskService taskService = engine.getTaskService();

        //初始化分支数据
        Map<String,Object> vars = new HashMap<String, Object>();
        vars.put("test",3);
        ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId(),vars);

        for(int i = 0;i<50;i++)
        {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

            if(tasks.size() == 0)
                break;

            //遍历，然后完成
            for (Task task : tasks) {
                taskService.complete(task.getId());
            }
        }

        System.out.println("xucceed");


    }


    private Process getProcess() {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";
        String excluForkStr = "exclu-fork";
        String excluJoinStr = "exclu-join";


        process.addFlowElement(ProcessUtils.createStartEvent());
        process.addFlowElement(ProcessUtils.createExclusiveGateway(excluForkStr,excluForkStr));
        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 1,userTaskStr + 1));
        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 2,userTaskStr + 2));
        process.addFlowElement(ProcessUtils.createExclusiveGateway(excluJoinStr,excluJoinStr));
        process.addFlowElement(ProcessUtils.createEndEvent());

        process.addFlowElement(ProcessUtils.createSequenceFlow(startStr,excluForkStr,flowStr + 1));
        process.addFlowElement(ProcessUtils.createSequenceFlow(excluForkStr,userTaskStr +1 ,flowStr + 2,"${test>=5}"));
        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr +1 ,excluJoinStr ,flowStr + 3));

        process.addFlowElement(ProcessUtils.createSequenceFlow(excluForkStr,userTaskStr +2 ,flowStr + 4,"${test<5}"));
        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr +2 ,excluJoinStr ,flowStr + 5));

        process.addFlowElement(ProcessUtils.createSequenceFlow(excluJoinStr ,endStr ,flowStr + 6));


        return process;
    }


//    private Process getProcess() {
//        Process process = new Process();
//        process.setId("process1");
//
//        String startStr = "startEvent";
//        String endStr = "endEvent";
//        String userTaskStr = "UserTask";
//        String forkStr = "parallel-fork";
//        String joinStr = "parallel-join";
//        String flowStr = "flow";
//
//
//        process.addFlowElement(ProcessUtils.createStartEvent());
//        process.addFlowElement(ProcessUtils.createEndEvent());
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
//
//        process.addFlowElement(ProcessUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));
//
//        int taskIndex = 2;
//        int forkIndex = 1;
//        int flowIndex = 3;
//
//        int a[] = ProcessUtils.setProcBranch20(process,taskIndex,forkIndex,flowIndex);
//        int temp = a[1] - 1 ;
//        int userTaskIndex = a[0];
//        flowIndex = a[2];
//
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        userTaskIndex++;
//
//        int b[] = ProcessUtils.setProcBranch10(process,userTaskIndex,5,flowIndex);
//        temp = b[1] - 1 ;
//        userTaskIndex = b[0];
//        flowIndex = b[2];
//
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(joinStr + 5, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        userTaskIndex++;
//
//
//        temp = userTaskIndex - 1 ;
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));
//
//
//        for(int i = 0 ; i <16 ;i++)
//        {
//            process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//            userTaskIndex++;
//            temp = userTaskIndex -1 ;
//            if(userTaskIndex <= 50)
//                process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        }
//
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));
//
//        return process;
//    }




//    private Process getProcess() {
//        Process process = new Process();
//        process.setId("process1");
//
//        String startStr = "startEvent";
//        String endStr = "endEvent";
//        String userTaskStr = "UserTask";
//        String forkStr = "parallel-fork";
//        String joinStr = "parallel-join";
//        String flowStr = "flow";
//
//        process.addFlowElement(ProcessUtils.createStartEvent());
//
//        process.addFlowElement(ProcessUtils.createParallelGateway(forkStr+1, forkStr+1));
//
//        //这里在并行前面先有两个串行的
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
//        process.addFlowElement(ProcessUtils.createParallelGateway(forkStr+2, forkStr+2));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 2, userTaskStr + 2));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 3, userTaskStr + 3));
//        process.addFlowElement(ProcessUtils.createParallelGateway(joinStr+2, joinStr+2));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 4, userTaskStr + 4));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 5, userTaskStr + 5));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 10, userTaskStr + 10));
//        process.addFlowElement(ProcessUtils.createParallelGateway(joinStr+1, joinStr+1));
//        process.addFlowElement(ProcessUtils.createUserTask(userTaskStr + 6, userTaskStr + 6));
//        process.addFlowElement(ProcessUtils.createEndEvent());
//
//
//        //开始添加flow
//        process.addFlowElement(ProcessUtils.createSequenceFlow(startStr, forkStr+1, flowStr + 1));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(forkStr+1, userTaskStr+1 ,flowStr + 2));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 1, forkStr + 2, flowStr + 3));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(forkStr + 2, userTaskStr +2, flowStr + 4));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 2, joinStr + 2, flowStr + 5));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(forkStr + 2, userTaskStr +3, flowStr + 6));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 3, joinStr + 2, flowStr + 7));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(joinStr + 2, joinStr + 1, flowStr + 8));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(forkStr+1, userTaskStr+4 ,flowStr + 9));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 4, userTaskStr + 10, flowStr + 10));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr + 10, joinStr + 1, flowStr + 16));
//
//        process.addFlowElement(ProcessUtils.createSequenceFlow(joinStr + 1, userTaskStr +6, flowStr + 11));
//        process.addFlowElement(ProcessUtils.createSequenceFlow(userTaskStr +6, endStr, flowStr + 12));
//
//        return process;
//
//    }
}
