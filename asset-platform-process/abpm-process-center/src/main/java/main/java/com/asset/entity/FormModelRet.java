package com.asset.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FormModelRet {
    String form_model_id = "";  //数据库表中ID是int类型，自增，这里暂时用不到
    String form_name = "";
    Timestamp created_time = null;
    String created_by = "";
    Timestamp last_updated_time = null;
    String last_updated_by = "";
    int version ;
    String model_json = "";
    String icon_cls = "";
    int status;
    String proc_model_id;
}
