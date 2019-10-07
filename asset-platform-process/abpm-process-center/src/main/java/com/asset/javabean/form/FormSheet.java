package com.asset.javabean.form;

import lombok.Data;

import java.util.List;

/**
 * 由前台传来的json类型表单信息转化而成的表单实体类,不带栅格布局
 */
@Data
public class FormSheet {
    private List<FormItem> list;
    private FormConfig config;
}
