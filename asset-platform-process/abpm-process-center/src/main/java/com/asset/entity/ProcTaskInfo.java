package com.asset.entity;

import java.io.Serializable;

/**
 * @author lichao
 */
public class ProcTaskInfo implements Serializable {

    /**
     * 流程定义名称
     */
    private String name_;
    /**
     * 任务Id
     */
    private String taskId;
    /**
     * 流程实例Id
     */
    private String procInstId;
    /**
     * 流程定义Id
     */
    private String procDefId;
    /**
     * 当前节点Id
     */
    private String actId;
    /**
     * 当前节点名称
     */
    private String actName;
    /**
     * 任务处理人
     */
    private String assignee;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 结束时间
     */
    private String endTime;

    public ProcTaskInfo() {
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
