package com.asset.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class GroupCreateDTO {
    String app_id;      //分组所属应用ID
    String group_name;        //分组名称
}
