package com.asset.mapper;

import com.asset.bean.Application;

public interface ApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);
}