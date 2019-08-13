package com.asset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接收表单实例审批的实体类
 */
@Data
public class FormInstRecApprove extends FormInstRecBase {
    String form_inst_id;    //审批的表单实例ID
    //当前执行的TaskID
    String task_id;
    //把审批节点上的审批意见作为表单的一部分内容新添加进来！
    //新加审批意见的格式最好是 单行文本 的形式：XXX拒绝/同意了申请。
    @ApiModelProperty(value = "对当前待审阅任务的处理意见，1——同意，0——拒绝")
    int approve_result;     //同意申请 或者 拒绝申请
}
