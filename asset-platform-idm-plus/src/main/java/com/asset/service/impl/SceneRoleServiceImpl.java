package com.asset.service.impl;

import com.asset.bean.*;
import com.asset.mapper.SceneRoleMapper;
import com.asset.service.ISceneRoleService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2018/05/28.
 */
@Service
@Transactional
public class SceneRoleServiceImpl extends ServiceImpl<SceneRoleMapper, SceneRole> implements ISceneRoleService {

    @Autowired
    private SceneRoleMapper sceneRoleMapper;

//    @PreAuthorize(value = "administrator")
    public List<SceneRole> getAllRoles() {
        return sceneRoleMapper.roles();
    }

    public List<RoleGroup> rolesWithGroup() {
        return sceneRoleMapper.groupRoles();
    }

    public boolean roleExist(SceneRole record){
        return null != sceneRoleMapper.roleExist(record.getRoleNameZh(), record.getSceneCode());
    }

    public boolean roleGroupExist(RoleGroup record){
        return null != sceneRoleMapper.roleGroupExist(record.getRoleGroupName(), record.getSceneCode());
    }

    public boolean rolesInGroup(Long groupId){
        List<SceneRole> sceneRoleList = sceneRoleMapper.rolesInGroup(groupId);
        return sceneRoleList.size() != 0;
    }
//

//
//    public int addRoleGroup(RoleGroup record){
//        if(roleMapper.getGroupByName(record.getRoleGroupName()).size() > 0){
//            return -2;
//        }
//        record.setAddTime(new Date());
//        return roleMapper.addRoleGroup(record);
//    }
//
//    public int deleteGroup(Long id){
//        return roleMapper.deleteGroup(id);
//    }
//
//    public int modifyGroup(RoleGroup newRecord){
//        return roleMapper.modifyGroup(newRecord.getId(), newRecord.getRoleGroupName());
//    }
//
//    public int addRole2Group(Long rid, Long groupId){
//        return roleMapper.addRoleToGroup(rid, groupId);
//    }
//
//    public int addUsers2Role(Long rid, String[] users){
//        List<UserRole> userList = new ArrayList<>();
//        UserRole userRole = new UserRole();
//        for (String user : users) {
//            userRole.setUid(user);
//            userRole.setCreatedTime(new Date());
//            userRole.setRoleId(rid);
//            userRole.setStatus(1);
//            userList.add(userRole);
//        }
//        return roleMapper.addUsersToRole(userList);
//    }
//
//    /**
//     * 添加角色成员
//     * @param userList
//     * @return int
//     */
//    public int addUsers2Role(List<UserRole> userList){
//        //TODO:改成批量增（若存在则不插入）
//        for (UserRole userRole : userList) {
//            userRole.setCreatedTime(new Date());
//        }
//        return roleMapper.addUsersToRole(userList);
//    }
//
//    /**
//     * 角色模糊搜索
//     * @param roleNameZh
//     * @return Role
//     */
//    public Role getRoleByName(String roleNameZh){
//        return roleMapper.roleSearchByName(roleNameZh);
//    }
//
//    /**
//     * 角色批量删除
//     * @param userList
//     * @return int
//     */
//    public int batchDelete(List<UserRole> userList){
//        return roleMapper.batchDeleteRoleMember(userList);
//    }
//
//    public List<User> getUsersByRole(Long roleId){
//        return roleMapper.getUsersByRole(roleId);
//    }
}
