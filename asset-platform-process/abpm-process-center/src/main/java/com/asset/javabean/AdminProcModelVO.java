package com.asset.javabean;

import com.asset.entity.ActDeModel;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class AdminProcModelVO  {

    private String id;
    private String name;
    private String modelKey;
    private String description;
    private String modelComment;
    private String createdBy;
    private String lastUpdatedBy;
    private Integer version;
    private String modelEditorJson;
    private Blob thumbnail;
    private Integer modelType;
    private String tenantId;


    private long created;
    private long lastUpdated;

    public AdminProcModelVO() {
    }
}
