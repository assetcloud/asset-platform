package com.asset.service;

import com.asset.bean.Scene;
import com.asset.bean.User;
import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ISceneService extends IService<Scene> {

    List<Scene> getAllScene();

    int addScene4User(Scene scene, User user) throws Exception;

    int addSceneNormal(Scene record);

    int deleteScene(String sceneId);

    int updateSceneInfo(Scene scene);

    int updateSceneSelective(Scene scene);

    List<Scene> getSceneByName(String sceneName);

    /**
     * 场景信息-模糊搜索兼全部展现
     * @param sceneName
     * @return
     */
    List<Scene> findSceneByNameAlike(String sceneName);

    List<Scene> getScenesByUser(String userId);

    boolean isSceneEmpty(String sceneId);

    boolean addUserScene(List<UserScene> userScenes);
}
