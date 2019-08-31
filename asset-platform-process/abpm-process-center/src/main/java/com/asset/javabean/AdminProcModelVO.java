package com.asset.javabean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Blob;

@Data
@ApiModel(description = "控制端返回的流程模型信息")
public class AdminProcModelVO  {

    private String id;
    private String name;
    private String modelKey;
    @ApiModelProperty(value = "流程模型描述")
    private String description;
    private String modelComment;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    private String lastUpdatedBy;
    private Integer version;
    private String modelEditorJson;
    private Blob thumbnail;
    @ApiModelProperty(value = "模型类型")
    private Integer modelType;
    private String tenantId;

    @ApiModelProperty(value = "创建时间（时间戳格式）")
    private long created;
    private long lastUpdated;

    public AdminProcModelVO() {
    }
}
