package com.asset.javabean;

import lombok.Data;

import java.util.Date;

/**
 * 未绑定流程模型的表单模型展示对象
 * @author YBY
 */
@Data
public class UnBindFormModelVO {
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

    public UnBindFormModelVO() {
    }
}
