package com.asset.entity;

import com.asset.javabean.UuidIdGenerator;
import com.asset.utils.IDGenerator;
import lombok.Data;

import java.util.Date;

@Data
public class FormInstInfo {
    private String id;

    private String formModelId;

    private String taskId;

    private String executionId;

    private Date createdTime;

    private String createdBy;

    private String formInstJson;

    public FormInstInfo(String formModelId, String taskId, String executionId, String createdBy, String formInstJson) {
        IDGenerator generator = new UuidIdGenerator();
        this.id = generator.generateID();
        this.formModelId = formModelId;
        this.taskId = taskId;
        this.executionId = executionId;
        this.createdBy = createdBy;
        this.formInstJson = formInstJson;
    }
}
