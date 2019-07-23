package com.asset.common;

import java.util.concurrent.ConcurrentHashMap;

public class GlobalConstant {

    public static ConcurrentHashMap<String, String> USER_SCENE_MAP = new ConcurrentHashMap<>();

    public static void put(String key, String value){
        USER_SCENE_MAP.put(key, value);
    }
}
