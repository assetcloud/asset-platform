package com.asset.entity;

import lombok.Data;

@Data
public class ActAuthority {
    private String procModelId;

    private String actId;

    private String formItemKey;

    private Integer authority;

    public ActAuthority() {
    }

    private ActAuthority(Builder builder) {
        setProcModelId(builder.procModelId);
        setActId(builder.actId);
        setFormItemKey(builder.formItemKey);
        setAuthority(builder.authority);
    }


    public static final class Builder {
        private String procModelId;
        private String actId;
        private String formItemKey;
        private Integer authority;

        public Builder() {
        }

        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder actId(String val) {
            actId = val;
            return this;
        }

        public Builder formItemKey(String val) {
            formItemKey = val;
            return this;
        }

        public Builder authority(Integer val) {
            authority = val;
            return this;
        }

        public ActAuthority build() {
            return new ActAuthority(this);
        }
    }
}
