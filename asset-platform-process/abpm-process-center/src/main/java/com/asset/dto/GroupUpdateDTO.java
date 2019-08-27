package com.asset.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
public class GroupUpdateDTO {
    int group_id;    //重命名的时候需要指定这个值
    String app_id;      //分组所属应用ID
    String group_name;        //分组名称
}
