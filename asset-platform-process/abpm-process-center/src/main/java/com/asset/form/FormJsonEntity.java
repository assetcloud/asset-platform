package com.asset.form;

import lombok.Data;

import java.util.List;

/**
 * 由前台传来的json类型表单信息转化而成的表单实体类
 */
@Data
public class FormJsonEntity {
    private List<FormItem> list;
    private FormConfig config;
}
