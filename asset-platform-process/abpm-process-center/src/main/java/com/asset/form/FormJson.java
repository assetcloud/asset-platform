package com.asset.form;

import com.asset.form.FormConfig;
import lombok.Data;

import java.util.List;

/**
 * 表单模型类
 */
@Data
public class FormJson {
    List<FormItem> list;
    FormConfig config;
}
