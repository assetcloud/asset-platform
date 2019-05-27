package com.asset.controller;

import com.asset.utils.ExamProcModelUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程迁移实验
 * @author yby
 * @time 190522之前某一天
 */
@Controller
public class ExamTestController {

    @GetMapping("/testTest")
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


        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createExclusiveGateway(excluForkStr,excluForkStr));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1,userTaskStr + 1));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 2,userTaskStr + 2));
        process.addFlowElement(ExamProcModelUtils.createExclusiveGateway(excluJoinStr,excluJoinStr));
        process.addFlowElement(ExamProcModelUtils.createEndEvent());

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr,excluForkStr,flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluForkStr,userTaskStr +1 ,flowStr + 2,"${test>=5}"));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr +1 ,excluJoinStr ,flowStr + 3));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluForkStr,userTaskStr +2 ,flowStr + 4,"${test<5}"));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr +2 ,excluJoinStr ,flowStr + 5));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluJoinStr ,endStr ,flowStr + 6));


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
//        process.addFlowElement(ExamProcModelUtils.createStartEvent());
//        process.addFlowElement(ExamProcModelUtils.createEndEvent());
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
//
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));
//
//        int taskIndex = 2;
//        int forkIndex = 1;
//        int flowIndex = 3;
//
//        int a[] = ExamProcModelUtils.setProcBranch20(process,taskIndex,forkIndex,flowIndex);
//        int temp = a[1] - 1 ;
//        int userTaskIndex = a[0];
//        flowIndex = a[2];
//
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        userTaskIndex++;
//
//        int b[] = ExamProcModelUtils.setProcBranch10(process,userTaskIndex,5,flowIndex);
//        temp = b[1] - 1 ;
//        userTaskIndex = b[0];
//        flowIndex = b[2];
//
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 5, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        userTaskIndex++;
//
//
//        temp = userTaskIndex - 1 ;
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));
//
//
//        for(int i = 0 ; i <16 ;i++)
//        {
//            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//            userTaskIndex++;
//            temp = userTaskIndex -1 ;
//            if(userTaskIndex <= 50)
//                process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
//        }
//
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));
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
//        process.addFlowElement(ExamProcModelUtils.createStartEvent());
//
//        process.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+1, forkStr+1));
//
//        //这里在并行前面先有两个串行的
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
//        process.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+2, forkStr+2));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 2, userTaskStr + 2));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 3, userTaskStr + 3));
//        process.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+2, joinStr+2));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 4, userTaskStr + 4));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 5, userTaskStr + 5));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 10, userTaskStr + 10));
//        process.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+1, joinStr+1));
//        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 6, userTaskStr + 6));
//        process.addFlowElement(ExamProcModelUtils.createEndEvent());
//
//
//        //开始添加flow
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr, forkStr+1, flowStr + 1));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr+1, userTaskStr+1 ,flowStr + 2));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 2, flowStr + 3));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + 2, userTaskStr +2, flowStr + 4));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 2, joinStr + 2, flowStr + 5));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + 2, userTaskStr +3, flowStr + 6));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 3, joinStr + 2, flowStr + 7));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 2, joinStr + 1, flowStr + 8));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr+1, userTaskStr+4 ,flowStr + 9));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 4, userTaskStr + 10, flowStr + 10));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 10, joinStr + 1, flowStr + 16));
//
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr +6, flowStr + 11));
//        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr +6, endStr, flowStr + 12));
//
//        return process;
//
//    }
}
