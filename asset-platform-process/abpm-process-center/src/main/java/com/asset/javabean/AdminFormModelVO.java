package com.asset.javabean;

import lombok.Data;

import java.util.Date;

/**
 * 控制端表单模型
 * @author YBY
 */
@Data
public class AdminFormModelVO {
    private String id;
    private String sceneId;
    private String formName;
    private String createdBy;
    private Integer version;
    private Integer groupId;
    private String iconCls;
    private Integer status;
    private String procModelId;
    private String modelSheetStr;

    private long createdTime;

    public AdminFormModelVO() {
    }
}
