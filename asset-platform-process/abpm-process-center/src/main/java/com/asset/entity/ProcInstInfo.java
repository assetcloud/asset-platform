package com.asset.entity;

import java.io.Serializable;

/**
 * @author lichao
 */
public class ProcInstInfo implements Serializable {

    /**
     * 执行实例Id
     */
    private String id_;
    /**
     * 流程定义名称
     */
    private String name_;
    /**
     * 流程实例Id
     */
    private String procInstId;
    /**
     * 业务Id
     */
    private String bizId;
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
     * 开始时间
     */
    private String startTime;

    public ProcInstInfo() {
    }

    public String getId_() {
        return id_;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
