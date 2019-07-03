package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AsFormProcInstBind {
    private Integer id;

    private String formInstId;

    private String procInstId;

    private String formModelId;

    private String procModelId;

    private String procDefId;

    private String procDeployId;

    private Date createdTime;

    private String createdBy;

    public AsFormProcInstBind(String formModelId,
                              String procModelId,
                              String procDefId,
                              String procDeployId,
                              String procInstId,
                              String formInstId,
                              String createdBy) {
        this.formModelId = formModelId;
        this.procModelId = procModelId;
        this.procDefId = procDefId;
        this.procDeployId = procDeployId;
        this.procInstId = procInstId;
        this.formInstId = formInstId;
        this.createdBy = createdBy;
    }
}
