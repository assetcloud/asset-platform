package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SceneSelectDTO {
    @ApiModelProperty(value = "当前填写的表单样式，用户要选择加入的工作场景样式写在这里")
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "填写的注册表单内容，用户要选择加入的工作场景值写在这里")
    String form_inst_value;
    @ApiModelProperty(value = "发起流程的人")
    String user_id;
    @ApiModelProperty(value = "当前工作场景选择审批流程模型所属的工作场景是什么，不是用户选择的工作场景")
    String scene_id;
    @ApiModelProperty(value = "该审批流程的审核环节可以交给哪些人审批")
    String candidate_user_id;  //审批阶段谁可以审批这个工作场景申请的请求
    @ApiModelProperty(value = "该审批流程的审核环节可以交给该工作场景下哪些部门审批")
    String candidate_section_id; //哪些部门可以审批
}



