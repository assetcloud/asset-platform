package com.asset.dao;


import com.asset.entity.AppFormBind;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OApp应用与表单模型绑定的DAO层
 */
@Repository
public interface AppFormBindMapper {
    int deleteByPrimaryKey(String oappId);

    int insert(AppFormBind record);

    int insertSelective(AppFormBind record);

    AppFormBind selectByPrimaryKey(String oappId);

    int updateByPrimaryKeySelective(AppFormBind record);

    int updateByPrimaryKey(AppFormBind record);

    List<String> getFormModelIDs(String oappID);

}
