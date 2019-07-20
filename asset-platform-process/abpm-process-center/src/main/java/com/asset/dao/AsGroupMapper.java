package com.asset.dao;


import com.asset.entity.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    /**
     * 获取一个App下的所有分组
     * @param appID
     * @return
     */
    List<Group> selectAll(String appID);

    int deleteGroup(Group info);

    int updateGroup(Group info);
}
