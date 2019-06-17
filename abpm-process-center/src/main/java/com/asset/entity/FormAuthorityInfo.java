package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.UuidIdGenerator;
import com.asset.rec.FormAuthorityRec;
import com.asset.utils.IDGenerator;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * 由前台传过来的FormAuthorityRecEntity加上其他必要信息组装成这个类，传给后台
 * @author yby
 * @time 190603 2101
 * @version 1.0_190603 2101
 */
public class FormAuthorityInfo {
    String id = "";
    String form_model_id ="";
    String model_id = "";
    String node_id ="";
    Timestamp created_time = null;
    String created_by ="";
    String authority_json = "";
    IDGenerator generator = new UuidIdGenerator();

    public FormAuthorityInfo(String id, String form_model_id, String model_id, String node_id, Timestamp created_time, String created_by, String authority_json) {
        this.id = id;
        this.form_model_id = form_model_id;
        this.model_id = model_id;
        this.node_id = node_id;
        this.created_time = created_time;
        this.created_by = created_by;
        this.authority_json = authority_json;
    }

    public FormAuthorityInfo(FormAuthorityRec authoRec) {
        this.id = generator.generateID();
        this.form_model_id = authoRec.getForm_model_id();
        this.model_id = authoRec.getModel_id();
        this.node_id = authoRec.getNode_id();
        this.created_by = authoRec.getCreated_by();

        HashMap<String,Integer> temp = (HashMap<String, Integer>) authoRec.getAuthority_json();
        Object json = JSONObject.toJSON(temp);
        this.authority_json = json.toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getAuthority_json() {
        return authority_json;
    }

    public void setAuthority_json(String authority_json) {
        this.authority_json = authority_json;
    }

    @Override
    public String toString() {
        return "FormAuthorityInfo{" +
                "id='" + id + '\'' +
                ", form_model_id='" + form_model_id + '\'' +
                ", model_id='" + model_id + '\'' +
                ", node_id='" + node_id + '\'' +
                ", created_time=" + created_time +
                ", created_by='" + created_by + '\'' +
                ", authority_json='" + authority_json + '\'' +
                '}';
    }
}
