package com.asset.dao;


import com.asset.entity.AsFormProcInstBind;
import org.springframework.stereotype.Repository;

@Repository
public interface AsFormProcInstBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AsFormProcInstBind record);

    int insertSelective(AsFormProcInstBind record);

    AsFormProcInstBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AsFormProcInstBind record);

    int updateByPrimaryKey(AsFormProcInstBind record);
}
