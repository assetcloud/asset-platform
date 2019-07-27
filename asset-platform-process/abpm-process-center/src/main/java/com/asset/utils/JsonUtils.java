package com.asset.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用json-path对json进行解析
 * @author YBY
 */
public class JsonUtils {

    public static HashMap<String,Object> constructHashMap(String jsonStr){
        HashMap<String,Object> hashMap = new HashMap<>();
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            hashMap.put(entry.getKey(),entry.getValue());
        }

        return hashMap;
    }

}
