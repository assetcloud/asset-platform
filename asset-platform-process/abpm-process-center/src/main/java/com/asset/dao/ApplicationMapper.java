package com.asset.dao;

import com.asset.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ApplicationMapper {

    List<Application> getAppList();

    int deleteByPrimaryKey(String id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    Application findAppByName(@Param("applicationName") String applicationName);

    List<Application> getPublishedApp();
}
