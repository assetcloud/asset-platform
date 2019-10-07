package com.asset.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.form.FormItem;
import com.asset.javabean.form.ItemRuleRequired;

import java.util.List;

/**
 * 用于封装对表单的操作
 * @author YBY
 */
public class FormUtils {


    public static void addRequiredRule(FormItem formItem) {
        List<JSONObject> rules = formItem.getRules();

        ItemRuleRequired required = new ItemRuleRequired("此项必填");
        JSONObject object = (JSONObject) JSON.toJSON(required);
        rules.add(object);
    }



}
