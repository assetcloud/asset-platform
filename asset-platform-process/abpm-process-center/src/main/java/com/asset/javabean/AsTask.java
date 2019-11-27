package com.asset.javabean;


import com.asset.entity.ActHiActinst;
import lombok.Builder;
import lombok.Data;
import org.flowable.engine.history.HistoricActivityInstance;

@Data
@Builder
public class AsTask {
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
        switch (node.getActivityType())
        {
            case "parallelGateway":
                this.taskId = "parallelGateway_"+node.getActivityId();
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
        switch (actInst.getActType())
        {
            case "parallelGateway":
                this.taskId = "parallelGateway_"+actInst.getActId();
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
}
