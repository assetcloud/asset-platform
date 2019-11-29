package com.asset.javabean;

import lombok.*;

/**
 * 包含当前执行任务更加复杂的数据信息
 * 除了基本数据之外，还包含了运行时的一些数据
 */
@Data
public class AsRunningTask extends AsSimpleTask {
    String formSheet;
    String formValue;
    String[] candidateUser;
    String[] candidateGroup;
    Integer ifJointSign;
    String committer;
    String procModelId;
    String formModelId;
    String sceneId;

    public AsRunningTask(int nodeType, String nodeId, String taskId, String procInstId, String executionId) {
        super(nodeType, nodeId, taskId, procInstId, executionId);
    }

    public AsRunningTask() {
    }
}
