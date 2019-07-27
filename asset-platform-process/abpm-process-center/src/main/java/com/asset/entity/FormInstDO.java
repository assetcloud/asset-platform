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
    private String id;

    private String formModelId;

    private String procInstId;

    private String executionId;

    private String taskId;

    private Date executeTime;

    private String executor;

    private String formInstSheetStr;

    private int status;

    private String formInstValue;

    private Integer nodeType;

    private Integer approveResult;

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
