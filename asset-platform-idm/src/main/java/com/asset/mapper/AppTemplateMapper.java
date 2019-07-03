package com.asset.mapper;

import com.asset.bean.AppTemplate;
import org.springframework.stereotype.Component;

@Component
public interface AppTemplateMapper {

    int deleteByPrimaryKey(String id);

    int insert(AppTemplate record);

    int insertSelective(AppTemplate record);

    AppTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppTemplate record);

    int updateByPrimaryKey(AppTemplate record);
}
