package com.asset.utils;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsFormInst;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonUtils {

    /**
     * 返回这样的json数据： {"code": 0}
     * @param code
     * @return
     */
    public static String getCodeJson(int code){
        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code",code);
        Object json = JSONObject.toJSON(map);
        return json.toString();
    }

    /**
     * 创建表单模型、修改表单模型时，需要把前端传入的model_json的字段先解析为String字符串，再存入数据库
     */
    public static String recJsonArrayToString(List<Object> recJsonArray){
        List<Object> model_json_before = recJsonArray;
        Object json = JSONObject.toJSON(model_json_before);
        return json.toString();
    }

    /**
     * selectAll之后得到的对象列表，加上code字段返回给前台解析
     */
    public static String listToJson(ArrayList<Serializable> recJsonArray){
        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", Constants.CODE_SUCCESS);
        map.put("list", recJsonArray);

        Object json = JSONObject.toJSON(map);
        return json.toString();
    }


    public static String procListToString(ArrayList<AsFormInst> asFormInsts)
    {
        int code = Constants.CODE_SUCCESS;
        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", code);
        map.put("list", asFormInsts);
        Object json = JSONObject.toJSON(map);
        return json.toString();
    }
}
