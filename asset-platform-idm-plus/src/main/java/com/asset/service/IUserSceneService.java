package com.asset.service;

import com.asset.bean.SceneRole;
import com.asset.bean.User;
import com.asset.bean.UserScene;
import com.asset.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserSceneService extends IService<UserScene> {
    /**
     * 更新用户主部门
     * @param userId
     * @param sceneId
     * @param nodeId
     * @return
     */
    boolean updateNodeIdByUserId(String userId, String sceneId, String nodeId);
    /**
     * 设置部门负责人
     * @param userId
     * @param sceneId
     * @param nodeId
     * @return boolean
     */
    boolean updatePrincipalByUserId(String userId, String sceneId, String nodeId);

    /**
     * （场景中）获取部门中的用户
     * @param sceneId
     * @param nodeId
     * @return
     */
    List<UserVO> getNodeUsers(String sceneId, String nodeId, String memberName);

    /**
     * 获取用户在场景中的已有角色
     * @param userId
     * @param sceneId
     * @return
     */
    List<SceneRole> rolesOwned(String userId, String sceneId);

    /**
     * 获取用户在场景中未拥有的角色
     * @param userId
     * @param sceneId
     * @return
     */
    List<SceneRole> rolesChecked(String userId, String sceneId);


    /**
     * （场景中）获取多个部门中的用户id
     * @param sceneId
     * @param nodeId
     * @return
     */
    List<String> getNodeUsers(String sceneId, List<String> nodeId);
}
