package com.asset.entity;

import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import lombok.Data;

@Data
public class FormAuthorityDO {
    private String id;
    private String procModelId;
    private String actId;
    private String formItemKey;
    private Integer authority;

    public FormAuthorityDO() {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
    }


    private FormAuthorityDO(Builder builder) {
        setId(builder.id);
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
        private String id;

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

        public FormAuthorityDO build() {
            IDGenerator generator = new UuidIdGenerator();
            id = generator.generateID();
            return new FormAuthorityDO(this);
        }
    }
}
