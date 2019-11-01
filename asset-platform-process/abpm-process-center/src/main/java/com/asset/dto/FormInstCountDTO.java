package com.asset.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@ApiModel("获取当前用户分配到的任务数目")
public class FormInstCountDTO {
    @ApiParam(value = "当前用户Id", required = true)
    String userId;

    @ApiParam(value = "当前用户登录时选择的工作场景Id", required = true)
    String sceneId;

    @ApiParam(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
    String sectionId;

    @ApiParam(value = "当前用户在当前工作场景下所属的部门中的所有用户Id，用于在任务节点指定当前部门时的筛选", required = true)
    String curSectionUsers;
}
