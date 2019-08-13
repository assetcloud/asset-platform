package com.asset.service;

import com.asset.bean.Resource;
import com.asset.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
public interface IResourceService extends IService<Resource> {

    boolean addFuncResource(Resource formResource) throws CloneNotSupportedException;

    int addResourceAll(Resource resource);

    /**
     * 通过用户获取资源
     * @param userId
     * @param sceneId
     * @return
     */
    List<Resource> getResourcesByCurrentUser(String userId, String sceneId);

    List<Resource> getResourcesByUserId(String userId, String sceneId);

    /**
     * 通过角色获取所有资源
     * @param roleId
     * @return
     */
    List<String> getResourcesByRole(Long roleId);

    /**
     * 通过用户获取应用资源
     * @param userId
     * @param sceneId
     * @return
     */
    List<Resource> getAppResourcesByUser(String userId, String sceneId);

    /**
     * 获取表单资源
     * @param userId
     * @param appResourceId
     * @param sceneId
     * @return
     */
    List<Resource> getFormResourcesByApp(String userId, Long appResourceId, String sceneId);

    /**
     * 获取操作型资源
     * @param userId
     * @param formResourceId
     * @param sceneId
     * @return
     */
    List<Resource> getFuncResourcesByForm(String userId, Long formResourceId, String sceneId);

    void addResource4Admin(Resource resource);

    int addResource4DefaultRole(Resource resource);

    Resource getResourceByPath(String applicationId);

    /**
     * 更新表单资源，表单与场景绑定
     * @param formModelId
     * @param sceneId
     * @return
     */
    int updateFormInfo(String formModelId, String sceneId);

    /**
     * 更新操作型资源，操作与场景绑定
     * @param formModelId
     * @param sceneId
     * @return int
     */
//    int updateFuncInfo(String formModelId, String sceneId);

    /**
     * 判断应用是否已存在
     * @param applicationName
     * @param sceneId
     * @return
     */
    boolean appExists(String applicationName, String sceneId);

    /**
     * 验证表单名称是否已被占用
     * @param formName
     * @param sceneId
     * @param parentId
     * @return
     */
    boolean formExists(String formName, String sceneId, Long parentId);

    /**
     * 验证表单是否在资源表中
     * @param formModelId
     * @return
     */
    boolean formExists(String formModelId);

    /**
     * 更新操作型资源，与场景相绑定
     * @param formModelId
     * @param sceneId
     * @return
     */
    int updateFuncInfo(String formModelId, String sceneId);

    /**
     * 获取场景下的所有资源
     * @param sceneId
     * @return
     */
    Resource getResourceList(String sceneId);

    /**
     * 资源树形结构
     *
     * @return
     */
    List<ResourceVO> tree(String sceneId);

    /**
     * 获取表单资源list
     * @param appId
     * @param roleIds
     * @return
     */
    List<Resource> getFormList(Long appId, List<Long> roleIds);
}
