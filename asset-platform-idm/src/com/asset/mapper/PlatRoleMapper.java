package com.asset.mapper;

import com.asset.bean.PlatRole;

public interface PlatRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlatRole record);

    int insertSelective(PlatRole record);

    PlatRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatRole record);

    int updateByPrimaryKey(PlatRole record);
}