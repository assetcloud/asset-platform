package com.asset.entity;

import com.asset.javabean.UuidIdGenerator;
import com.asset.rec.FormModelEditRec;
import com.asset.utils.IDGenerator;
import com.asset.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 与数据库交互的表单模型entity
 * @author yby
 * @time 190523 1544
 * @version 1.0_190523 1544
 */
@Data
public class FormModelInfo implements Serializable {

    String form_model_id;  //数据库表中ID是int类型，自增，这里暂时用不到
    String form_name;
    Timestamp created_time = null;
    String created_by;
    Timestamp last_updated_time = null;
    String last_updated_by;
    int version ;
    String model_json;
    String group_id;
    String icon_cls;
    int status;
    String proc_model_id;

    public FormModelInfo(String form_name, String created_by, String model_json,String icon_cls, int status) {
        IDGenerator generator = new UuidIdGenerator();
        form_model_id = generator.generateID();
        this.form_name = form_name;
        this.icon_cls = icon_cls;
        this.created_by = created_by;
        this.model_json = model_json;
        this.status = status;
    }

    public FormModelInfo(String form_model_id, String form_name, String model_json, String group_id, String icon_cls) {
        this.form_model_id = form_model_id;
        this.form_name = form_name;
        this.model_json = model_json;
        this.group_id = group_id;
        this.icon_cls = icon_cls;
    }

    public FormModelInfo(FormModelEditRec rec) {
        this.form_model_id = rec.getForm_model_id();
        this.form_name = rec.getForm_name();
        this.model_json = JsonUtils.recJsonArrayToString(rec.getModel_json());
        this.group_id = rec.getGroup_id();
        this.icon_cls = rec.getIcon_cls();
    }

    /**
     * 修改表单模型分组用
     * @param form_model_id
     * @param group_id
     */
    public FormModelInfo(String form_model_id, String group_id) {
        this.form_model_id = form_model_id;
        this.group_id = group_id;
    }
}
