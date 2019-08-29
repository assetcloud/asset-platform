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
    private String modelSheet;
    private String sceneId;
    private String appId;

    public FormModelBO() {
    }
}
