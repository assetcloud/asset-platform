package com.asset.dao;


import com.asset.entity.AsGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface AsGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(AsGroup record);

    int insertSelective(AsGroup record);

    AsGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(AsGroup record);

    int updateByPrimaryKey(AsGroup record);
}
