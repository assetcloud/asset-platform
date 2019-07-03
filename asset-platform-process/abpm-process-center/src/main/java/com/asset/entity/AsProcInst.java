package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AsProcInst {
    private String procInstId;

    private String procModelId;

    private String procDefId;

    private String procDeployId;

    private Date createdTime;

    private String createdBy;

    public AsProcInst() {
    }

    public AsProcInst(String procInstId, String procModelId, String procDefId, String procDeployId, String createdBy) {
        this.procInstId = procInstId;
        this.procModelId = procModelId;
        this.procDefId = procDefId;
        this.procDeployId = procDeployId;
        this.createdBy = createdBy;
    }
}
