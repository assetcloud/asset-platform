package com.asset.mapper;

public interface OrganTreeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrganTree record);

    int insertSelective(OrganTree record);

    OrganTree selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrganTree record);

    int updateByPrimaryKey(OrganTree record);
}
