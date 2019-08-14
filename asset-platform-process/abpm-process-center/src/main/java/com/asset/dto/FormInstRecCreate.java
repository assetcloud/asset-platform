package com.asset.dto;

import com.asset.form.FormSheet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
@ApiModel
public class FormInstRecCreate extends FormInstRecBase {
    @ApiModelProperty(value = "流程中第一个节点要填写的表单内容的样式",required = true)
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "流程中第一个节点填写的表单内容",required = true)
    String form_inst_value; //填写的表单数据信息
}
