package com.asset.dao;


import com.asset.entity.GroupDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupDO record);

    int insertSelective(GroupDO record);

    GroupDO selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(GroupDO record);

    int updateByPrimaryKey(GroupDO record);

    /**
     * 获取一个App下的所有分组
     * @param appID
     * @return
     */
    List<GroupDO> selectAll(String appID);

    int deleteGroup(GroupDO info);

    int updateGroup(GroupDO info);
}
