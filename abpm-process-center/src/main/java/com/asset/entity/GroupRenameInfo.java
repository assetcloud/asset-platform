package com.asset.entity;

public class GroupRenameInfo {
    String newName;
    String groupID;

    public GroupRenameInfo() {
    }

    public GroupRenameInfo(String newName, String groupID) {
        this.newName = newName;
        this.groupID = groupID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
