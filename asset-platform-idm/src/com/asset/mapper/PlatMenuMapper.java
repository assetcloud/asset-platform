package com.asset.mapper;

import com.asset.bean.PlatMenu;

public interface PlatMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatMenu record);

    int insertSelective(PlatMenu record);

    PlatMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatMenu record);

    int updateByPrimaryKey(PlatMenu record);
}