package com.asset.dao;


import com.asset.entity.OAppGroupBind;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OAppGroupBindMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(OAppGroupBind record);

    int insertSelective(OAppGroupBind record);

    OAppGroupBind selectByPrimaryKey(Integer groupId);

    /**
     * 获取一个OApp下的所有分组
     * @return
     */
    List<OAppGroupBind> selectAll(String oAppID);

    int updateByPrimaryKeySelective(OAppGroupBind record);

    int updateByPrimaryKey(OAppGroupBind record);
}
