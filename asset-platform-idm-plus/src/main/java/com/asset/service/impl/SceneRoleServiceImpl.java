package com.asset.service.impl;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.mapper.SceneRoleMapper;
import com.asset.service.IRoleGroupService;
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

    @Autowired
    private IRoleGroupService roleGroupService;

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

    @Override
    public SceneRole getDefaultRole(String sceneId) {
        return sceneRoleMapper.getDefaultRole(sceneId);
    }

    @Override
    public boolean addRoles4Scene(String sceneId) {
        //新场景中新增2个默认角色
        RoleGroup roleGroup = new RoleGroup(SystemConstant.DEFAULT_GROUP_NAME, 0, new Date(), sceneId);
        roleGroupService.insert(roleGroup);
        //在场景中新增两个默认角色
        SceneRole roleAdmin = new SceneRole(sceneId, SystemConstant.SCENE_ADMIN_CH, SystemConstant.SCENE_ADMIN);
        SceneRole roleDefault = new SceneRole(sceneId, SystemConstant.SCENE_DEFAULT_CH, SystemConstant.SCENE_DEFAULT);
        List<SceneRole> list = new ArrayList<>();
        roleAdmin.setGroupId(roleGroup.getId());
        roleAdmin.setStatus(true);
        roleAdmin.setCreatedTime(new Date());
        roleAdmin.setEnableTime(new Date());
        roleDefault.setGroupId(roleGroup.getId());
        roleDefault.setStatus(true);
        roleDefault.setRoleDefault(1);
        roleDefault.setCreatedTime(new Date());
        roleDefault.setEnableTime(new Date());
        list.add(roleAdmin);
        list.add(roleDefault);
        for (SceneRole sceneRole : list) {
            sceneRole.setSceneCode(sceneId);
        }
        return this.insertBatch(list);
    }

    @Override
    public boolean addDefaultRole4Reg(String sceneId, List<SceneRole> list) {
        for (SceneRole sceneRole : list) {
            sceneRole.setSceneCode(sceneId);
        }
        return this.insertBatch(list);
    }

    @Override
    public boolean roleAvailable(String sceneId) {
        List<SceneRole> rolesByScene = sceneRoleMapper.getRolesByScene(sceneId);
        for (SceneRole sceneRole : rolesByScene) {
            sceneRole.setStatus(true);
            sceneRoleMapper.updateById(sceneRole);
        }
        return true;
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
