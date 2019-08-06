package com.asset.service.impl;

import com.asset.bean.OrganScene;
import com.asset.bean.Resource;
import com.asset.bean.SceneRelation;
import com.asset.bean.SceneRole;
import com.asset.common.SystemConstant;
import com.asset.mapper.ResourceMapper;
import com.asset.service.IResourceService;
import com.asset.service.ISceneRelationService;
import com.asset.service.ISceneRoleService;
import com.asset.utils.ResourceNodeManager;
import com.asset.utils.ResourceNodeMerger;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    final static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private ISceneRelationService sceneRelationService;

    @Override
    public boolean appExists(String applicationName, String sceneId){
        return resourceMapper.findAppResourceByName(applicationName, sceneId).size() > 0;
    }

    /**
     * 将操作添加为菜单
     * @param formResource
     * @return int
     */
    public int addFuncResource(Resource formResource) throws CloneNotSupportedException {
        Resource [] array = SystemConstant.RESOURCES;
        List<Resource> menuList = new ArrayList<>();
        for (Resource value : array) {
            //操作型菜单深拷贝
            Resource var = (Resource) value.clone();
            var.setParentId(formResource.getId());
            var.setPath(formResource.getPath() + var.getPath());
            var.setIsDeleted(0);
            var.setAddTime(new Date());
            menuList.add(var);
        }
        resourceMapper.batchInsert(menuList);
        resourceMapper.batchInsertResourceRole(menuList);
        return 1;
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
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda().eq(SceneRole::getStatus, 1).eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda().in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
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

    public List<Resource> getResourcesByRole(Long roleId, String sceneId){
        return resourceMapper.getResourcesByRole(roleId, sceneId);
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
        List<SceneRole> sceneRoles = sceneRoleService.list(Wrappers.<SceneRole>query().lambda().eq(SceneRole::getStatus, 1).eq(SceneRole::getSceneCode, sceneId));
        List<Long> rids = new ArrayList<>();
        sceneRoles.forEach(map-> rids.add(map.getId()));
        List<SceneRelation> sceneRelations = sceneRelationService.list(Wrappers.<SceneRelation>query().lambda().in(SceneRelation::getRid, rids).eq(SceneRelation::getUid, userId));
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
    public void addResource4Admin(Resource resource){
        resourceMapper.addResourceRole(resource.getId(), SystemConstant.ADMIN_ROLE_ID);
    }

    /**
     * 为系统默认角色增加菜单
     * @param menu
     * @return
     */
    public int addResource4DefaultRole(Resource menu){
        return resourceMapper.addResourceRole(menu.getId(),SystemConstant.DEFAULT_ROLE_ID);
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
                .eq(Resource::getSceneId, sceneId)
                .eq(Resource::getIsDeleted, 0));
        return ResourceNodeMerger.merge(resources);
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
}