package com.asset.command;

import com.asset.entity.ActHiActinst;
import com.asset.exception.ProcException;
import com.asset.javabean.AsExecution;
import com.asset.javabean.AsParallelNode;
import com.asset.javabean.AsTask;
import com.asset.javabean.AsTaskStack;
import com.asset.service.FlowableService;
import com.asset.service.ProcNodeService;
import com.asset.utils.Constants;
import com.asset.utils.ProcUtils;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ParallelGateway;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Component
public class RollbackCommand {

    @Autowired
    ProcUtils procUtils;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    ModelService modelService;
    @Autowired
    FlowableService flowableService;
    @Autowired
    DeleteProcCommand deleteProcCommand;

    //其中
    public HashMap<String, Object> constructStacks(HashMap<String, AsExecution> allExes, String curTaskId) {
        List<AsTaskStack> stacks = new ArrayList<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        int mainIndex = 0;
        int temp = 0;

        for (String key : allExes.keySet()) {
            AsTaskStack stack = new AsTaskStack();
            ArrayList<AsTask> executions = allExes.get(key).getExecutions();
            for (AsTask task : executions) {
                if (task.getTaskId().equals(curTaskId))
                    mainIndex = temp;
                stack.push(task);
                stack.initExeId(allExes.get(key).getExeId());
            }

            stacks.add(stack);
            temp++;
        }

        hashMap.put("stacks", stacks);
        hashMap.put("main", mainIndex);

        return hashMap;
    }


    /**
     * @param publicExecution  这个是当前方法的一个公共执行链，如果需要创建新的分支，需要从这个公共执行链复制内容,注意这里的String内容是taskId
     * @param historicActsAsc  一个不变的执行序列
     * @param curHistoricIndex 当前递归层次遍历到执行序列的哪一个元素了
     * @param parallelNodes    之前遍历model时得到的关于并行网关的信息
     * @param allExes          构造出的所有执行流列表
     */
    private void newConstructExecutions(AsExecution publicExecution,
                                        List<HistoricActivityInstance> historicActsAsc,
                                        int curHistoricIndex,
                                        HashMap<String, AsParallelNode> parallelNodes,
                                        HashMap<String, AsExecution> allExes,
                                        String curRunningTaskId) {
        for (; curHistoricIndex < historicActsAsc.size(); curHistoricIndex++) {
            HistoricActivityInstance curNode = historicActsAsc.get(curHistoricIndex);
            AsTask curTask = new AsTask(curNode);
            if (curTask.getActType().equals("parallelGateway")) {
                AsParallelNode asParallelNode = parallelNodes.get(curTask.getActId());

                //判断是不是开始节点
                if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_start) {
                    //有几个出度，就要新创建几个exe，注意第一个创建的exe会把原来的publicExe在allExes中的位置顶替掉
                    int outNums = asParallelNode.getOutNums();

                    List<ActHiActinst> unCompleteActinsts = flowableService.selectTaskByInstId(curNode.getProcessInstanceId());
                    if (unCompleteActinsts.size() != outNums)
                        throw new ProcException("当前并行网关有" + outNums + "个出度，但是有" + unCompleteActinsts.size() + "个未执行task");


                    for (int i = 0; i < outNums; i++) {
                        //构建新execution，如果是第一个遍历到的出度的话，这个新execution的exeId应该确定(继承原来的executionId)，
                        // 然后去act_ru_task表中找到与这个新创建的execution相同exeId的任务节点（得去act_ru_task表中找，因为act_hi_actinst表中会存在拥有相同exeId的历史任务，这些历史任务不能加到这里）
                        publicExecution.initExeId(curTask.getExecutionId());
                        publicExecution.add(curTask);
                        AsExecution newExe = new AsExecution(publicExecution);


                        //curNode是parallel，parallel之后的节点如果是没有被执行的，那么需要我们从act_ru_task中找到之后的节点信息，预加载进来
                        //第一个创建的exe会继承原来的publicExe的id,然后会把原来的publicExe在allExes中的位置顶替掉
                        if (i == 0) {
                            newExe.setExeId(publicExecution.getExeId());
                            allExes.put(newExe.getExeId(), newExe);

                            //去act_ru_task表中找到待执行的拥有相同exeId的下一个任务信息，添加到当前的newExe中
                            //注意这里是根据executionId然后精确找到那个parallel之后的任务节点的，只有新建的第一条execution有这个待遇
//                            ActHiActinst unCompleteActinst = flowableService.selectTaskByExeId(publicExecution.getExeId());
                            for (int m = 0; m < unCompleteActinsts.size(); m++) {
                                if (!unCompleteActinsts.get(m).getSign()
                                        && unCompleteActinsts.get(m).getExecutionId().equals(publicExecution.getExeId())) {
                                    unCompleteActinsts.get(m).setSign(true);

                                    AsTask unCompleteTask = new AsTask(unCompleteActinsts.get(m));
                                    newExe.add(unCompleteTask);
                                    break;
                                }
                            }
                        } else {
                            //这里的execution都是除了主execution之外剩下的新创建的execution
                            //这里新创建的execution的exeId是由 从act_ru_task表中读出的没有被添加过的ActHiActinst
                            for (int m = 0; m < unCompleteActinsts.size(); m++) {
                                if (!unCompleteActinsts.get(m).getSign()) {
                                    unCompleteActinsts.get(m).setSign(true);

                                    AsTask unCompleteTask = new AsTask(unCompleteActinsts.get(m));
                                    newExe.setExeId(unCompleteTask.getExecutionId());
                                    newExe.add(unCompleteTask);
                                    break;
                                }
                            }
                            allExes.put(newExe.getExeId(), newExe);
                        }

                        //curHistoricIndex是基本类型int，所以是值传递，不用担心在下一层递归函数中被修改
                        int newHistoricIndex = curHistoricIndex + 1;
                        newConstructExecutions(newExe, historicActsAsc, newHistoricIndex, parallelNodes, allExes, curRunningTaskId);
                    }
                } else if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_end) {
                    publicExecution.initExeId(curTask.getExecutionId());
                    publicExecution.add(curTask);
                }
            }
            //遇到当前节点，return
            else if (curTask.getTaskId().equals(curRunningTaskId)) {
                publicExecution.initExeId(curTask.getExecutionId());
                publicExecution.add(curTask);
                return;
            }
            //遇到非并行网关节点，直接添加
            else {
                publicExecution.initExeId(curTask.getExecutionId());
                publicExecution.add(curTask);
            }
        }
    }


    /*
    在包含并行分支的流程执行序列中找到正确的回滚点位置，方案设计如下：
    从头开始遍历执行序列，遇到并行网关，如果出度有多个分支，那么代表是开始 发散了，需要构建一条新的executionId;
            如果出度只有一个出口，那么代表的是结束；
         遇到普通userTask，出度是一个的，加入之前创建的多条executionId表
         */
    public String getRollbackPosInParallelProc(String procInstID, String procModelId, String curTaskId) throws Exception {
        //获取model
        ArrayList<FlowElement> flowElements = (ArrayList<FlowElement>) procUtils.getFlowElements(procModelId, modelService);
        //遍历model,标记并行网关,String是这个并行网关的act_id
        HashMap<String, AsParallelNode> parallelNodes = signParallel(flowElements);

        //获取从头开始的执行序列
        List<HistoricActivityInstance> historicActsAsc = ProcUtils.getHistoricActsAsc(procInstID);
        AsExecution firstExecution = new AsExecution();
        int historicIndex = 0;
        HashMap<String, AsExecution> allExes = new HashMap<>();
//        allExes.put(firstExecution.getExeId(), firstExecution);
        newConstructExecutions(firstExecution, historicActsAsc, historicIndex, parallelNodes, allExes, curTaskId);



        //这边需要创建多个栈，即把上面的allExes中的execution中的元素放入栈中，用于区分并行网关之间的层次关系
        HashMap<String, Object> map = constructStacks(allExes, curTaskId);
        //在stacks中找到主执行序列的stack，然后以该stack为主，往前遍历找到回滚点
        int mainIndex = (int) map.get("main");
        List<AsTaskStack> stacks = (List<AsTaskStack>) map.get("stacks");

        //用来标示回滚点
        AsTask rollbackTask = null;
        //这个用来标示当前选中的经办节点所处的并行网关层次，要求选中的层次得和主执行序列的一致
        AsParallelNode mainParallel = null;

        //先对主执行序列进行遍历，找到回滚点
        AsTaskStack mainStack = stacks.get(mainIndex);
        HashMap<String, Object> hashMap = mainStack.selectLastApplyTask(parallelNodes, procModelId, procNodeService);
        mainParallel = (AsParallelNode) hashMap.get("parallel");
        rollbackTask = (AsTask) hashMap.get("rollbackTask");

        if (rollbackTask == null)
            throw new ProcException("当前流程实例无法完成回滚操作！无法找到回滚点！");

        ArrayList<AsTaskStack> rollbackEnable = new ArrayList<>();
        ArrayList<AsTaskStack> rollbackDisable = new ArrayList<>();

        //对剩余的栈进行遍历，看哪些栈属于可以回滚，哪些栈属于不可回滚，划分出 可回滚 与 不可回滚 列表
        for (int i = 0; i < stacks.size(); i++) {
            //主执行序列自动属于rollbackEnable
            if (i == mainIndex)
            {
                //mainIndex此时更新为mainStack在rollbackEnable中的下标位置
                mainIndex = rollbackEnable.size();
                rollbackEnable.add(stacks.get(i));
            }
            AsTaskStack curStack = stacks.get(i);

            if(curStack.isRollbackEnable(parallelNodes,rollbackTask,mainParallel,procModelId,procNodeService))
                rollbackEnable.add(curStack);
            else
                rollbackDisable.add(curStack);
        }

        //对可回滚列表中的mainExe进行回滚，对可回滚列表中的剩余Exe进行强行停止
        for(int i = 0;i<rollbackEnable.size();i++)
        {
            //mainExe的位置，回滚
            if(i == mainIndex)
                ProcUtils.rollback(rollbackEnable.get(i).getExeId(),
                        rollbackTask.getActId(),   //第二个参数是回滚点的actId
                        procInstID);
            else
                deleteProcCommand.deleteExecution(rollbackEnable.get(i).getExeId());
        }

        //rollbackDisable表不进行任何操作


        //接着遍历得到的allExes，注意这里先遍历包含curTaskId的exe(containExes变量),找到离当前审批节点最近的统一的经办节点，然后回滚到这个经办节点，
        // 接着看剩余的exe（不包含curTaskId的exe，noneExes变量）,是否包含这个回滚点，如果包含，那么也回滚
//        ArrayList<AsExecution> containExes = new ArrayList<>();
//        ArrayList<AsExecution> noneExes = new ArrayList<>();

//        for (String key : allExes.keySet()) {
//            AsExecution curExe = allExes.get(key);
//
//            //原来是从startevent开始排序的，需要逆序排列
//            Collections.reverse(curExe.getExecutions());
//
//            if (curExe.containTask(curTaskId)) {
//                containExes.add(curExe);
//            } else {
//                noneExes.add(curExe);
//            }
//        }
//
//        AsExecution curExecution = containExes.get(0);
//        //这里是去找到正确的的回滚点
//        //这里的i表示现在找第几个i
//        loop1:
//        for (int i = 1; i < curExecution.getExecutions().size() + 1; i++) {
//            rollbackTask = getApproveTask(procModelId, i, curExecution.getExecutions());
//            //在剩余的执行序列中找当前找到的经办节点
//            for (int f = 1; f < containExes.size(); f++) {
//                //如果有执行流不包含，那么说明当前找到的经办节点不会，i值++，找下一个经办节点
//                if (!containExes.get(f).containTask(rollbackTask.getTaskId()))
//                    continue loop1;
//            }
//            //如果能到这一步，说明这个当前找到的经办节点在当前执行流中全都包含，所以
//            break loop1;
//        }

//        //接着，要回滚了，containExes全部回滚到这个回滚点，noneExes看有没有这个回滚点，如果包含的话，也要回滚
//        for (int i = 0; i < containExes.size(); i++) {
//            ProcUtils.rollback(ProcUtils.getExecutionId(curTaskId),   //第一个参数是当前正在执行的task的executionId
//                    rollbackTask.getActId(),   //第二个参数是回滚点的actId
//                    procInstID);
//        }
//
//        for (int i = 0; i < noneExes.size(); i++) {
//            if (noneExes.get(i).containTask(rollbackTask.getTaskId()))
//                ProcUtils.rollback(noneExes.get(i).getExecutions().get(0).getExecutionId(),
//                        rollbackTask.getActId(),
//                        procInstID);
//        }

            return rollbackTask.getActId();


//        //获取当前执行序列，从后往前
//        List<HistoricActivityInstance> historicActsDesc = ProcUtils.getHistoricActsDesc(procInstID);
//        int i, count = 0;
//        HashMap<String, ProcExecution> runningExecutions = new HashMap<>();
//        HashMap<String, Boolean> isVisited = new HashMap<>();
//        HashMap<String, String[]> nodeContainsIn = new HashMap<>();
//
//
//        ProcExecution mainExecution = new ProcExecution("main");
//        String startEventExecutionId = historicActsAsc.get(0).getExecutionId();
//        mainExecution.setExecutionId(startEventExecutionId);
//        runningExecutions.put(startEventExecutionId, mainExecution);
//
//
//        //构建执行序列
//        constructExecutions(historicActsAsc, flowElements, runningExecutions, mainExecution, 0, procModelId, isVisited, nodeContainsIn);
//
//        //##对执行序列进行遍历，找到两个序列经过的相同的距离审批节点最近的经办节点，就是我们要找的回滚点
//        //获取当前节点执行流的ExecutionID
//        String curExecutionID = historicActsDesc.get(0).getExecutionId();
//        //获取当前执行流
//        ProcExecution curExecution = runningExecutions.get(curExecutionID);
//        //获取当中节点
//        ArrayList<ProcNode> curProcnodes = curExecution.getProcNodes();
        //从后往前遍历寻找回滚节点
//        for (i = curProcnodes.size() - 2; i >= 0; i--) {
//            //检查节点的类型
//            ProcNode procNode = curProcnodes.get(i);
//            if (procNode.getType() == Constants.AS_NODE_APPLY && count <= 0) {
//                //count用来记录经过的并行网关出口，来辅助寻找回滚节点，若找到配对的并行网关入口则减1，count<=0时代表是合适的回滚位置
//                return procNode.getId();
//
//            } else if (procNode.getType() == Constants.AS_NODE_PARALLEL_end) {
//                count++;
//            } else if (procNode.getType() == Constants.AS_NODE_PARALLEL_start) {
//                count--;
//            }
//        }

    }


    /**
     * 标记并行网关
     *
     * @param flowElements
     */
    private HashMap<String, AsParallelNode> signParallel(ArrayList<FlowElement> flowElements) {
        //记录当前模型中出现的并行网关，然后对其中的并行网关进行标记
        HashMap<String, AsParallelNode> parallelNodes = new HashMap<>();
        Stack<String> stack = new Stack<>();

        int i = 0;
        //先从头遍历，构建多条执行序列
        for (; i < flowElements.size(); i++) {
            FlowElement flowElement = flowElements.get(i);
            if (flowElement instanceof ParallelGateway) {
                ParallelGateway gateway = (ParallelGateway) flowElement;
                List<SequenceFlow> outgoingFlows = gateway.getOutgoingFlows();
                List<SequenceFlow> incomingFlows = gateway.getIncomingFlows();

                //出度为1，那么是end
                if (outgoingFlows.size() == 1) {
                    String curPeerStartId = stack.pop();
                    parallelNodes.put(gateway.getId(), new AsParallelNode.Builder()
                            .id(gateway.getId())
                            .type(Constants.AS_NODE_PARALLEL_end)
                            .peerNodeId(curPeerStartId)
                            .outNums(1)
                            .build());
                    AsParallelNode curPeerStartNode = parallelNodes.get(curPeerStartId);
                    curPeerStartNode.setPeerNodeId(gateway.getId());
                } else {
                    stack.push(gateway.getId());
                    parallelNodes.put(gateway.getId(), new AsParallelNode(gateway.getId(), Constants.AS_NODE_PARALLEL_start, outgoingFlows.size()));
                }
            }
        }

        return parallelNodes;
    }

}
