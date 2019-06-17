package com.asset.rec;

import lombok.Data;

import java.util.List;

/**
 * 对表单模型进行修改
 */
@Data
public class FormModelEditRec {
    String form_model_id = "";
    String form_name = "";
    List<Object> model_json = null;
    String group_id = "";
    String icon_cls = "";
}
