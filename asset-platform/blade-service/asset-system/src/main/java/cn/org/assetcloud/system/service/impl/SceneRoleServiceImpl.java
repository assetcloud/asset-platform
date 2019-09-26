package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.common.SystemConstant;
import cn.org.assetcloud.system.entity.*;
import cn.org.assetcloud.system.mapper.SceneRoleMapper;
import cn.org.assetcloud.system.service.IResourceRoleService;
import cn.org.assetcloud.system.service.IRoleGroupService;
import cn.org.assetcloud.system.service.ISceneRelationService;
import cn.org.assetcloud.system.service.ISceneRoleService;
import cn.org.assetcloud.system.user.entity.User;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjhu on 2018/05/28.
 */
@Service
@Transactional
@AllArgsConstructor
public class SceneRoleServiceImpl extends ServiceImpl<SceneRoleMapper, SceneRole> implements ISceneRoleService {

    private SceneRoleMapper sceneRoleMapper;

    private IRoleGroupService roleGroupService;

    private ISceneRelationService sceneRelationService;

    private IResourceRoleService resourceRoleService;

    public List<RoleGroup> rolesWithGroup(String sceneId) {
        return sceneRoleMapper.groupRoles(sceneId);
    }

    public List<SceneRole> listAll(SceneRole sceneRole) {
        return sceneRoleMapper.listAll(sceneRole);
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
	@Transactional
    public boolean addRoles4Scene(String sceneId) {
        //新场景中新增2个默认角色
        RoleGroup roleGroup = new RoleGroup(SystemConstant.DEFAULT_GROUP_NAME, 0, new Date(), sceneId);
        roleGroupService.save(roleGroup);
        //在场景中新增两个默认角色
        SceneRole roleAdmin = new SceneRole(sceneId, SystemConstant.SCENE_ADMIN_CH, SystemConstant.SCENE_ADMIN);
        SceneRole roleDefault = new SceneRole(sceneId, SystemConstant.SCENE_DEFAULT_CH, SystemConstant.SCENE_DEFAULT);
        List<SceneRole> list = new ArrayList<>();
        roleAdmin.setGroupId(roleGroup.getId());
        roleAdmin.setStatus(true);
        roleAdmin.setCreatedTime(new Date());
        roleAdmin.setEnableTime(new Date());
        roleAdmin.setRoleType(1);
        roleDefault.setGroupId(roleGroup.getId());
        roleDefault.setRoleType(2);
        roleDefault.setStatus(true);
        roleDefault.setRoleDefault(1);
        roleDefault.setCreatedTime(new Date());
        roleDefault.setEnableTime(new Date());
        list.add(roleAdmin);
        list.add(roleDefault);
        for (SceneRole sceneRole : list) {
            sceneRole.setSceneCode(sceneId);
        }
        return this.saveBatch(list);
    }

    @Override
    public boolean addDefaultRole4Reg(String sceneId, List<SceneRole> list) {
        for (SceneRole sceneRole : list) {
            sceneRole.setSceneCode(sceneId);
        }
        return this.saveBatch(list);
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

    public int addRoleGroup(RoleGroup record){
        if(sceneRoleMapper.getGroupByName(record.getRoleGroupName()).size() > 0){
            return -2;
        }
        record.setAddTime(new Date());
        return sceneRoleMapper.addRoleGroup(record);
    }

    public int deleteGroup(Long id){
        return sceneRoleMapper.deleteGroup(id);
    }

    public int modifyGroup(RoleGroup newRecord){
        return sceneRoleMapper.modifyGroup(newRecord.getId(), newRecord.getRoleGroupName());
    }

    @Override
    public boolean updateGroupInfo(SceneRole record){
        return sceneRoleMapper.updateById(record) > 0;
    }

    @Override
    public boolean addUsers2Role(@NotEmpty Long roleId, @NotEmpty List<String> userIds) {
        return sceneRelationService.saveBatch(roleId, userIds);
    }

    /**
     * 角色模糊搜索
     * @param role
     * @return Role
     */
    public List<SceneRole> getRoleByName(SceneRole role){
        return sceneRoleMapper.selectList(Wrappers.<SceneRole>query().lambda()
                .like(SceneRole::getRoleNameZh, role.getRoleNameZh())
                .eq(SceneRole::getSceneCode, role.getSceneCode())
                .eq(SceneRole::getStatus, 1));
    }

    public List<User> getUsersByRole(Long roleId, String userName){
        return sceneRoleMapper.getUsersByRole(roleId, userName);
    }

    @Override
    public List<String> getUsersByRoles(List<Long> roleIds){
        List<SceneRelation> list = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda()
                .in(SceneRelation::getRid, roleIds));
        ArrayList<String> userIds = new ArrayList<>();
        list.forEach(relation -> userIds.add(relation.getUid()));
        return userIds;
    }

    @Override
    @Transactional
    public boolean grant(@NotEmpty Long roleId, @NotEmpty List<Long> resourceIds) {
        resourceRoleService.remove(Wrappers.<ResourceRole>update().lambda()
                .eq(ResourceRole::getRoleId, roleId));
        List<ResourceRole> resourceRoles = new ArrayList<>();
        resourceIds.forEach(resourceId -> {
            ResourceRole resourceRole = new ResourceRole();
            resourceRole.setMenuId(resourceId);
            resourceRole.setRoleId(roleId);
            resourceRoles.add(resourceRole);
        });
        return resourceRoleService.saveBatch(resourceRoles);
    }

    @Override
    @Transactional
    public boolean setAuthority(@NotEmpty String sceneId, @NotEmpty String userId, @NotEmpty List<Long> roleIds) {

        List<SceneRole> sceneRoles = sceneRoleMapper.selectList(Wrappers.<SceneRole>lambdaQuery().eq(SceneRole::getSceneCode, sceneId));
        List<Long> ids = sceneRoles.stream().map(SceneRole::getId).collect(Collectors.toList());
        sceneRelationService.remove(Wrappers.<SceneRelation>update().lambda()
                .in(SceneRelation::getRid, ids)
                .eq(SceneRelation::getUid, userId));
        List<SceneRelation> relations = new ArrayList<>();
        roleIds.forEach(roleId -> {
            SceneRelation relation = new SceneRelation();
            relation.setRid(roleId);
            relation.setUid(userId);
            relations.add(relation);
        });
        return sceneRelationService.saveBatch(relations);
    }

    @Override
    public List<SceneRole> getRolesByScene(String sceneId) {
        return baseMapper.selectList(Wrappers.<SceneRole>query().lambda()
                .eq(SceneRole::getSceneCode, sceneId)
                .eq(SceneRole::getStatus, 1));
    }

    @Override
    public List<SceneRole> getRolesOwned(String userId, String sceneId) {
        return sceneRoleMapper.getRolesOwned(userId, sceneId);
    }
}
