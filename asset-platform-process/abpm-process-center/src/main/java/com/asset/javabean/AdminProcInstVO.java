package com.asset.javabean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用来在控制端显示的流程实例信息
 * @author YBY
 */
@Data
@ApiModel
public class AdminProcInstVO {
    String procInstId;
    String procInstName;
    @ApiModelProperty(value = "0——运行中，1——挂起，2——被删除，3——已完成，4——审批被拒绝")
    Integer status;
    long commitTime;
    String committer;
    String bindFormModelId;

    public AdminProcInstVO() {
    }



}
