package com.asset.form;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.OptionsBase;
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
}
