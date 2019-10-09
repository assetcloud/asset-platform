package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 经办节点
 */
@Data
public class FormInstRecHandle extends FormInstRecBase {
    //待办节点的表单实例ID
    @ApiModelProperty(value = "表单实例Id")
    String form_inst_id;
    @ApiModelProperty(value = "当前运行的实例任务Id")
    String task_id;
    @ApiModelProperty(value = "对应的实例Id")
    String proc_inst_id;
    @ApiModelProperty(value = "当前填写的表单样式")
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "填写的表单内容")
    String form_inst_value; //填写的表单数据信息
}
