package com.asset.service.impl;

import com.asset.bean.SceneRole;
import com.asset.bean.User;
import com.asset.bean.UserScene;
import com.asset.mapper.SceneRoleMapper;
import com.asset.mapper.UserSceneMapper;
import com.asset.service.IUserSceneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSceneServiceImpl extends ServiceImpl<UserSceneMapper, UserScene> implements IUserSceneService {

    @Autowired
    UserSceneMapper userSceneMapper;

    @Autowired
    SceneRoleMapper sceneRoleMapper;

    @Override
    public boolean updateNodeIdByUserId(String userId, String sceneId, String nodeId) {
        return userSceneMapper.updateNodeIdByUserId(userId, sceneId, nodeId) > 0;
    }

    @Override
    public boolean updatePrincipalByUserId(String userId, String sceneId, String nodeId) {
        userSceneMapper.cancelPrincipal(sceneId, nodeId);
        UserScene targetUser = userSceneMapper.getUser4Principal(userId, sceneId, nodeId);
        if (null == targetUser){
            return false;
        }
        targetUser.setNodePrincipal(1);
        return userSceneMapper.updateById(targetUser) > 0;
    }

    @Override
    public List<User> getNodeUsers(String sceneId, String nodeId, String memberName) {
        return userSceneMapper.getUsers(sceneId, nodeId, memberName);
    }

    @Override
    public List<SceneRole> rolesOwned(String userId, String sceneId) {
        return userSceneMapper.getRoles(userId, sceneId);
    }

    @Override
    public List<SceneRole> rolesChecked(String userId, String sceneId) {
        List<SceneRole> roleList = sceneRoleMapper.getAllByScene(sceneId);
        List<SceneRole> rolesOwned = userSceneMapper.getRoles(userId, sceneId);
        roleList.stream()
                .filter(map -> rolesOwned.stream().anyMatch(map1 -> map.getId().equals(map1.getId())))
                .forEach(map -> map.setChecked(1));
        return roleList;
    }
}
