package com.asset.rec;

import com.asset.javabean.FormJson;
import lombok.Data;

import java.util.List;

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
