package com.asset.service.impl;

import com.asset.bean.Resource;
import com.asset.common.SystemConstant;
import com.asset.mapper.ResourceMapper;
import com.asset.service.IResourceService;
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
     * 为当前角色查询菜单
     * @return
     */
    public List<Resource> getResourcesByCurrentUser(String userId, String sceneId){
        return resourceMapper.getResourcesByUser(userId, sceneId);
    }

    /**
     * 为当前用户获取菜单
     * @return
     */
    public List<Resource> getResourcesByUserId(String userId, String sceneId){
        return resourceMapper.getResourcesByUser(userId, sceneId);
    }

    public List<Resource> getResourcesByRole(Long roleId, String sceneId){
        return resourceMapper.getResourcesByRole(roleId, sceneId);
    }

    /**
     * 通过用户id获取应用资源
     * @return List<Resource>
     */
    public List<Resource> getAppResourcesByUser(String userId, String sceneId){
        return resourceMapper.getResourcesByUserAndScene(userId, sceneId);
    }

    /**
     * 通过用户id和应用id获取表单资源
     * @return List<Resource>
     */
    @Override
    public List<Resource> getFormResourcesByApp(String userId, Long appResourceId, String sceneId){
        return resourceMapper.getFormResourcesByApp(userId, appResourceId, sceneId);
    }

    @Override
    public List<Resource> getFuncResourcesByForm(String userId, Long formResourceId, String sceneId) {
        return resourceMapper.getFuncResourceByForm(userId, formResourceId, sceneId);
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
