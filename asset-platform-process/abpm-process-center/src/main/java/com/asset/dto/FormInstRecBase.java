package com.asset.dto;

import lombok.Data;

@Data
public class FormInstRecBase {
    String editor;          //提交人
    String form_model_id;   //对应的表单实例的模型ID
//    String form_inst_sheet; //每个节点对应的表单样式不一样
//    String form_inst_value; //填写的表单数据信息
}
