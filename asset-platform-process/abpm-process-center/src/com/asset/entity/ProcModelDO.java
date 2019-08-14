package com.asset.entity;

import java.util.Date;

public class ProcModelDO {
    private String procModelId;

    private String actId;

    private Integer actType;

    private String candidateUser;

    private String candidateGroup;

    private Date limitTime;

    private Integer overtimeStrategy;

    private String signStrategy;

    private String todoStrategy;

    private Integer ifJointSign;

    public String getProcModelId() {
        return procModelId;
    }

    public void setProcModelId(String procModelId) {
        this.procModelId = procModelId == null ? null : procModelId.trim();
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId == null ? null : actId.trim();
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public String getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(String candidateUser) {
        this.candidateUser = candidateUser == null ? null : candidateUser.trim();
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup == null ? null : candidateGroup.trim();
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    public Integer getOvertimeStrategy() {
        return overtimeStrategy;
    }

    public void setOvertimeStrategy(Integer overtimeStrategy) {
        this.overtimeStrategy = overtimeStrategy;
    }

    public String getSignStrategy() {
        return signStrategy;
    }

    public void setSignStrategy(String signStrategy) {
        this.signStrategy = signStrategy == null ? null : signStrategy.trim();
    }

    public String getTodoStrategy() {
        return todoStrategy;
    }

    public void setTodoStrategy(String todoStrategy) {
        this.todoStrategy = todoStrategy == null ? null : todoStrategy.trim();
    }

    public Integer getIfJointSign() {
        return ifJointSign;
    }

    public void setIfJointSign(Integer ifJointSign) {
        this.ifJointSign = ifJointSign;
    }
}