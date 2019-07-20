package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import com.asset.dto.FormModelEditRec;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FormModel implements Serializable {
    private String id;

    private String formName;

    private Date createdTime;

    private String createdBy;

    private Date lastUpdatedTime;

    private String lastUpdatedBy;

    private Integer version;

    private Integer groupId;

    private String iconCls;

    private Integer status;

    private String procModelId;

    private String modelJson;

    public FormModel() {
    }

    public FormModel(String formName,
                     String createdBy,
                     String iconCls,
                     Integer status,
                     String modelJson) {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
        this.formName = formName;
        this.createdBy = createdBy;
        this.iconCls = iconCls;
        this.status = status;
        this.modelJson = modelJson;
    }


    public FormModel(FormModelEditRec rec) {
        this.id = rec.getForm_model_id();
        this.formName = rec.getForm_name();
        this.iconCls = rec.getIcon_cls();
        String jsonString = JSONObject.toJSONString(rec.getModel_json());
        this.modelJson = jsonString;
        this.groupId = rec.getGroup_id();
    }

    public FormModel(String id, String procModelId) {
        this.id = id;
        this.procModelId = procModelId;
    }

    public FormModel(String id, Integer groupId) {
        this.id = id;
        this.groupId = groupId;
    }
}
