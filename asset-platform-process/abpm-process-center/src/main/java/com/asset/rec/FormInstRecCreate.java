package com.asset.rec;

import com.asset.javabean.FormJson;
import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
public class FormInstRecCreate extends FormInstRecBase {
    FormJson form_inst_json;
}
