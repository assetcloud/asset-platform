package com.asset.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 待阅节点前台传来的信息
 */
@Data
@ApiModel
public class FormInstRecReadle extends FormInstRecBase{
    @ApiModelProperty(value = "当前填写的表单样式")
    String form_inst_id;
    @ApiModelProperty(value = "对应的实例Id")
    String proc_inst_id;
    @ApiModelProperty(value = "当前运行的实例任务Id")
    String task_id;
}
