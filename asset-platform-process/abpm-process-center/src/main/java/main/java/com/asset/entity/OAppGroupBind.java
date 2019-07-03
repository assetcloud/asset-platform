package com.asset.entity;

import java.io.Serializable;

public class OAppGroupBind implements Serializable {
    private Integer groupId;

    private String oappId;

    private String groupName;

    private Integer status;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getOappId() {
        return oappId;
    }

    public void setOappId(String oappId) {
        this.oappId = oappId == null ? null : oappId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
