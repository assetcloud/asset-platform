package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProcInstDO {
    private String procInstId;
    private String procModelId;
    private String procDefId;
    private String procDeployId;
    private Date commitTime;
    private String committer;
    private String formInstAllValue;
    private Integer status;

    public ProcInstDO() {
    }

    public ProcInstDO(String procInstId,
                      String procModelId,
                      String procDefId,
                      String procDeployId,
                      String committer,
                      String formInstAllValue
    ) {
        this.procInstId = procInstId;
        this.procModelId = procModelId;
        this.procDefId = procDefId;
        this.procDeployId = procDeployId;
        this.committer = committer;
        this.formInstAllValue = formInstAllValue;
    }

    private ProcInstDO(Builder builder) {
        setProcInstId(builder.procInstId);
        setProcModelId(builder.procModelId);
        setProcDefId(builder.procDefId);
        setProcDeployId(builder.procDeployId);
        setCommitTime(builder.commitTime);
        setCommitter(builder.committer);
        setFormInstAllValue(builder.formInstAllValue);
        setStatus(builder.status);
    }


    public static final class Builder {
        private String procInstId;
        private String procModelId;
        private String procDefId;
        private String procDeployId;
        private Date commitTime;
        private String committer;
        private String formInstAllValue;
        private Integer status;

        public Builder() {
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder procDefId(String val) {
            procDefId = val;
            return this;
        }

        public Builder procDeployId(String val) {
            procDeployId = val;
            return this;
        }

        public Builder commitTime(Date val) {
            commitTime = val;
            return this;
        }

        public Builder committer(String val) {
            committer = val;
            return this;
        }

        public Builder formInstAllValue(String val) {
            formInstAllValue = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public ProcInstDO build() {
            return new ProcInstDO(this);
        }
    }
}
