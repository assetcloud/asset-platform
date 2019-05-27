package com.asset.controller;

import com.asset.dao.ExamCopyProcMapper;
import com.asset.utils.ExamProcModelUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ExecutionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.ui.modeler.rest.app.ModelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


/**
 * 流程迁移实验
 * @author yby
 * @time 190522阡陌一天
 */
@Controller
public class ExamRollbackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelResource.class);
    @Autowired
    ExamCopyProcMapper mapper;

    //初始化
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    RuntimeService runtimeService = engine.getRuntimeService();
    TaskService taskService = engine.getTaskService();


    /**
     * 这边完成迁移流程与普通流程执行完剩下来任务时间之间对比的实验
     *
     * @param nodesNum  这里值不能低于50
     * @param percents
     */
    @GetMapping("/rollTest")
    public void rollbackProcModel(@RequestParam(value = "num") Integer nodesNum,
                                  @RequestParam(value = "per") double percents) {
        preComplete();
        rollbackProc(nodesNum, percents);
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


    private void rollbackProc(Integer nodesNum, double percents) {
        double per = percents / 100;
        int parallelNum = (int) (nodesNum * per);
        int normalNum = nodesNum - parallelNum;

        int startForkTaskIndex = 0;
        int endJoinFirstTaskIndex = 0;

        //在最后运行的时候，需要遍历的次数
        int execLineNum = normalNum + 1;

        //相同的model
        BpmnModel bpmnModel = new BpmnModel();
//        BpmnModel bpmnModel2 = new BpmnModel();

        Process process = null;

//        if(nodesNum != 50) {
            process = getProcess(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);
//        }
//        else {
//            if(percents == 20)
//            {
//                process = getProcess10(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);
//            }
//            else if(percents == 40)
//            {
//                process = getProcess20(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);
//            }
//            else if(percents == 60)
//            {
//                process = getProcess30(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);
//            }
//            else if(percents == 80)
//            {
//                process = getProcess40(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);
//            }
//        }


//        Process process2 = getProcess(parallelNum, normalNum, nodesNum, startForkTaskIndex, endJoinFirstTaskIndex);

        //添加
        bpmnModel.addProcess(process);
//        bpmnModel2.addProcess(process2);

        //创建，然后先跑只单纯执行节点的流程实例,这个时间应当小
//        createInstances(bpmnModel2, endJoinFirstTaskIndex, nodesNum);
//        double executeTime = executeNormalInstances(execLineNum);

        //创建，接着跑完成迁移工作（分情况处理），完成迁移工作之后立马把这个流程运行完，然后记录迁移+跑完流程的时间，这个时间应当大
        createInstances(bpmnModel, endJoinFirstTaskIndex, nodesNum);
        double[] times = rollbackInstances(endJoinFirstTaskIndex, execLineNum,nodesNum,parallelNum);

        //计算差值
        double cha = times[0] - times[1] ;
//        if(cha<0)
//            cha = -1*cha;

        //输出结果
        System.out.println("带迁移执行时间："+times[0]+"只有执行的执行时间："+times[1]+" 差值："+cha);
    }

    //这里执行的是model2的相关的流程实例，直接执行就好
    private double executeNormalInstances(int execLineNum) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> lists = query.active().list();

        //遍历所有流程实例
        double all = 0;

        for (int i = 0; i < lists.size(); i++) {
            ProcessInstance processInstance = lists.get(i);
            String insID = processInstance.getProcessInstanceId();
            long time1 = System.currentTimeMillis();
            execute(processInstance, execLineNum);
            long time2 = System.currentTimeMillis();

//            all += ExamProcModelUtils.getDiff(mapper.getStartTime(insID),mapper.getEndTime(insID));
            all += time2 - time1 ;
        }

        return all;
    }

    //3.1、遍历之前创建的流程实例,完成迁移+执行，返回的是数组，[0]表示的是带迁移+执行的总时间，[1]是只有执行的时间
    private double[] rollbackInstances(int endJoinFirstTaskIndex, int execLineNum, int nodesNum, int parallelNum) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();

        List<ProcessInstance> lists = query.active().list();


        double times[] = new double[2];
        //这边计算的是执行所有流程实例的执行时间
        double execTime = 0;


        //遍历所有流程实例,完成迁移+执行的步骤
        //从这里开始计时
        long time1 = System.currentTimeMillis();

        //2、根据parallelNum进行处理
//        changeProcDef(nodesNum,parallelNum);

        //3、遍历流程实例，然后对当前遍历到的流程实例，判断它们当前运行到的activitiID
        for (int i = 0; i < lists.size(); i++)
        {
            ProcessInstance processInstance = lists.get(i);
            ExecutionQuery newEXE = executionQuery.processInstanceId(processInstance.getProcessInstanceId());

            List<Execution> executions = newEXE.list();
            Execution oldProcExe = null;

            //这里之所以要搞一个execution的list是因为实际获取的时候，是一个父Execution在executions[0]的位置，
            // 另一个在[1]的位置，在[1]位置上的execution才是我们要找的
            for (int j = 0; j < executions.size(); j++) {
                oldProcExe = executions.get(j);
                if (oldProcExe.getActivityId() == null)
                    continue;
                else
                    break;
            }

            //这边获取splitNum，后面不一定要用到，只有在要迁移和回滚的地方用到
            String curActivityID = oldProcExe.getActivityId();
            //分割，得到最后的那个userTask数字
            //然后从1遍历，会因为nodesNum的增加，而增加遍历时间
            String[] splits = curActivityID.split("k");
            int splitNum = 0;

            if(splits.length==2)
                splitNum = Integer.parseInt(splits[1]);



            //接下来需要判断cur和rollback相对位置，然后决定是1、迁移 2、迁移加回滚 3、不可逆，直接走
            //  迁移：     的操作是当前运行实例的PRO_DEF_ID需要更新为最新的版本，这个可以在act_re_prodef表中找，然后找到最新的那个版本
            //获取它的ID，就是最新版本的DEF_ID，这边好像没有直接修改的方法，需要自己进数据库进行修改
            //注意这里传进去的是是运行在旧的流程定义上的processInstance
            //迁移到最新版本
            if (curActivityID.compareTo("userTask3") < 0) {
//                procChangeService.migrateProc(oldProcInstance, oldProcExe);
                considerNodesNum(nodesNum,processInstance.getProcessInstanceId());
                considerParallelNum(parallelNum,processInstance.getProcessInstanceId());

                mapper.setDefinition(processInstance.getProcessInstanceId());
                long time3 = System.currentTimeMillis();
                execute(processInstance, execLineNum);
                long time4 = System.currentTimeMillis();

                execTime += time4 - time3 ;
            }

            // 迁移+回滚
            else if (curActivityID.compareTo("userTask3") == 0 ||
                    curActivityID.compareTo("userTask" + endJoinFirstTaskIndex) == 0) {
//                procChangeService.migrateProc(oldProcInstance, oldProcExe);
                considerNodesNum(nodesNum,processInstance.getProcessInstanceId());
                considerParallelNum(parallelNum,processInstance.getProcessInstanceId());

                mapper.setDefinition(processInstance.getProcessInstanceId());
                runtimeService.createChangeActivityStateBuilder().processInstanceId(processInstance.getProcessInstanceId())
                        .moveExecutionToActivityId(oldProcExe.getId(), "userTask3").changeState();
//                LOGGER.info("流程 {} 回滚完成", processInstance.getProcessInstanceId());
                long time3 = System.currentTimeMillis();

                execute(processInstance, execLineNum);
                long time4 = System.currentTimeMillis();

                execTime += time4 - time3 ;

            }
            // 直接运行
            else
            {
                long time3 = System.currentTimeMillis();

                execute(processInstance, execLineNum);
                long time4 = System.currentTimeMillis();

                execTime += time4 - time3 ;
            }
        }

        long time2 = System.currentTimeMillis();
        times[0] = time2 - time1;
        times[1] = execTime ;

        return times;
    }

    //模拟遍历到当前节点的操作
    private void considerNodesNum(int nodesNum,String processInstanceID ) {
        for(int i = 0;i < nodesNum/2;i++ )
        {
            mapper.setDefinition(processInstanceID);
            mapper.setDefinition(processInstanceID);
        }
    }

    //模拟遍历到当前节点的操作
    private void considerParallelNum(int parallelNum,String processInstanceID) {
        for(int i = 0;i < parallelNum/2;i++ )
        {
            mapper.setDefinition(processInstanceID);
            mapper.setDefinition(processInstanceID);
        }
    }




    //0、创建流程定义
    //这里的流程是start-->串行 user1,user2-->并行 user3····userN-->串行userN+1···userNodesNum-->end
    private Process getProcess(Integer parallelNum, Integer normalNum, Integer nodesNum,
                               Integer startForkTaskIndex, Integer endJoinFirstTaskIndex) {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";

        int index = 1;

        process.addFlowElement(ExamProcModelUtils.createStartEvent());

        //这里在并行前面先有两个串行的
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 2, userTaskStr + 2));

        process.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr, forkStr));

        index = 3;
        startForkTaskIndex = index;
        for (; index <= parallelNum + 2; index++)
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + index, userTaskStr + index));

        process.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr, joinStr));

        //是从join出来之后第一个串行的UserTask的ID
        endJoinFirstTaskIndex = index;


        for (; index <= nodesNum; index++)
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + index, userTaskStr + index));

        process.addFlowElement(ExamProcModelUtils.createEndEvent());

        //开始添加flow
        int flowIndex = 4;
        index = 1;

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr, userTaskStr + 1, flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, userTaskStr + 2, flowStr + 2));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 2, forkStr, flowStr + 3));

        index = 3;

        for (; index <= parallelNum + 2; index++) {
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr, userTaskStr + index, flowStr + flowIndex));
            flowIndex++;
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + index, joinStr, flowStr + flowIndex));
            flowIndex++;
        }
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr, userTaskStr + index, flowStr + flowIndex));
        flowIndex++;
        for (; index < nodesNum; index++) {
            int t1 = index + 1;
            process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + index, userTaskStr + t1, flowStr + flowIndex));
            flowIndex++;
        }

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + index, endStr, flowStr + flowIndex));

        return process;
    }

    //这里的流程定义跟上面的不一样，50,20%, 10个节点,其余40个串行（前1，后39）
    //这里的流程是start-->串行 user1,user2-->并行 user3····userN-->串行userN+1···userNodesNum-->end
    private Process getProcess10(Integer parallelNum, Integer normalNum, Integer nodesNum,
                               Integer startForkTaskIndex, Integer endJoinFirstTaskIndex) {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";


        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createEndEvent());
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));

        int taskIndex = 2;
        int forkIndex = 1;
        int flowIndex = 3;
        int a[] = ExamProcModelUtils.setProcBranch10(process,taskIndex,forkIndex,flowIndex);

        int temp = a[1] - 1 ;
        int userTaskIndex = a[0];
        flowIndex = a[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;

        temp = userTaskIndex - 1 ;
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));


        for(int i = 0 ; i <37 ;i++)
        {
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
            userTaskIndex++;
            temp = userTaskIndex -1 ;
            if(userTaskIndex <= 50)
                process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        }

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));

        return process;
    }

    //这里的流程定义跟上面的不一样，50,40%, 20个节点,串行30个（前1后29）
    //这里的流程是start-->串行 user1,user2-->并行 user3····userN-->串行userN+1···userNodesNum-->end
    private Process getProcess20(Integer parallelNum, Integer normalNum, Integer nodesNum,
                                 Integer startForkTaskIndex, Integer endJoinFirstTaskIndex) {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";


        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createEndEvent());
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));

        int taskIndex = 2;
        int forkIndex = 1;
        int flowIndex = 3;
        int a[] = ExamProcModelUtils.setProcBranch20(process,taskIndex,forkIndex,flowIndex);

        int temp = a[1] - 1 ;
        int userTaskIndex = a[0];
        flowIndex = a[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;

        temp = userTaskIndex - 1 ;
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));


        for(int i = 0 ; i <27 ;i++)
        {
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
            userTaskIndex++;
            temp = userTaskIndex -1 ;
            if(userTaskIndex <= 50)
                process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        }

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));

        return process;
    }


    //这里的流程定义跟上面的不一样，50,60%, 30个节点,串行20个（前1后19）
    private Process getProcess30(Integer parallelNum, Integer normalNum, Integer nodesNum,
                                 Integer startForkTaskIndex, Integer endJoinFirstTaskIndex) {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";


        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createEndEvent());
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));

        int taskIndex = 2;
        int forkIndex = 1;
        int flowIndex = 3;

        int a[] = ExamProcModelUtils.setProcBranch20(process,taskIndex,forkIndex,flowIndex);
        int temp = a[1] - 1 ;
        int userTaskIndex = a[0];
        flowIndex = a[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;

        int b[] = ExamProcModelUtils.setProcBranch10(process,userTaskIndex,5,flowIndex);
        temp = b[1] - 1 ;
        userTaskIndex = b[0];
        flowIndex = b[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 5, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;


        temp = userTaskIndex - 1 ;
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));


        for(int i = 0 ; i <16 ;i++)
        {
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
            userTaskIndex++;
            temp = userTaskIndex -1 ;
            if(userTaskIndex <= 50)
                process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        }

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));

        return process;
    }


    //这里的流程定义跟上面的不一样，50,80%, 40个节点,串行10个（前1后9）
    //这里的流程是start-->串行 user1,user2-->并行 user3····userN-->串行userN+1···userNodesNum-->end
    private Process getProcess40(Integer parallelNum, Integer normalNum, Integer nodesNum,
                                 Integer startForkTaskIndex, Integer endJoinFirstTaskIndex) {
        Process process = new Process();
        process.setId("process1");

        String startStr = "startEvent";
        String endStr = "endEvent";
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";


        process.addFlowElement(ExamProcModelUtils.createStartEvent());
        process.addFlowElement(ExamProcModelUtils.createEndEvent());
        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + 1, userTaskStr + 1));

        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(startStr , userTaskStr + 1 ,flowStr + 1));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + 1, forkStr + 1 ,flowStr + 2));

        int taskIndex = 2;
        int forkIndex = 1;
        int flowIndex = 3;

        int a[] = ExamProcModelUtils.setProcBranch20(process,taskIndex,forkIndex,flowIndex);
        int temp = a[1] - 1 ;
        int userTaskIndex = a[0];
        flowIndex = a[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 1, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;

        int b[] = ExamProcModelUtils.setProcBranch20(process,userTaskIndex,5,flowIndex);
        temp = b[1] - 1 ;
        userTaskIndex = b[0];
        flowIndex = b[2];

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + 5, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        userTaskIndex++;


        temp = userTaskIndex - 1 ;
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex,flowStr + flowIndex++));


        for(int i = 0 ; i <7 ;i++)
        {
            process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
            userTaskIndex++;
            temp = userTaskIndex -1 ;
            if(userTaskIndex <= 50)
                process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + userTaskIndex ,flowStr + flowIndex++));
        }

        process.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + userTaskIndex, userTaskStr + userTaskIndex));
        process.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + userTaskIndex, endStr ,flowStr + flowIndex++));

        return process;
    }


    //1、创建流程实例
    private void createInstances(BpmnModel bpmnModel, Integer endJoinFirstTaskIndex, int nodesNum) {
        //跑300条流程，100条要迁移，100条迁移加回退，100条啥都不做
        //迁移,停留在userTask2上
        int onlyTransNum = 2;
        //迁移加回退,停留在userTask3上
        int transRollNum = 2;
        //啥都不做，停留在endJoin+1上
        int nullNum = 2;


        //部署
        Deployment dep = repositoryService.createDeployment()
                .addBpmnModel("dynamic-model.bpmn", bpmnModel).name("Dynamic process deployment").deploy();
        //运行
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();
        TaskService taskService = engine.getTaskService();
        //刚好运行到userTask2上
        for (int t = 0; t < onlyTransNum; t++) {
            ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());

            outterLoop:
            for (int i = 0; i < nodesNum; i++) {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                //遍历，然后完成
                for (Task task : tasks) {
                    if (task.getName().equals("UserTask2"))
                        break outterLoop;

                    taskService.complete(task.getId());
//                    System.out.println(i + "  " + task.getName());
                }
            }
        }

        //运行到userTask3上
        for (int t = 0; t < transRollNum; t++) {
            ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());

//            System.out.println(instance.getId());

            outterLoop2:
            for (int i = 0; i < nodesNum; i++) {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                //遍历，然后完成
                for (Task task : tasks) {
                    if (task.getName().equals("UserTask3"))
                        break outterLoop2;

                    taskService.complete(task.getId());
//                    System.out.println(i + "  " + task.getName());
                }
            }
        }

        //运行到并行节点结束之后的第二个节点(废弃)
        //现在改成运行到最后一个节点
        int temp = nodesNum;

        for (int t = 0; t < nullNum; t++) {
            ProcessInstance instance = runtimeService.startProcessInstanceById(pd.getId());

//            System.out.println(instance.getId());

            outterLoop3:
            for (int i = 0; i < nodesNum; i++) {
                List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

                //遍历，然后完成
                for (Task task : tasks) {
                    if (task.getName().equals("UserTask" + temp))
                        break outterLoop3;

                    taskService.complete(task.getId());
                }
            }
        }

    }

    //2、对原来的流程定义A进行修改，修改并行分支的第一个、第二个节点以及之后的那个join节点
    private void changeProcDef(int nodesNum ,int parallelNum) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();

        List<ProcessInstance> lists = query.active().list();
        ProcessInstance processInstance = lists.get(0);

        //遍历所有流程实例,完成迁移+执行的步骤
        for (int i = 0; i < parallelNum; i++) {
            mapper.setDefinition(processInstance.getProcessInstanceId());
        }

    }



    private void execute(ProcessInstance instance, int execLineNum) {

        for (int i = 0; i < execLineNum; i++) {
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();

            if (tasks.size() == 0)
                break;

            //遍历，然后完成
            for (Task task : tasks) {
                taskService.complete(task.getId());
//                System.out.println(i+"  "+task.getName());
            }
        }

    }


}
