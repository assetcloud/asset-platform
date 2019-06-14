package com.asset.mapper;

import com.asset.bean.ModuleFunc;

public interface ModuleFuncMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleFunc record);

    int insertSelective(ModuleFunc record);

    ModuleFunc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleFunc record);

    int updateByPrimaryKey(ModuleFunc record);
}