package com.asset.entity;

/**
 * @author yby
 * @time 190603 1553
 * @version 1.0_190603 1553
 */
public class FormBindInfo {
    String formModelID;
    String procModelID;

    public FormBindInfo(String formModelID, String procModelID) {
        this.formModelID = formModelID;
        this.procModelID = procModelID;
    }

    public String getFormModelID() {
        return formModelID;
    }

    public void setFormModelID(String formModelID) {
        this.formModelID = formModelID;
    }

    public String getProcModelID() {
        return procModelID;
    }

    public void setProcModelID(String procModelID) {
        this.procModelID = procModelID;
    }

    @Override
    public String toString() {
        return "FormBindInfo{" +
                "formModelID='" + formModelID + '\'' +
                ", procModelID='" + procModelID + '\'' +
                '}';
    }
}
