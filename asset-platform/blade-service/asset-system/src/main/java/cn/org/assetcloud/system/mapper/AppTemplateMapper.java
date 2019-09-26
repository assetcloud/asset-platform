package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.AppTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface AppTemplateMapper {

    int deleteByPrimaryKey(String id);

    int insert(AppTemplate record);

    int insertSelective(AppTemplate record);

    AppTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppTemplate record);

    int updateByPrimaryKey(AppTemplate record);
}
