package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AssetFormModel {
    private String id;

    private String formName;

    private Date createdTime;

    private String createdBy;

    private Date lastUpdatedTime;

    private String lastUpdatedBy;

    private Integer version;

    private Integer groupId;

    private String iconCls;

    private Byte status;

    private String procModelId;

    private String modelJson;
}
