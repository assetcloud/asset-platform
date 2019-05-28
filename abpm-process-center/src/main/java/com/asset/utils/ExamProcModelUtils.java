package com.asset.utils;

import liquibase.util.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.Execution;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 这个工具类主要用于生成流程模型节点信息
 * @author yby
 * @time 190522之前的某一天
 * @version 1.0_190522b(efore)
 */
public class ExamProcModelUtils {

    //任务节点-组
    public static UserTask createGroupTask(String id, String name, String candidateGroup) {
        List<String> candidateGroups = new ArrayList<String>();
        candidateGroups.add(candidateGroup);
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateGroups(candidateGroups);
        return userTask;
    }

    //任务节点-用户
    public UserTask createUserTask(String id, String name, String userPkno) {
        List<String> candidateUsers = new ArrayList<String>();
        candidateUsers.add(userPkno);
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateUsers(candidateUsers);
        return userTask;
    }

    //任务节点-用户,不用指定候选人
    public static UserTask createUserTask(String id, String name) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        return userTask;
    }

    //任务节点-锁定者
    public static UserTask createAssigneeTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }

    /*连线*/
    public static SequenceFlow createSequenceFlow(String from, String to, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        if (StringUtils.isNotEmpty(conditionExpression)) {
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }

    /*连线*/
    public static SequenceFlow createSequenceFlow(String from, String to, String name) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);

        return flow;
    }

    //排他网关
    public static ExclusiveGateway createExclusiveGateway(String id, String name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
        return exclusiveGateway;
    }

    //并行网关
    public static ParallelGateway createParallelGateway(String id, String name) {
        ParallelGateway gateway = new ParallelGateway();
        gateway.setId(id);
        gateway.setName(name);
        return gateway;
    }

    //开始节点
    public static StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("startEvent");
        return startEvent;
    }

    /*结束节点*/
    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEvent");
        return endEvent;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long getDiff(String startTime , String endTime)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date start = df.parse(startTime);
            Date end = df.parse(endTime);
            long diff = end.getTime() - start.getTime();//这样得到的差值是毫秒级别
//            long days = diff / (1000 * 60 * 60 * 24);

//            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
//            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);

            return diff;
        } catch (Exception e) {
        }

        return 0;
    }


    //这里刚好10个userTask在分支结构中
    public static int[] setProcBranch10(Process proc , int staTaskIndex , int forkIndex, int flowIndex)
    {
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";

        int t1,t2,t3,t4,f1,f2;


        //第一个fork
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+forkIndex, forkStr+forkIndex));
        //第一个fork对应的join
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+forkIndex, joinStr+forkIndex));
        f1 = forkIndex;
        forkIndex++;

        //上分支初始3个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t1 = staTaskIndex;
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;

        //第二个fork
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+forkIndex, forkStr+forkIndex));
        //对应第二个fork的join
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+forkIndex, joinStr+forkIndex));
        f2 = forkIndex;
        forkIndex++;

        //两个嵌套
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;

        //后部1个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t2 = staTaskIndex;
        staTaskIndex ++;

        //下面4个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t3 = staTaskIndex;
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t4 = staTaskIndex;
        staTaskIndex ++;


        //接下来添加flow
        //前后两个大并行
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f1, userTaskStr + t1 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f1, userTaskStr + t3 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t2, joinStr + f1 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t4, joinStr + f1 , flowStr + flowIndex));
        flowIndex++;

        int temp = t1+1 ;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t1, userTaskStr + temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, forkStr + f2 , flowStr + flowIndex));
        flowIndex++;

        //小分支
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f2, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, joinStr + f2 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f2, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, joinStr + f2 , flowStr + flowIndex));
        flowIndex++;

        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + f2 ,userTaskStr +t2, flowStr + flowIndex));


        //下面4个UserTask
        temp = t3 + 1;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t3 ,userTaskStr + temp, flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp ,userTaskStr + ++temp, flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp ,userTaskStr + ++temp, flowStr + flowIndex));
        flowIndex++;


        int a[] = new int[3];
        a[0] = staTaskIndex;  //下个出现的userTask出现的下标
        a[1] = forkIndex; //下个出现的fork/join的下标
        a[2] = flowIndex; //下个出现的flow的下标

        //下一个的
        return a;
    }

    //这里刚好10个userTask在分支结构中
    public static int[] setProcBranch20(Process proc , int staTaskIndex , int forkIndex, int flowIndex)
    {
        String userTaskStr = "UserTask";
        String forkStr = "parallel-fork";
        String joinStr = "parallel-join";
        String flowStr = "flow";

        int t1,t2,t3,t4,f1,f2;


        //第一个fork
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+forkIndex, forkStr+forkIndex));
        //第一个fork对应的join
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+forkIndex, joinStr+forkIndex));
        f1 = forkIndex;
        forkIndex++;

        //上分支初始3个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t1 = staTaskIndex;
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;

        //第二个fork
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(forkStr+forkIndex, forkStr+forkIndex));
        //对应第二个fork的join
        proc.addFlowElement(ExamProcModelUtils.createParallelGateway(joinStr+forkIndex, joinStr+forkIndex));
        f2 = forkIndex;
        forkIndex++;

        //两个嵌套
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;

        //后部1个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t2 = staTaskIndex;
        staTaskIndex ++;

        //下面4个
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t3 = staTaskIndex;
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        staTaskIndex ++;
        proc.addFlowElement(ExamProcModelUtils.createUserTask(userTaskStr + staTaskIndex, userTaskStr + staTaskIndex));
        t4 = staTaskIndex;
        staTaskIndex ++;


        //接下来添加flow
        //前后两个大并行
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f1, userTaskStr + t1 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f1, userTaskStr + t3 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t2, joinStr + f1 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t4, joinStr + f1 , flowStr + flowIndex));
        flowIndex++;

        int temp = t1+1 ;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t1, userTaskStr + temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, forkStr + f2 , flowStr + flowIndex));
        flowIndex++;

        //小分支
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f2, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, joinStr + f2 , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(forkStr + f2, userTaskStr + ++temp , flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp, joinStr + f2 , flowStr + flowIndex));
        flowIndex++;

        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + f2 ,userTaskStr +t2, flowStr + flowIndex));


        //下面4个UserTask
        temp = t3 + 1;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t3 ,userTaskStr + temp, flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp ,userTaskStr + ++temp, flowStr + flowIndex));
        flowIndex++;
        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + temp ,userTaskStr + ++temp, flowStr + flowIndex));
        flowIndex++;

        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(userTaskStr + t4 ,forkStr + forkIndex, flowStr + flowIndex));
        flowIndex++;

        int a[] = setProcBranch10(proc,staTaskIndex,forkIndex,flowIndex);

        temp = a[1] - 2;

        proc.addFlowElement(ExamProcModelUtils.createSequenceFlow(joinStr + temp ,joinStr + f1, flowStr + flowIndex));
        flowIndex++;


        a[2] = flowIndex; //下个出现的flow的下标

        //下一个的
        return a;

    }



    //这里的流程定义跟上面的不一样，50,40%, 20个节点,串行30个（前1后29）
    //这里的流程是start-->串行 user1,user2-->并行 user3····userN-->串行userN+1···userNodesNum-->end
    public static Process getProcess20(Integer parallelNum, Integer normalNum, Integer nodesNum,
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

    /**
     * 这里之所以要搞一个execution的list是因为实际获取的时候，是一个父Execution在executions[0]的位置，另一个在[1]的位置，在[1]位置上的execution才是我们要找的
     * @param executions
     * @return
     */
    public static Execution getCurExecution(List<Execution> executions){
        Execution curExe = null;

        for (int j = 0; j < executions.size(); j++) {
            curExe = executions.get(j);
            if (curExe.getActivityId() == null)
                continue;
            else
                break;
        }

        return curExe;
    }


    /**
     * @param historyService
     * @param procInstanceID
     * @param firstChangedID 影响域中第一个节点
     * @return 这个方法是返回当前执行的流程实例中影响域最前端的前面一个节点的信息
     */
    public static HistoricActivityInstance getBeforeChanged(HistoryService historyService, String procInstanceID, String firstChangedID){

        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(procInstanceID)
                .finished()//已经结束的活动节点
                .orderByHistoricActivityInstanceEndTime().desc().list();
//        for(HistoricActivityInstance historicActivityInstance: historicActivityInstanceList){
//            System.out.println("历史活动ID: "+historicActivityInstance.getActivityId() + " 历史活动名称: "+historicActivityInstance.getActivityName());
//        }

        for(int i = historicActivityInstanceList.size()-1;i>=0;i--)
        {
            System.out.println("历史活动ID: "+historicActivityInstanceList.get(i).getActivityId());
        }


        for(int i = 0;i<historicActivityInstanceList.size();i++)
        {
            //获取当前节点的前一个活动节点
            HistoricActivityInstance historicActivityInstance = historicActivityInstanceList.get(i);
            //获取当前影响域一个活动节点的id,比较它和影响域中第一个之间的大小，如果就是影响域第一个，那么就是==，还需要获取下一个下标，只有第一次出现 <0
            //才是我们想要的那个节点(同时还得是userTask的类型！因为有可能在前面的是分支节点啥的)
            if(historicActivityInstance.getActivityId().compareTo(firstChangedID) < 0
                    && historicActivityInstance.getActivityType().equals("userTask"))
                return historicActivityInstance;
        }

        return null;
    }




//    /**
//     * @param assignee
//     * @return
//     */
//    public List<String> getIncomeFlow(String assignee){
//        List<String> strList = new ArrayList<String>();
//        Task task = taskService.createTaskQuery()
//                .processDefinitionKey("leave_bill").taskAssignee(assignee).singleResult();
//        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService
//                .getProcessDefinition(task.getProcessDefinitionId());
//        //获取流程实例
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(task.getProcessInstanceId()).singleResult();
//        //获取当前流程实例的活动id
//        String activityId = processInstance.getActivityId();
//        //获取当前流程定义中的活动节点(包括开始和结束节点)
//        ActivityImpl activityImpl = processDefinition.findActivity(activityId);
//        //获取输出的flow
//        List<PvmTransition> transitionList = activityImpl.getIncomingTransitions();
//        for(PvmTransition transition: transitionList){
//            String flowName = (String) transition.getProperty("name");
//            strList.add(flowName);
//        }
//        return strList;
//    }





}
