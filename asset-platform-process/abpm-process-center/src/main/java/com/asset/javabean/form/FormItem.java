package com.asset.javabean.form;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * 基础表单项实体类
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
@Data
public class FormItem {
    String type;
    String name;
    String icon;
    OptionsBase options;
    String key;
    String model;
    List<JSONObject> rules;
    List<JSONObject> columns;
    JSONObject novalid;  //这个属性一般不会出现，只有嵌套在栅格布局中的表单项才会出现这个属性
}
