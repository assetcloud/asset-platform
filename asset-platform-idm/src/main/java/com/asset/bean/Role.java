package com.asset.bean;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable{

    private Integer id;

    private String roleName;

    private String roleNameZh;

    private String roleDescription;

    private Boolean status;

    private String applicableUnitLevel;

    private String productCode;

    private Date enableTime;

    private Date disableTime;

    private Date updatedTime;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleNameZh() {
        return roleNameZh;
    }

    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh == null ? null : roleNameZh.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getApplicableUnitLevel() {
        return applicableUnitLevel;
    }

    public void setApplicableUnitLevel(String applicableUnitLevel) {
        this.applicableUnitLevel = applicableUnitLevel == null ? null : applicableUnitLevel.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public Date getEnableTime() {
        return enableTime;
    }

    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleNameZh='" + roleNameZh + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", status=" + status +
                ", applicableUnitLevel='" + applicableUnitLevel + '\'' +
                ", productCode='" + productCode + '\'' +
                ", enableTime=" + enableTime +
                ", disableTime=" + disableTime +
                ", updatedTime=" + updatedTime +
                ", createdTime=" + createdTime +
                '}';
    }
}
