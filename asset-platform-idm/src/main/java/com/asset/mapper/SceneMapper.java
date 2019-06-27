package com.asset.mapper;

import com.asset.bean.Scene;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SceneMapper {

    int deleteByPrimaryKey(String id);

    int insert(Scene record);

    int insertSelective(Scene record);

    Scene selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);

    List<Scene> selectAll();

    List<Scene> selectByName(@Param("sceneName") String sceneName);

    List<Scene> selectByNameAlike(@Param("sceneName") String sceneName);

    int deleteScene(@Param("id") String id);

    int updateSceneName(@Param("id") String id, @Param("sceneName") String sceneName);
}
