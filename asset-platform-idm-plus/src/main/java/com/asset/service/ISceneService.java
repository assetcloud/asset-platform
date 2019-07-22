package com.asset.service;

import com.asset.bean.Scene;
import com.asset.bean.User;
import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ISceneService extends IService<Scene> {

    List<Scene> getAllScene();

    int addSceneNormal(Scene record);

    int deleteScene(String sceneId);

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
    /**
     * 判定用户是否属于所属场景
     * @param userId
     * @param sceneId
     * @return
     */
    boolean hasScene(String userId, String sceneId);
    /**
     * 用户注册时，设置该用户在该场景中的角色
     * @param sceneId
     * @param userId
     * @param roleId
     * @return boolean
     */
    boolean userSceneBind(String sceneId, String userId, Long roleId);
    /**
     * 判断场景是否有效
     * @param sceneId
     * @return
     */
    boolean sceneAvailable(String sceneId);
}
