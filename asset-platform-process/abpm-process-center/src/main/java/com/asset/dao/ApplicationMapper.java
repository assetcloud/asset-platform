package com.asset.dao;

import com.asset.entity.ApplicationDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ApplicationMapper {

    List<ApplicationDO> getAppList();

    int deleteByPrimaryKey(String id);

    int insert(ApplicationDO record);

    int insertSelective(ApplicationDO record);

    ApplicationDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApplicationDO record);

    int updateByPrimaryKey(ApplicationDO record);

    ApplicationDO findAppByName(@Param("applicationName") String applicationName);

    List<ApplicationDO> getPublishedApp();

    ApplicationDO selectOne(String appId);
}
