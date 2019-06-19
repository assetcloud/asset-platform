package com.asset.mapper;

public interface SceneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Scene record);

    int insertSelective(Scene record);

    Scene selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);
}
