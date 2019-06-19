package com.asset.mapper;

import com.asset.bean.Scene;

public interface SceneMapper {
    int deleteByPrimaryKey(String id);

    int insert(Scene record);

    int insertSelective(Scene record);

    Scene selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);
}