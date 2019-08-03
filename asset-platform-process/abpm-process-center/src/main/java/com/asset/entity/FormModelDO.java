package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import com.asset.dto.FormModelEditDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FormModelDO implements Serializable {
    private String id;
    private String sceneId;
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
    private String modelSheetStr;

    public FormModelDO() {
    }

    public FormModelDO(String id, String formName,  String createdBy, Integer version, Integer groupId, Integer status, String procModelId, String modelSheetStr) {
        this.id = id;
        this.formName = formName;
        this.createdBy = createdBy;
        this.version = version;
        this.groupId = groupId;
        this.status = status;
        this.procModelId = procModelId;
        this.modelSheetStr = modelSheetStr;
    }

    public FormModelDO(String formName,
                       String createdBy,
                       String iconCls,
                       Integer status,
                       String modelSheetStr) {
        IDGenerator generator = new UuidIdGenerator();
        id = generator.generateID();
        this.formName = formName;
        this.createdBy = createdBy;
        this.iconCls = iconCls;
        this.status = status;
        this.modelSheetStr = modelSheetStr;
    }


    public FormModelDO(FormModelEditDTO rec) {
        this.id = rec.getForm_model_id();
        this.formName = rec.getForm_name();
        this.iconCls = rec.getIcon_cls();
        String jsonString = JSONObject.toJSONString(rec.getForm_sheet());
        this.modelSheetStr = jsonString;
        this.groupId = rec.getGroup_id();
    }

    public FormModelDO(String id, String procModelId) {
        this.id = id;
        this.procModelId = procModelId;
    }

    public FormModelDO(String id, Integer groupId) {
        this.id = id;
        this.groupId = groupId;
    }
}
