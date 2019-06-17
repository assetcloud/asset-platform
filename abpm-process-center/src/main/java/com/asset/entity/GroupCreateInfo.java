package com.asset.entity;

public class GroupCreateInfo {
    String oapp_id;
    String name;
    int status;

    public GroupCreateInfo() {
    }

    public GroupCreateInfo(String oapp_id, String name, int status) {
        this.oapp_id = oapp_id;
        this.name = name;
        this.status = status;
    }

    public String getOapp_id() {
        return oapp_id;
    }

    public void setOapp_id(String oapp_id) {
        this.oapp_id = oapp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
