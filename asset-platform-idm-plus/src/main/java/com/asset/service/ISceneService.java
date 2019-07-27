package com.asset.service;

import com.asset.bean.OrganScene;
import com.asset.bean.Scene;
import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISceneService extends IService<Scene> {

    List<Scene> getAllScene();

    int addSceneNormal(Scene record);
    /**
     * 用户注册时新增场景
     * @param record
     * @return
     */
    int addScene4Reg(Scene record, List<String> nodeList);

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
     * 用户注册时，设置该用户在该场景中的角色和所属部门（默认为根部门）
     * @param sceneId
     * @param userId
     * @param roleId
     * @return boolean
     */
    boolean userSceneBind(String sceneId, String userId, Long roleId);
    /**
     * 向场景中批量增加成员
     * @param userIds
     * @param sceneId
     * @return boolean
     */
    boolean addSceneMembers(List<String> userIds, String sceneId, Long roleId);
    /**
     * 判断场景是否有效
     * @param sceneId
     * @return
     */
    boolean sceneAvailable(String sceneId);

    /**
     * 用户审核，更新user_scene表
     * @param sceneId
     * @param userId
     * @return
     */
    boolean enableScene(String userId, String sceneId);

    List<OrganScene> getNodesByNameAlike(String keyword, String sceneId);
}
