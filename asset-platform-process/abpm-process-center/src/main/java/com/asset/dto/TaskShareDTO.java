package com.asset.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskShareDTO {
    String taskId;

    @ApiModelProperty(value = "当前用户Id", required = true)
    String userId;

    @ApiModelProperty(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
    String sectionId;

    @ApiModelProperty(value = "当前用户在当前工作场景下所属的部门中的所有用户Id，用于在任务节点指定当前部门时的筛选", required = true)
    String curSectionUsers;

}
