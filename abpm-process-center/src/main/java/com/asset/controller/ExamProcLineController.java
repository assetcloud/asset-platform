package com.asset.controller;

import com.asset.utils.ExamProcModelUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程迁移实验
 * 完成流程实例迁移中按照执行路径去完成的实验
 * @author yby
 * @time 190522之前某一天
 */

@Controller
public class ExamProcLineController {

    Logger logger = LoggerFactory.getLogger(ExamProcLineController.class);
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    RuntimeService runtimeService = engine.getRuntimeService();
    //查询当前执行到的任务
    TaskService taskService = engine.getTaskService();
    HistoryService historyService = engine.getHistoryService();


    //这里是计算执行单条串行流程实例所花的时间
    @GetMapping("/proclineTest")
    public void createProcLine() {
        preComplete();

        BpmnModel bpmnModel = new BpmnModel();
        Process process = getProcess();
        bpmnModel.addProcess(process);

        //部署
        Deployment dep = repositoryService.createDeployment()
                .addBpmnModel("dynamic-model.bpmn", bpmnModel).name("Dynamic process deployment").deploy();

        //运行
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        logger.debug("创建流程定义成功！");
        System.out.println("创建流程定义成功！");

        createProcIns(pd);

        rollback();

        logger.debug("回滚之后流程实例执行完毕！");
        System.out.println("回滚之后流程实例执行完毕！");

    }

    //这里先把没跑完的流程实例都先跑完
    private void preComplete() {
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
        String excluCondition1 = "${test>=5}";
        String excluCondition2 = "${test<5}";

        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
        process.addFlowElement(ExamProcModelUtils.createExclusiveGateway(excluForkStr, excluForkStr));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 2, userTaskStr + 2));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 3, userTaskStr + 3));
        process.addFlowElement(ExamProcModelUtils.createExclusiveGateway(excluJoinStr, excluJoinStr));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 4, userTaskStr + 4));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 5, userTaskStr + 5));

        process.addFlowElement(ExamProcModelUtils.createEndEvent());

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr, userTaskStr + 1, flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, excluForkStr, flowStr + 2));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluForkStr, userTaskStr + 2, flowStr + 3, excluCondition1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 2, excluJoinStr, flowStr + 4));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluForkStr, userTaskStr + 3, flowStr + 5, excluCondition2));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 3, excluJoinStr, flowStr + 6));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(excluJoinStr, userTaskStr + 4, flowStr + 7));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 4, userTaskStr + 5, flowStr + 8));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 5, endStr, flowStr + 9));



        return process;
    }

    private void createProcIns(ProcessDefinition pd) {

        //初始化分支数据
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("test", 3);
        ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId(), vars);

        loop:
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

            if (tasks.size() == 0)
                break;

            //遍历，然后完成
            for (Task task : tasks) {

                if (task.getName().equals("UserTask5"))
                {
                    logger.debug("当前实例执行位置定为: {}",task.getName());
                    System.out.println("当前实例执行位置定为: "+task.getName());
                    break loop;
                }
                taskService.complete(task.getId());
            }
        }

    }

    private void rollback() {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();
        Execution curExe = null;
        List<ProcessInstance> lists = query.active().list();
        ProcessInstance processInstance = null;
        String curActivityID = "";

        for (int i = 0; i < lists.size(); i++) {
            processInstance = lists.get(i);
            ExecutionQuery newEXE = executionQuery.processInstanceId(processInstance.getProcessInstanceId());
            List<Execution> executions = newEXE.list();
            curExe = ExamProcModelUtils.getCurExecution(executions);

            curActivityID = curExe.getActivityId();
        }

        //这里我们可以判断当前执行节点相对于影响域的位置（ userTask > 还是 < ）以及如果在影响域中是否在影响域的第一个节点处
        //然后有几个情况：当前执行节点处于影响域第一个节点；处于影响域第一个节点的之后的节点
        //当处于影响域第一个节点，这里可以直接获取当前执行节点的前一个节点，那么这个位置就是我们想要回滚的位置
        //当处于影响域第一个节点的之后的节点，我们可以从最近的那个节点遍历，然后拿到我们想要的的那个节点位置

        String firstChangedID = "UserTask4";

        //这里指定影响域是userTask4····
        if (checkIsChanged(curActivityID))
        {
            logger.debug("当前实例影响域: UserTask4、UserTask5");
            System.out.println("当前实例影响域: UserTask4、UserTask5");

            //获取影响域的第一个节点前面的那个节点
            HistoricActivityInstance historicActivityInstance = ExamProcModelUtils.getBeforeChanged(historyService,processInstance.getProcessInstanceId(),firstChangedID);
            //不为空就代表找到了，准备开始回滚
            if(historicActivityInstance != null)
            {
                logger.debug("经过分析，要回滚到的节点是: {} ", historicActivityInstance.getActivityName());
                System.out.println("经过分析，要回滚到的节点是: " + historicActivityInstance.getActivityName());



                //这里是回滚，第一个参数代表当前的Execution的ID，第二个参数代表要回滚到的节点位置（这里应该是影响域前一个节点处）
                runtimeService.createChangeActivityStateBuilder().processInstanceId(processInstance.getProcessInstanceId())
                        .moveExecutionToActivityId(curExe.getId(), historicActivityInstance.getActivityId()).changeState();
            }
            else
                logger.warn("无法获取影响域前一个普通节点！");
        }

        checkRollback();

    }




    //判断当前节点是否处在影响域之内
    private boolean checkIsChanged(String curActivityID) {
        if(curActivityID.equals("UserTask4") || curActivityID.equals("UserTask5"))
            return true;
        else
            return false;
    }

    //检测回滚是不是正常
    private void checkRollback() {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> lists = query.active().list();

        for(int j = 0 ; j<lists.size() ; j++) {
            ProcessInstance instance = lists.get(j);

            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();


                if (tasks.size() == 0)
                    break;

                if ( i ==0 )
                {
                    logger.debug("经过回滚后，实例当前待执行节点为：{}",tasks.get(0).getName());
                    System.out.println("经过回滚后，实例当前待执行节点为：" + tasks.get(0).getName());
                }

                //遍历，然后完成
                for (Task task : tasks) {
                    taskService.complete(task.getId());
                }
            }
        }
    }

}
