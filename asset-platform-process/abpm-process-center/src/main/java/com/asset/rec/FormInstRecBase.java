package com.asset.rec;

import com.asset.javabean.FormJson;
import lombok.Data;

@Data
public class FormInstRecBase {
    String editor;          //提交人
    String form_model_id;   //对应的表单实例的模型ID
    String form_inst_value; //填写的表单数据信息
}
