package com.asset.dao;


import com.asset.entity.AsGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(AsGroup record);

    int insertSelective(AsGroup record);

    AsGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(AsGroup record);

    int updateByPrimaryKey(AsGroup record);

    /**
     * 获取一个App下的所有分组
     * @param appID
     * @return
     */
    List<AsGroup> selectAll(String appID);

    int deleteGroup(AsGroup info);

    int updateGroup(AsGroup info);
}
