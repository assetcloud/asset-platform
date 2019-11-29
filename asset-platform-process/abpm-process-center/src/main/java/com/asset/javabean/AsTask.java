package com.asset.javabean;


import com.asset.entity.ActHiActinst;
import com.asset.service.ProcNodeService;
import com.asset.step.TranslateStep;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
public class AsTask {


    //    @TableField("task_")
    String taskId; //用来找共同点
    //下面两个属性用于回滚
    String executionId;
    String actId;
    String actType;

    public AsTask() {
    }

    public AsTask(String taskId, String executionId, String actId, String actType) {
        this.taskId = taskId;
        this.executionId = executionId;
        this.actId = actId;
        this.actType = actType;
    }


    public AsTask(HistoricActivityInstance node) {
        switch (node.getActivityType()) {
            case "parallelGateway":
                this.taskId = "parallelGateway_" + node.getActivityId();
                break;
            case "startEvent":
                this.taskId = "startEvent";
                break;
            case "endEvent":
                this.taskId = "startEvent";
                break;
            default:
                this.taskId = node.getTaskId();
                break;
        }

        this.executionId = node.getExecutionId();
        this.actId = node.getActivityId();
        this.actType = node.getActivityType();
    }

    public AsTask(ActHiActinst actInst) {
        switch (actInst.getActType()) {
            case "parallelGateway":
                this.taskId = "parallelGateway_" + actInst.getActId();
                break;
            case "startEvent":
                this.taskId = "startEvent";
                break;
            case "endEvent":
                this.taskId = "startEvent";
                break;
            default:
                this.taskId = actInst.getTaskId();
                break;
        }
        this.executionId = actInst.getExecutionId();
        this.actId = actInst.getActId();
        this.actType = actInst.getActType();
    }


    //判断栈顶元素是不是经办节点
    public boolean isApplyTask(ProcNodeService procNodeService,TranslateStep translateStep) {
        String procModelId = translateStep.taskIdToProcModelId(this.taskId);
        if (this.getActType().equals(Constants.FLOWABLE_ACT_TYPE_USERTASK))
            if (procNodeService.getNodeType(     procModelId, this.getActId()   ) == (Constants.AS_NODE_APPLY))
                return true;
            else
                return false;
        else
            return false;
    }

    public boolean isStartEvent() {
        if (this.getActType().equals(Constants.FLOWABLE_ACT_TYPE_STARTEVENT))
            return true;
        else
            return false;
    }

    //判断栈顶元素是不是并行网关-开始
    public boolean isTopParallelStart(HashMap<String, AsParallelNode> parallelNodes) {
        if (this.getActType().equals(Constants.FLOWABLE_ACT_TYPE_PARALLEL)) {
            AsParallelNode asParallelNode = parallelNodes.get(this.getActId());
            //检查是结束还是开始，结束的话，要一直出栈，只到遇到对应的开始
            if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_start)
                return true;
            else
                return false;
        } else
            return false;
    }

    //判断栈顶元素是不是并行网关-结束
    public boolean isTopParallelEnd(HashMap<String, AsParallelNode> parallelNodes) {

        if (this.getActType().equals(Constants.FLOWABLE_ACT_TYPE_PARALLEL)) {
            AsParallelNode asParallelNode = parallelNodes.get(this.getActId());
            //检查是结束还是开始，结束的话，要一直出栈，只到遇到对应的开始
            if (asParallelNode.getType() == Constants.AS_NODE_PARALLEL_end)
                return true;
            else
                return false;
        } else
            return false;
    }

    public boolean isUserTask() {
        if (this.getActType().equals(Constants.FLOWABLE_ACT_TYPE_USERTASK))
            return true;
        else
            return false;
    }
}
