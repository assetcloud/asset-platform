package com.asset.javabean;

import com.asset.entity.AsTaskDO;
import lombok.Data;

import java.util.Date;


@Data
public class AsTaskVO extends AsTaskDO {
    //返回给前台用来展示的表单标题
    String title;
    //用来展示的表单内容
    String content;
    //当前任务节点所处的实例的发起时间
    long commitTime;

    private AsTaskVO(Builder builder) {
        setId(builder.id);
        setFormModelId(builder.formModelId);
        setProcInstId(builder.procInstId);
        setExecutionId(builder.executionId);
        setExecutor(builder.executor);
        setExecuteTime(builder.executeTime);
        setFormInstSheet(builder.formInstSheet);
        setFormInstValue(builder.formInstValue);
        setStatus(builder.status);
        setNodeType(builder.nodeType);
        setApproveResult(builder.approveResult);
        setTitle(builder.title);
        setContent(builder.content);
        setCommitTime(builder.commitTime);
    }


    public static final class Builder {
        private String id;
        private String formModelId;
        private String procInstId;
        private String executionId;
        private String executor;
        private Date executeTime;
        private String formInstSheet;
        private String formInstValue;
        private Integer status;
        private Integer nodeType;
        private Integer approveResult;
        private String title;
        private String content;
        private long commitTime;

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

        public Builder executor(String val) {
            executor = val;
            return this;
        }

        public Builder executeTime(Date val) {
            executeTime = val;
            return this;
        }

        public Builder formInstSheet(String val) {
            formInstSheet = val;
            return this;
        }

        public Builder formInstValue(String val) {
            formInstValue = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
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

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder commitTime(long val) {
            commitTime = val;
            return this;
        }

        public AsTaskVO build() {
            return new AsTaskVO(this);
        }
    }
}
