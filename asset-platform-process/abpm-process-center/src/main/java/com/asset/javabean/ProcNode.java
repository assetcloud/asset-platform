package com.asset.javabean;

import lombok.Data;

/**
 * 用来表示流程中的一个节点（包含了网关、开始、结束事件）
 */
@Data
public class ProcNode {
    String id;
    Integer type;
    String executionId;   //这个executionId是这个节点在flowable中真实的executionId值

    public ProcNode(String id, Integer type, String executionId) {
        this.id = id;
        this.type = type;
        this.executionId = executionId;
    }

    private ProcNode(Builder builder) {
        id = builder.id;
        type = builder.type;
        executionId = builder.executionId;
    }


    public static final class Builder {
        private String id;
        private Integer type;
        private String executionId;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder type(Integer val) {
            type = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public ProcNode build() {
            return new ProcNode(this);
        }
    }
}
