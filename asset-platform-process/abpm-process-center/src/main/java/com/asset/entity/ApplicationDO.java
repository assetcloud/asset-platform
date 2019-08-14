package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationDO {

    private String id;

    private String applicationName;

    private String iconCls;

    private Integer status;

    private Integer isPublished;

    private Date createdTime;

    private Date disableTime;

    private Date removeTime;

    public ApplicationDO() {
    }

    private ApplicationDO(Builder builder) {
        setId(builder.id);
        setApplicationName(builder.applicationName);
        setIconCls(builder.iconCls);
        setStatus(builder.status);
        setIsPublished(builder.isPublished);
        setCreatedTime(builder.createdTime);
        setDisableTime(builder.disableTime);
        setRemoveTime(builder.removeTime);
    }


    public static final class Builder {
        private String id;
        private String applicationName;
        private String iconCls;
        private Integer status;
        private Integer isPublished;
        private Date createdTime;
        private Date disableTime;
        private Date removeTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder applicationName(String val) {
            applicationName = val;
            return this;
        }

        public Builder iconCls(String val) {
            iconCls = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public Builder isPublished(Integer val) {
            isPublished = val;
            return this;
        }

        public Builder createdTime(Date val) {
            createdTime = val;
            return this;
        }

        public Builder disableTime(Date val) {
            disableTime = val;
            return this;
        }

        public Builder removeTime(Date val) {
            removeTime = val;
            return this;
        }

        public ApplicationDO build() {
            return new ApplicationDO(this);
        }
    }
}
