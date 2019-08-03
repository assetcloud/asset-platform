package com.asset.dao;


import com.asset.entity.AppFormBindDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 应用与表单模型绑定的DAO层
 */
@Repository
public interface AppFormBindMapper {
    int deleteByPrimaryKey(String appId);

    int insert(AppFormBindDO record);

    int insertSelective(AppFormBindDO record);

    AppFormBindDO selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(AppFormBindDO record);

    int updateByPrimaryKey(AppFormBindDO record);

    List<String> getFormModelIDs(String appID);


}
