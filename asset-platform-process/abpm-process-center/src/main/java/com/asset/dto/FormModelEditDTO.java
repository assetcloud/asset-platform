package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 对表单模型进行修改
 */
@Data
@ApiModel
public class FormModelEditDTO {
    @NotNull(message = "表单模型Id不能为空")
    @ApiModelProperty(value = "要修改的表单模型Id",required = true)
    String form_model_id;
    @ApiModelProperty(value = "修改后的表单名称")
    String form_name ;
    @ApiModelProperty(value = "修改后的表单样式")
    FormSheet form_sheet;
    @ApiModelProperty(value = "修改后的分组Id")
    int group_id ;
    @ApiModelProperty(value = "修改后的图标")
    String icon_cls;
}
