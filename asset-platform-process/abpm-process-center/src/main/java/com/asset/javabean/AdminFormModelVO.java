package com.asset.javabean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 控制端表单模型
 * @author YBY
 */
@Data
@ApiModel(description= "控制台端表单模型展示信息")
public class AdminFormModelVO {
    @ApiModelProperty(value = "表单模型ID")
    private String id;
    @ApiModelProperty(value = "工作场景")
    private String sceneId;
    @ApiModelProperty(value = "表单名称")
    private String formName;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    private Integer version;
    @ApiModelProperty(value = "分组Id")
    private Integer groupId;
    private String iconCls;
    private Integer status;
    private String procModelId;
    private String modelSheet;
    private String appId;

    @ApiModelProperty(value = "表单模型创建时间：时间戳格式")
    private long createdTime;

    private String appName;
    private String groupName;


    public AdminFormModelVO() {
    }
}
