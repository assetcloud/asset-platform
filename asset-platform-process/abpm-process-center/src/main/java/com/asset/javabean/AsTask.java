package com.asset.javabean;


import lombok.Data;

@Data
public class AsTask {
    String taskId; //用来找共同点
    //下面两个属性用于回滚
    String executionId;
    String actId;

    public AsTask(String taskId, String executionId, String actId) {
        this.taskId = taskId;
        this.executionId = executionId;
        this.actId = actId;
    }

    private AsTask(Builder builder) {
        setTaskId(builder.taskId);
        setExecutionId(builder.executionId);
        setActId(builder.actId);
    }


    public static final class Builder {
        private String taskId;
        private String executionId;
        private String actId;

        public Builder() {
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public Builder actId(String val) {
            actId = val;
            return this;
        }

        public AsTask build() {
            return new AsTask(this);
        }
    }
}
