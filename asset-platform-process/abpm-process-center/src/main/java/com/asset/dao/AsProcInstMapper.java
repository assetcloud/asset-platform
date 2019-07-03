package com.asset.dao;


import com.asset.entity.AsProcInst;
import org.springframework.stereotype.Repository;

@Repository
public interface AsProcInstMapper {
    int deleteByPrimaryKey(String procInstId);

    int insert(AsProcInst record);

    int insertSelective(AsProcInst record);

    AsProcInst selectByPrimaryKey(String procInstId);

    int updateByPrimaryKeySelective(AsProcInst record);

    int updateByPrimaryKey(AsProcInst record);

}
