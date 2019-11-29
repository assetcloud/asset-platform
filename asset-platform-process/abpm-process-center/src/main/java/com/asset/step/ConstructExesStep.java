package com.asset.step;

import com.asset.entity.ActHiActinst;
import com.asset.exception.ProcException;
import com.asset.javabean.AsExecution;
import com.asset.javabean.AsParallelNode;
import com.asset.javabean.AsTask;
import com.asset.mapper.FlowableMapper;
import com.asset.service.FlowableService;
import com.asset.utils.Constants;
import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ConstructExesStep {
    @Autowired
    FlowableService flowableService;

    /**
     * @param publicExecution  这个是当前方法的一个公共执行链，如果需要创建新的分支，需要从这个公共执行链复制内容,注意这里的String内容是taskId
     * @param historicActsAsc  一个不变的执行序列
     * @param curHistoricIndex 当前递归层次遍历到执行序列的哪一个元素了
     * @param parallelNodes    之前遍历model时得到的关于并行网关的信息
     * @param allExes          构造出的所有执行流列表
     */
    public void constructExes(AsExecution publicExecution,
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
                        constructExes(newExe, historicActsAsc, newHistoricIndex, parallelNodes, allExes, curRunningTaskId);
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
}
