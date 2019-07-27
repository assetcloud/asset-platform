package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
public class FormInstRecCreate extends FormInstRecBase {
    FormSheet form_inst_sheet;
    String form_inst_value; //填写的表单数据信息
}
