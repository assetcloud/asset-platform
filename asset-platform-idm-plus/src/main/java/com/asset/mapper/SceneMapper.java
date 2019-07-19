package com.asset.mapper;

import com.asset.bean.OrganScene;
import com.asset.bean.Scene;
import org.apache.ibatis.annotations.Param;

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

    List<Scene> getScenesByUser(@Param("userId")String userId);

    int userSceneBind(@Param("sceneId")String sceneId, @Param("userId")String userId, @Param("roleId")long roleId);

    int getSceneNodes(@Param("sceneId") String sceneId);

    int addNode(OrganScene record);
}
