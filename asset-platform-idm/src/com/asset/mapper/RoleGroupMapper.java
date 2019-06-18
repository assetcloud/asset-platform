package com.asset.mapper;

import com.asset.bean.RoleGroup;

public interface RoleGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleGroup record);

    int insertSelective(RoleGroup record);

    RoleGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleGroup record);

    int updateByPrimaryKey(RoleGroup record);
}