package com.asset.javabean;

import javafx.concurrent.Task;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 简单的任务节点信息
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AsSimpleTask {
    int nodeType;
    String nodeId;
    String taskId;
    String procInstId;
    String executionId;

    public AsSimpleTask() {
    }
}
