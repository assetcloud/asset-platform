package com.asset.mapper;


import com.asset.entity.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
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
