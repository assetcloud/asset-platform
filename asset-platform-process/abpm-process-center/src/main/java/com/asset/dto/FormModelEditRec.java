package com.asset.dto;

import com.asset.form.FormJson;
import lombok.Data;

/**
 * 对表单模型进行修改
 */
@Data
public class FormModelEditRec {
    String form_model_id;
    String form_name ;
    FormJson model_json ;
    int group_id ;
    String icon_cls;
}
