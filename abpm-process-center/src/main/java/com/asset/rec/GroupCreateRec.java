package com.asset.rec;

public class GroupCreateRec {
    String oapp_id;
    String name;

    public GroupCreateRec() {
    }

    public GroupCreateRec(String oapp_id, String name) {
        this.oapp_id = oapp_id;
        this.name = name;
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

    @Override
    public String toString() {
        return "GroupCreateRec{" +
                "oapp_id='" + oapp_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
