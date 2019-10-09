package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author YBY
 */
@Data
public class RegisterDTO {
    @ApiModelProperty(value = "当前填写的注册表单样式")
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "填写的注册表单内容")
    String form_inst_value;
    @ApiModelProperty(value = "想要注册的人")
    String user_id;
    @ApiModelProperty(value = "注册表单审批流程模型所属的工作场景，这个需要前台给我，可以快速更改")
    String scene_id;
    @ApiModelProperty(value = "该注册审批流程的审核环节可以交给哪些人审批")
    String candidate_user_id;  //审批阶段谁可以审批这个注册请求
    @ApiModelProperty(value = "该注册审批流程的审核环节可以交给该工作场景下哪些部门审批")
    String candidate_section_id; //哪些部门可以审批
}
