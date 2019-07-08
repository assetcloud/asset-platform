package com.asset.common;

import java.util.concurrent.ConcurrentHashMap;

public class GlobalConstant {

    //场景id
    public static String CURRENT_SCENE = "e65edc60-96ee-11e9-ac96-005056c00001";
    /**
     * 存储用户场景
     * ConcurrentHashMap线程安全
     * TODO 转用redis实现
     */
    public static ConcurrentHashMap<String, String> USER_SCENE_MAP = new ConcurrentHashMap<>();
}
