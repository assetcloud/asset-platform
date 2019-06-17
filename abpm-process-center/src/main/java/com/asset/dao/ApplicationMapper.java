package com.asset.dao;

import com.asset.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApplicationMapper {

    List<Application> getAppList();

    int deleteByPrimaryKey(String id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    Application findAppByName(@Param("applicationName") String applicationName);
}
