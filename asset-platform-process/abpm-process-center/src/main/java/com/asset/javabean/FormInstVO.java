package com.asset.javabean;

import com.asset.entity.FormInstDO;
import com.asset.service.FormInstService;
import com.asset.service.ProcInstService;
import com.asset.utils.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
public class FormInstVO extends FormInstDO {

    //返回给前台用来展示的表单标题
    String title;
    //用来展示的表单内容
    String content;
    //当前任务节点所处的实例的发起时间
    long commitTime;



    public FormInstVO(FormInstDO doo,String committer) {
        this.formInstValue = doo.getFormInstValue();
        this.formModelId = doo.getFormModelId();
        this.procInstId = doo.getProcInstId();
        this.executionId = doo.getExecutionId();
        this.taskId = doo.getTaskId();
        this.executor = doo.getExecutor();
        this.formInstSheetStr = doo.getFormInstSheetStr();
        this.executeTime = doo.getExecuteTime();
        this.id = doo.getId();
        this.nodeType = doo.getNodeType();
        this.status = doo.getStatus();

        this.title = committer +"发起的表单";
        if(doo.getNodeType() == Constants.AS_NODE_APPLY)
            this.content = "经办节点";
        else if(doo.getNodeType() == Constants.AS_NODE_APPROVE)
            this.content = "审批节点";
        else if(doo.getNodeType() == Constants.AS_NODE_CC)
            this.content = "抄送节点";
        else
            this.content = "其他节点";
    }

    public FormInstVO() {
    }

    private FormInstVO(Builder builder) {
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
        setTitle(builder.title);
        setContent(builder.content);
        setCommitTime(builder.commitTime);
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

        public FormInstVO build() {
            return new FormInstVO(this);
        }
    }
}
