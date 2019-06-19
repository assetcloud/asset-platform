package com.asset.mapper;

public interface OrganSceneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrganScene record);

    int insertSelective(OrganScene record);

    OrganScene selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrganScene record);

    int updateByPrimaryKey(OrganScene record);
}
