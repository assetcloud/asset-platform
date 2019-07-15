package com.asset.converter;

import com.alibaba.fastjson.JSON;
import com.asset.form.FormConfig;
import com.asset.form.FormJsonEntity;

/**
 * 前台传来的json类型表单信息与表单实体类相互转化
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
public class FormConverter {
    public static FormJsonEntity jsonToEntity(String formJson){
        FormJsonEntity entity = JSON.parseObject(formJson, FormJsonEntity.class);
        return entity;
    }

    public static String entityToJson(FormJsonEntity entity) {
        String jsonString = JSON.toJSONString(entity);
        return jsonString;
    }
}
