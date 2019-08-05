package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

/**
 * 对表单模型进行修改
 */
@Data
public class FormModelEditDTO {
    String form_model_id;
    String form_name ;
    FormSheet form_sheet;
    int group_id ;
    String icon_cls;
}