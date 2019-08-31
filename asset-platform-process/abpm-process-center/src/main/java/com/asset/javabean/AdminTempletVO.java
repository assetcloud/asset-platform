package com.asset.javabean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 返回给控制端界面的内容
 */
@ApiModel(description = "控制端：模板信息")
public class AdminTempletVO {
    @ApiModelProperty(value = "模板Id")
    private String id;

    @ApiModelProperty(value = "应用模板的名称")
    private String templetName;


    @ApiModelProperty(value = "应用模板的图标")
    private String iconCls;


    @ApiModelProperty(value = "应用模板下对应的应用Id")
    private String linkAppId;


    @ApiModelProperty(value = "应用模板下对应的表单模型Id")
    private String linkFormModelId;

    @ApiModelProperty(value = "应用模板下对应的流程模型Id")
    private String linkProcModelId;

    @ApiModelProperty(value = "应用模板状态")
    private Integer status;

    @ApiModelProperty(value = "发布模板的人的Id")
    private String committer;

    @ApiModelProperty(value = "模板发布时间")
    private Date createTime;
}
