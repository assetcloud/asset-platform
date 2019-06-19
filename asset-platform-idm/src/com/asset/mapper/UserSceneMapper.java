package com.asset.mapper;

import com.asset.bean.UserScene;

public interface UserSceneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserScene record);

    int insertSelective(UserScene record);

    UserScene selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScene record);

    int updateByPrimaryKey(UserScene record);
}