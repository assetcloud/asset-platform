package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收表单实例审批的实体类
 */
@Data
@ApiModel
public class FormInstRecApprove extends FormInstRecBase {
    @ApiModelProperty(value = "表单实例Id",required = true)
    String form_inst_id;    //审批的表单实例ID
    //当前执行的TaskID
    @ApiModelProperty(value = "当前运行的实例任务Id",required = true)
    String task_id;
    //把审批节点上的审批意见作为表单的一部分内容新添加进来！
    //新加审批意见的格式最好是 单行文本 的形式：XXX拒绝/同意了申请。
    @ApiModelProperty(value = "对当前待审阅任务的处理意见，1——同意，0——拒绝",allowableValues = "0,1",required = true)
    int approve_result;     //同意申请 或者 拒绝申请

    //当前审批节点还可以对表单内容进行修改
    @ApiModelProperty(value = "当前填写的表单样式",required = true)
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "填写的表单内容",required = true)
    String form_inst_value; //填写的表单数据信息
    @ApiModelProperty(value = "对应的实例Id",required = true)
    String proc_inst_id;
}
