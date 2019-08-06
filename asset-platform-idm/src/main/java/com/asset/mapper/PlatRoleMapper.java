package com.asset.mapper;

import com.asset.bean.PlatRole;
import com.asset.bean.UserScene;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PlatRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PlatRole record);

    int insertSelective(PlatRole record);

    PlatRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatRole record);

    int updateByPrimaryKey(PlatRole record);

    List<UserScene> getPlatRoles(@Param("userId")String userId, @Param("sceneId")String sceneId);
}
