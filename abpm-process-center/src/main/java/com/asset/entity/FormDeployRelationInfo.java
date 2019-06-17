package com.asset.entity;

import com.asset.javabean.UuidIdGenerator;
import com.asset.utils.IDGenerator;

public class FormDeployRelationInfo {
    String id;
    String proc_deploy_id;
    String form_model_id;
    IDGenerator idGenerator = new UuidIdGenerator();

    public FormDeployRelationInfo(String proc_deploy_id, String form_model_id) {
        this.id = idGenerator.generateID();
        this.proc_deploy_id = proc_deploy_id;
        this.form_model_id = form_model_id;
    }

    public String getProc_deploy_id() {
        return proc_deploy_id;
    }

    public void setProc_deploy_id(String proc_deploy_id) {
        this.proc_deploy_id = proc_deploy_id;
    }

    public String getForm_model_id() {
        return form_model_id;
    }

    public void setForm_model_id(String form_model_id) {
        this.form_model_id = form_model_id;
    }

    @Override
    public String toString() {
        return "FormDeployRelationInfo{" +
                "proc_deploy_id='" + proc_deploy_id + '\'' +
                ", form_model_id='" + form_model_id + '\'' +
                '}';
    }
}
