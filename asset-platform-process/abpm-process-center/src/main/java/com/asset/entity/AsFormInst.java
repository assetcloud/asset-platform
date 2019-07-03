package com.asset.entity;

import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
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

    private String formValue;

    public AsFormInst() {
    }

    public AsFormInst(String id, String formValue, String editor) {
        this.id = id;
        this.formValue = formValue;
        this.createdBy = editor;
    }

    public AsFormInst(String id, String formInstJson, String formValue,String editor) {
        this.id = id;
        this.formInstJson = formInstJson;
        this.createdBy = editor;
        this.formValue = formValue;
    }

    public AsFormInst(String createdBy) {
        this.createdBy = createdBy;
    }

    public AsFormInst(String id, String formInstJson) {
        this.id = id;
        this.formInstJson = formInstJson;
    }


    public AsFormInst(String formModelId,
                      String procInstId,
                      String executionId,
                      String taskId,
                      String createdBy,
                      String formInstValue,
                      String formInstJson) {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
        formValue = formInstValue;
        this.formModelId = formModelId;
        this.procInstId = procInstId;
        this.executionId = executionId;
        this.taskId = taskId;
        this.createdBy = createdBy;
        this.formInstJson = formInstJson;
    }
}
