package com.asset.mapper;

import com.asset.bean.SceneRole;
import com.asset.bean.User;
import com.asset.bean.UserScene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserSceneMapper extends BaseMapper<UserScene> {

    /**
     * 判断是否用户-场景关联是否存在
     * @param userId
     * @param sceneId
     * @return
     */
    UserScene hasScene(String userId, String sceneId);
    /**
     * 根据场景和用户id更新 用户-场景关联信息
     * @param userId
     * @param sceneId
     * @return
     */
    int updateByUserAndScene(String userId, String sceneId);
    /**
     * 更新主部门
     * @param userId
     * @param sceneId
     * @param nodeId
     * @return int
     */
    int updateNodeIdByUserId(String userId, String sceneId, String nodeId);
    /**
     * 设置部门负责人
     * @param userId
     * @param sceneId
     * @param nodeId
     * @return int
     */
    UserScene getUser4Principal(String userId, String sceneId, String nodeId);
    /**
     * 取消部门负责人
     * @param sceneId
     * @param nodeId
     * @return
     */
    Integer cancelPrincipal(String sceneId, String nodeId);
    /**
     * 条件构造器获取用户id
     * @param sceneId
     * @param nodeId
     * @return
     */
    List<User> getUsers(String sceneId, String nodeId);

    /**
     * 获取场景中用户的角色列表
     * @param userId
     * @param sceneId
     * @return List<SceneRole>
     */
    List<SceneRole> getRoles(String userId, String sceneId);
}
