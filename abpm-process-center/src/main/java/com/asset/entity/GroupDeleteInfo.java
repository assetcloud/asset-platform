package com.asset.entity;

public class GroupDeleteInfo {
    String groupID;
    int disableParam;

    public GroupDeleteInfo() {
    }

    public GroupDeleteInfo(String groupID, int disableParam) {
        this.groupID = groupID;
        this.disableParam = disableParam;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public int getDisableParam() {
        return disableParam;
    }

    public void setDisableParam(int disableParam) {
        this.disableParam = disableParam;
    }
}
