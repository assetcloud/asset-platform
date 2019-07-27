package com.asset.javabean;

import com.asset.entity.FormModelDO;
import lombok.Data;

import java.util.Date;

@Data
public class FormModelBO {
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
    private String modelSheetStr;

    public FormModelBO(FormModelDO formModel) {
        this.id = formModel.getId();
        this.formName = formModel.getFormName();
        this.createdTime = formModel.getCreatedTime();
        this.createdBy = formModel.getCreatedBy();
        this.lastUpdatedTime = formModel.getLastUpdatedTime();
        this.lastUpdatedBy = formModel.getLastUpdatedBy();
        this.version = formModel.getVersion();
        this.groupId = formModel.getGroupId();
        this.iconCls = formModel.getIconCls();
        this.status = formModel.getStatus();
        this.procModelId = formModel.getProcModelId();
        this.modelSheetStr = formModel.getModelSheetStr();
    }
}
