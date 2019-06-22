package com.asset.rec;

import lombok.Data;

@Data
public class BaseFormInstRec {
    String form_inst_json;  //提交的表单实例json信息
    String editor;          //提交人
    String form_model_id;   //生成的表单实例的模型ID
}
