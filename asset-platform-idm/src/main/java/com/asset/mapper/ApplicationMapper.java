package com.asset.mapper;

import com.asset.bean.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ApplicationMapper {

    List<Application> getAppList();

    int deleteByPrimaryKey(Long id);

    int insert(Application record);

    int insertSelective(Application record);

    Application selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Application record);

    int updateByPrimaryKey(Application record);

    Application findAppByName(@Param("applicationName") String applicationName);
}
