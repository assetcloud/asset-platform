package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AsFormInst {
    private String id;

    private String formModelId;

    private String procInstId;

    private String executionId;

    private String taskId;

    private Date createdTime;

    private String createdBy;

    private String formInstJson;

    private int status;

    public AsFormInst(String formModelId, String procInstId, String executionId, String taskId, String createdBy, String formInstJson) {
        this.formModelId = formModelId;
        this.procInstId = procInstId;
        this.executionId = executionId;
        this.taskId = taskId;
        this.createdBy = createdBy;
        this.formInstJson = formInstJson;
    }
}
