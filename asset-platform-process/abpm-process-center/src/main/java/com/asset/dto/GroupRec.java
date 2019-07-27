package com.asset.dto;

import lombok.Data;

@Data
public class GroupRec {
    String app_id;      //分组所属应用ID
    String group_name;        //分组名称
    int group_id;    //重命名的时候需要指定这个值

    public GroupRec() {
    }

    public GroupRec(String app_id, String name) {
        this.app_id = app_id;
        this.group_name = name;
    }

    public GroupRec(String app_id, String name, int group_id) {
        this.app_id = app_id;
        this.group_name = name;
        this.group_id = group_id;
    }
}
