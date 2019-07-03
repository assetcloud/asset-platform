package com.asset.entity;

import com.asset.javabean.UuidIdGenerator;
import com.asset.rec.FormModelEditRec;
import com.asset.utils.IDGenerator;
import com.asset.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AsFormModel implements Serializable {
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

    public AsFormModel() {
    }

    public AsFormModel(String formName,
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


    public AsFormModel(FormModelEditRec rec) {
        this.id = rec.getForm_model_id();
        this.formName = rec.getForm_name();
        this.iconCls = rec.getIcon_cls();
        this.modelJson = JsonUtils.recJsonArrayToString(rec.getModel_json());
        this.groupId = rec.getGroup_id();
    }

    public AsFormModel(String id, String procModelId) {
        this.id = id;
        this.procModelId = procModelId;
    }

    public AsFormModel(String id, Integer groupId) {
        this.id = id;
        this.groupId = groupId;
    }
}
