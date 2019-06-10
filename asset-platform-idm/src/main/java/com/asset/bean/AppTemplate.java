package com.asset.bean;

import java.util.Date;

public class AppTemplate {

    private String id;

    private String applicationId;

    private Boolean status;

    private Date publishTime;

    private Date disableTime;

    private String publishAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public String getPublishAccount() {
        return publishAccount;
    }

    public void setPublishAccount(String publishAccount) {
        this.publishAccount = publishAccount == null ? null : publishAccount.trim();
    }

    @Override
    public String toString() {
        return "AppTemplate{" +
                "id='" + id + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", status=" + status +
                ", publishTime=" + publishTime +
                ", disableTime=" + disableTime +
                ", publishAccount='" + publishAccount + '\'' +
                '}';
    }
}
