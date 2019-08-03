package com.asset.entity;

import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import lombok.Data;

import java.util.Date;

/**
 * @author
 */
@Data
public class FormInstDO {
    protected String id;
    protected String formModelId;
    protected String procInstId;
    protected String executionId;
    protected String taskId;
    protected Date executeTime;
    protected String executor;
    protected String formInstSheetStr;
    protected int status;
    protected String formInstValue;
    protected Integer nodeType;
    protected Integer approveResult;

    public FormInstDO() {
    }

    public FormInstDO(String id, String executor) {
        this.id = id;
        this.executor = executor;
    }

    public FormInstDO(String id, String formInstSheetStr, String formInstValue, String editor) {
        this.id = id;
        this.formInstSheetStr = formInstSheetStr;
        this.executor = editor;
        this.formInstValue = formInstValue;
    }

    public FormInstDO(String procInstId, String id, String formInstSheetStr, String formInstValue, String editor) {
        this.procInstId = procInstId;
        this.id = id;
        this.formInstSheetStr = formInstSheetStr;
        this.executor = editor;
        this.formInstValue = formInstValue;
    }

    public FormInstDO(String executor) {
        this.executor = executor;
    }

    public FormInstDO(String formModelId,
                      String procInstId,
                      String executionId,
                      String taskId,
                      String executor,
                      String formInstValue,
                      String formInstSheetStr) {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
        this.formInstValue = formInstValue;
        this.formModelId = formModelId;
        this.procInstId = procInstId;
        this.executionId = executionId;
        this.taskId = taskId;
        this.executor = executor;
        this.formInstSheetStr = formInstSheetStr;
    }

    public FormInstDO(String formInstId, int approveResult, String editor) {
        this.id = formInstId;
        this.approveResult = approveResult;
        this.executor =editor;
    }
}
