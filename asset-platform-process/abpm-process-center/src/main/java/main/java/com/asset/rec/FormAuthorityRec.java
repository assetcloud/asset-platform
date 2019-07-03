package com.asset.rec;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yby
 * @time 190603 1651
 * @version 1.0_190603 1651
 */
public class FormAuthorityRec {
    String form_model_id;
    String model_id;
    String node_id;
    String created_by;
    Map<String,Integer> authority_json;

    public FormAuthorityRec() {
    }

    public FormAuthorityRec(String form_model_id, String model_id, String node_id, String created_by, Map<String, Integer> authority_json) {
        this.form_model_id = form_model_id;
        this.model_id = model_id;
        this.node_id = node_id;
        this.created_by = created_by;
        this.authority_json = authority_json;
    }

    public String getForm_model_id() {
        return form_model_id;
    }

    public void setForm_model_id(String form_model_id) {
        this.form_model_id = form_model_id;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Map<String, Integer> getAuthority_json() {
        return authority_json;
    }

    public void setAuthority_json(Map<String, Integer> authority_json) {
        this.authority_json = authority_json;
    }

    @Override
    public String toString() {
        return "FormAuthorityRec{" +
                "formModelId='" + form_model_id + '\'' +
                ", model_id='" + model_id + '\'' +
                ", node_id='" + node_id + '\'' +
                ", created_by='" + created_by + '\'' +
                ", authority_json=" + authority_json +
                '}';
    }
}
