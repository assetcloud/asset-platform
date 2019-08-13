package com.asset.service.impl;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.mapper.ResourceMapper;
import com.asset.service.IResourceRoleService;
import com.asset.service.IResourceService;
import com.asset.service.ISceneRelationService;
import com.asset.service.ISceneRoleService;
import com.asset.utils.ResourceNodeMerger;
import com.asset.utils.ResourceVONodeMerger;
import com.asset.vo.ResourceVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
@Service
@AllArgsConstructor
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    private ResourceMapper resourceMapper;

    private ISceneRoleService sceneRoleService;

    private ISceneRelationService sceneRelationService;

    IResourceRoleService resourceRoleService;

    @Override
    public boolean appExists(String applicationName, String sceneId){
        return resourceMapper.findAppResourceByName(applicationName, sceneId).size() > 0;
    }

    /**
     * 将操作添加为菜单
     * @param formResource
     * @return int
     */
    @Transactional
    public boolean addFuncResource(Resource formResource) throws CloneNotSupportedException {
        Resource [] array = SystemConstant.RESOURCES;
        List<Resource> menuList = new ArrayList<>();
        ArrayList<ResourceRole> roles = new ArrayList<>();
        for (Resource value : array) {
            //操作型菜单深拷贝
            Resource var = (Resource) value.clone();
            var.setParentId(formResource.getId());
            var.setSceneId(formResource.getSceneId());
            var.setPath(formResource.getPath() + var.getPath());
            var.setIsDeleted(0);
            var.setAddTime(new Date());
            menuList.add(var);
        }
        SceneRole one = sceneRoleService.getOne(Wrappers.<SceneRole>lambdaQuery()
                .eq(SceneRole::getSceneCode, formResource.getSceneId())
                .eq(SceneRole::getRoleType, SystemConstant.ROLE_TYPE_ADMIN));
        this.saveBatch(menuList);
        ArrayList<Long> menuIds = new ArrayList<>();
        menuList.forEach(menu -> {
            ResourceRole resourceRole = new ResourceRole();
            resourceRole.setMenuId(menu.getId());
            resourceRole.setRoleId(one.getId());
            roles.add(resourceRole);
            menuIds.add(menu.getId());
        });
        resourceRoleService.remove(Wrappers.<ResourceRole>update().lambda().in(ResourceRole::getMenuId, menuIds)
                .eq(ResourceRole::getRoleId, one.getId()));
        return resourceRoleService.saveBatch(roles);
    }

    /**
     * 复制应用时，将应用，对应的表单及操作权限都赋予给系统管理员
     * @param menu
     * @return int
     */
    public int addResourceAll(Resource menu){
        //TODO:复制应用时，将应用，对应的表单及操作权限都赋予给系统管理员
        return 1;
    }

    /**
     * 根据用户加载资源
     * @return
     */
    public List<Resource> getResourcesByCurrentUser(String userId, String sceneId){
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda()
                .eq(SceneRole::getStatus, 1)
                .eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda()
                .in(SceneRelation::getRid, rids)
                .eq(SceneRelation::getUid, userId));
        return resourceMapper.getResourcesByUser(sceneRelations);
    }

    /**
     * 为当前用户获取菜单
     * @return
     */
    public List<Resource> getResourcesByUserId(String userId, String sceneId){
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda().eq(SceneRole::getStatus, 1).eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda().in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
        return resourceMapper.getResourcesByUser(sceneRelations);
    }

    @Override
    public List<String> getResourcesByRole(Long roleId){
        List<ResourceRole> resourceRoles = resourceRoleService.list(Wrappers.<ResourceRole>query().lambda()
                .in(ResourceRole::getRoleId, roleId));
        return resourceRoles.stream().map(resourceRole -> Func.toStr(resourceRole.getMenuId())).collect(Collectors.toList());
    }

    public List<Resource> getAppResourcesByUser(String userId, String sceneId){
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda().eq(SceneRole::getStatus, 1).eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda().in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
        return resourceMapper.getAppResourceByUser(sceneRelations);
    }

    @Override
    public List<Resource> getFormResourcesByApp(String userId, Long appResourceId, String sceneId){
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>lambdaQuery().eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda()
                .in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
        return resourceMapper.getFormResourcesByApp(sceneRelations, appResourceId);
    }

    @Override
    public List<Resource> getFuncResourcesByForm(String userId, Long formResourceId, String sceneId) {
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda().eq(SceneRole::getStatus, 1).eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda().in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
        return resourceMapper.getFuncResourceByForm(sceneRelations, formResourceId);
    }

    /**
     * 为系统管理员增加菜单
     * @param resource
     */
    @Transactional
    public void addResource4Admin(Resource resource){
        SceneRole one = sceneRoleService.getOne(Wrappers.<SceneRole>lambdaQuery()
                .eq(SceneRole::getSceneCode, resource.getSceneId())
                .eq(SceneRole::getRoleType, SystemConstant.ROLE_TYPE_ADMIN));
        resourceRoleService.remove(Wrappers.<ResourceRole>update().lambda().eq(ResourceRole::getRoleId, one.getId())
                .eq(ResourceRole::getMenuId, resource.getId()));
        resourceMapper.addResourceRole(resource.getId(), one.getId());
    }

    /**
     * 为系统默认角色增加菜单
     * @param menu
     * @return
     */
    public int addResource4DefaultRole(Resource menu){
        return resourceMapper.addResourceRole(menu.getId(),SystemConstant.SCENE_DEFAULT_ROLE);
    }

    /**
     * 按照应用的id，查询该记录是否已经存在
     * @param applicationId
     * @return
     */
    public Resource getResourceByPath(String applicationId){
        return resourceMapper.getByPath(applicationId);
    }

    public int updateFormInfo(String formModelId, String sceneId){
        return resourceMapper.updateFormInfo(formModelId, sceneId);
    }

    @Override
    public int updateFuncInfo(String formModelId, String sceneId) {
        return resourceMapper.updateFuncInfo(formModelId, sceneId);
    }

    @Override
    public Resource getResourceList(String sceneId) {
        List<Resource> resources = resourceMapper.selectList(Wrappers.<Resource>query().lambda()
                .eq(Resource::getSceneId, sceneId));
        return ResourceNodeMerger.merge(resources);
    }

    @Override
    public List<ResourceVO> tree(String sceneId) {
        return ResourceVONodeMerger.merge(baseMapper.tree(sceneId));
    }

    public boolean formExists(String formName, String sceneId, Long parentId){
        List<Resource> resources = resourceMapper.getFormByName(formName, sceneId, parentId);
        return resources.size() > 0;
    }

    @Override
    public boolean formExists(String formModelId) {
        List<Resource> resources = resourceMapper.getFormByPath(formModelId);
        return resources.size() > 0;
    }

    @Override
    public List<Resource> getFormList(Long appId, List<Long> roleIds) {
        return baseMapper.formList(appId, roleIds);
    }
}
