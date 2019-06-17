package com.asset.dao;


import com.asset.entity.OAppFormBind;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OApp应用与表单模型绑定的DAO层
 */
@Repository
public interface OAppFormBindMapper {
    int deleteByPrimaryKey(String oappId);

    int insert(OAppFormBind record);

    int insertSelective(OAppFormBind record);

    OAppFormBind selectByPrimaryKey(String oappId);

    int updateByPrimaryKeySelective(OAppFormBind record);

    int updateByPrimaryKey(OAppFormBind record);

    List<String> getFormModelIDs(String oappID);

}
