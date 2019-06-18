package com.asset.bean;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {

    private Integer id;

    private String uid;

    private Long roleId;

    private Date createdTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", roleId=" + roleId +
                ", createdTime=" + createdTime +
                ", status=" + status +
                '}';
    }
}
