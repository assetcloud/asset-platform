package com.asset.command;

import com.asset.exception.ProcException;
import com.asset.javabean.*;
import com.asset.service.FlowableService;
import com.asset.service.ProcInstService;
import com.asset.service.ProcNodeService;
import com.asset.step.*;
import com.asset.utils.ProcUtils;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    ProcInstService procInstService;

    @Autowired
    ConstructExesStep constructExesStep;
    @Autowired
    ConstructStacksStep constructStacksStep;
    @Autowired
    SignParallelNodeStep signParallelNodeStep;

    @Autowired
    GetRollbackNodeStep getRollbackNodeStep;
    @Autowired
    TranslateStep translateStep;


    /*
    在包含并行分支的流程执行序列中找到正确的回滚点位置，方案设计如下：
    从头开始遍历执行序列，遇到并行网关，如果出度有多个分支，那么代表是开始 发散了，需要构建一条新的executionId;
            如果出度只有一个出口，那么代表的是结束；
         遇到普通userTask，出度是一个的，加入之前创建的多条executionId表
         */
    public HashMap<String,Object> rollbackParallelProc(String procInstID, String procModelId, String curTaskId) throws Exception {
        //获取model
        ArrayList<FlowElement> flowElements = (ArrayList<FlowElement>) procUtils.getFlowElements(procModelId, modelService);
        //遍历model,标记并行网关,String是这个并行网关的act_id
        HashMap<String, AsParallelNode> parallelNodes = signParallelNodeStep.signParallelNode(flowElements);

        //获取从头开始的执行序列
        List<HistoricActivityInstance> historicActsAsc = ProcUtils.getHistoricActsAsc(procInstID);
        AsExecution firstExecution = new AsExecution();
        int historicIndex = 0;
        HashMap<String, AsExecution> allExes = new HashMap<>();
        //构建执行序列
        constructExesStep.constructExes(firstExecution, historicActsAsc, historicIndex, parallelNodes, allExes, curTaskId);

        //这边需要创建多个栈，即把上面的allExes中的execution中的元素放入栈中，用于区分并行网关之间的层次关系
        HashMap<String, Object> map = constructStacksStep.constructStacks(allExes, curTaskId);
        //在stacks中找到主执行序列的stack，然后以该stack为主，往前遍历找到回滚点
        int mainIndex = (int) map.get("main");
        List<AsTaskStack> stacks = (List<AsTaskStack>) map.get("stacks");

        //用来标示回滚点
        AsTask rollbackTask = null;
        //这个用来标示当前选中的经办节点所处的并行网关层次，要求选中的层次得和主执行序列的一致
        AsParallelNode mainParallel = null;

        //先对主执行序列进行遍历，找到回滚点
        AsTaskStack mainStack = stacks.get(mainIndex);
        HashMap<String, Object> hashMap = mainStack.selectLastApplyTask(parallelNodes, procModelId, procNodeService,translateStep);
        mainParallel = (AsParallelNode) hashMap.get("parallel");
        rollbackTask = (AsTask) hashMap.get("rollbackTask");

        if (rollbackTask == null)
            throw new ProcException("当前流程实例无法完成回滚操作！无法找到回滚点！");

        ArrayList<AsTaskStack> rollbackEnable = new ArrayList<>();
        ArrayList<AsTaskStack> rollbackDisable = new ArrayList<>();

        //对剩余的栈进行遍历，看哪些栈属于可以回滚，哪些栈属于不可回滚，划分出 可回滚 与 不可回滚 列表
        //这边划分出之后，对原来的execution
        for (int i = 0; i < stacks.size(); i++) {
            AsTaskStack curStack = stacks.get(i);

            //主执行序列自动属于rollbackEnable
            if (i == mainIndex)
            {
                //mainIndex此时更新为mainStack在rollbackEnable中的下标位置
                mainIndex = rollbackEnable.size();
                rollbackEnable.add(stacks.get(i));
                for(AsExecution asExecution:allExes.values()){
                    asExecution.initRollbackEnable(curStack.getExeId());
                }
                continue;
            }

            if(curStack.isRollbackEnable(parallelNodes,rollbackTask,mainParallel,procModelId,procNodeService,translateStep))
            {
                rollbackEnable.add(curStack);
                for(AsExecution asExecution:allExes.values()){
                    asExecution.initRollbackEnable(curStack.getExeId());
                }
            }
            else
                rollbackDisable.add(curStack);
        }

        //对可回滚列表中的mainExe进行回滚，对可回滚列表中的剩余Exe进行强行停止
        //关于被回滚的任务状态更新在这里做，去as_form_inst表遍历相应记录，更新状态码
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
        HashMap<String,Object> hashMapReturn = new HashMap<>();
        hashMapReturn.put("rollback",rollbackTask.getActId());
        hashMapReturn.put("allExes",allExes);

        return hashMapReturn;
    }

    /**
     * 对不包含并行网关的流程进行回滚
     * @param simpleTask
     * @return
     */
    public String rollbackNormalProc(AsSimpleTask simpleTask){
        //获取上一个经办节点
        String lastApplyNode = getRollbackNodeStep.getNormalProcRollbackId(simpleTask.getProcInstId());
        if (lastApplyNode.equals(""))
            throw new ProcException("无法找到上一个经办节点，无法完成回滚，当前审批意见无法执行！");
        //回滚
        procInstService.rollback(simpleTask.getProcInstId(), lastApplyNode, simpleTask.getExecutionId());

        return lastApplyNode;
    }


}
