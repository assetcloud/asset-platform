package com.asset.mapper;

import com.asset.bean.Application;

public interface ApplicationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    Application findAppByName(@Param("applicationName") String applicationName);
}
