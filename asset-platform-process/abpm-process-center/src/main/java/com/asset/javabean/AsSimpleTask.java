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

    private AsSimpleTask(Builder builder) {
        setNodeType(builder.nodeType);
        setNodeId(builder.nodeId);
        setTaskId(builder.taskId);
        setProcInstId(builder.procInstId);
        setExecutionId(builder.executionId);
    }


    public static final class Builder {
        private int nodeType;
        private String nodeId;
        private String taskId;
        private String procInstId;
        private String executionId;

        public Builder() {
        }

        public Builder nodeType(int val) {
            nodeType = val;
            return this;
        }

        public Builder nodeId(String val) {
            nodeId = val;
            return this;
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public AsSimpleTask build() {
            return new AsSimpleTask(this);
        }
    }
}
