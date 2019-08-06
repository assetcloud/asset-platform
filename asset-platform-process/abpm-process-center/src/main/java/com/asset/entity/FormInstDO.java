package com.asset.entity;

import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

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

    private FormInstDO(Builder builder) {
        setId(builder.id);
        setFormModelId(builder.formModelId);
        setProcInstId(builder.procInstId);
        setExecutionId(builder.executionId);
        setTaskId(builder.taskId);
        setExecuteTime(builder.executeTime);
        setExecutor(builder.executor);
        setFormInstSheetStr(builder.formInstSheetStr);
        setStatus(builder.status);
        setFormInstValue(builder.formInstValue);
        setNodeType(builder.nodeType);
        setApproveResult(builder.approveResult);
    }


    public static final class Builder {
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

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder formModelId(String val) {
            formModelId = val;
            return this;
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder executeTime(Date val) {
            executeTime = val;
            return this;
        }

        public Builder executor(String val) {
            executor = val;
            return this;
        }

        public Builder formInstSheetStr(String val) {
            formInstSheetStr = val;
            return this;
        }

        public Builder status(int val) {
            status = val;
            return this;
        }

        public Builder formInstValue(String val) {
            formInstValue = val;
            return this;
        }

        public Builder nodeType(Integer val) {
            nodeType = val;
            return this;
        }

        public Builder approveResult(Integer val) {
            approveResult = val;
            return this;
        }

        public FormInstDO build() {
            if(id==null)
            {
                IDGenerator generator = new UuidIdGenerator();
                id = generator.generateID();
            }
            return new FormInstDO(this);
        }
    }
}
