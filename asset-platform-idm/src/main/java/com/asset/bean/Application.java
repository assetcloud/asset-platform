package com.asset.bean;

import java.util.Date;

public class Application {

    private String id;
    private String applicationName;
    private String iconCls;
    private Integer status;
    private Integer isPublished;
    private Date createdTime;
    private Date disableTime;
    private Date removeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName == null ? null : applicationName.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", status=" + status +
                ", isPublished=" + isPublished +
                ", createdTime=" + createdTime +
                ", disableTime=" + disableTime +
                ", removeTime=" + removeTime +
                '}';
    }
}
