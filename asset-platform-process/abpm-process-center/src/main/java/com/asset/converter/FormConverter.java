package com.asset.converter;

import com.alibaba.fastjson.JSON;
import com.asset.javabean.form.FormSheet;

/**
 * 前台传来的json类型表单信息与表单实体类相互转化
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
public class FormConverter {
    public static FormSheet jsonToEntity(String formJson){
        FormSheet entity = JSON.parseObject(formJson, FormSheet.class);
        return entity;
    }

    public static String entityToJson(FormSheet entity) {
        String jsonString = JSON.toJSONString(entity);
        return jsonString;
    }
}
