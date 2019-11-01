package com.asset.dto;

import com.asset.utils.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@ApiModel("获取当前用户分配到的任务信息")
public class FormInstListDTO {
    @ApiModelProperty(value = "当前用户Id", required = true)
    String userId;

    @ApiModelProperty(value = "当前用户登录时选择的工作场景Id", required = true)
    String sceneId;

    @ApiModelProperty(value = "当前用户在当前工作场景下所属的部门Id，这个信息需要向组织架构请求获取", required = true)
    String sectionId;

    @ApiModelProperty(value = "当前用户在当前工作场景下所属的部门中的所有用户Id，用于在任务节点指定当前部门时的筛选", required = true)
    String curSectionUsers;

    @ApiModelProperty(value = "筛选出的任务节点数目")
    Integer num;

    @ApiModelProperty(value = "当前要查看的任务节点类型,0——待办任务,1——待阅任务,2——全部任务", required = true, allowableValues = "0,1,2")
    Integer taskType;

//    int page;
//    int size;
}
