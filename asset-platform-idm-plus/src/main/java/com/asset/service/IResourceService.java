package com.asset.service;

import com.asset.bean.Resource;
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

    int addFuncResource(Resource formResource) throws CloneNotSupportedException;

    int addResourceAll(Resource resource);

    List<Resource> getResourcesByCurrentUser(String userId, String sceneId);

    List<Resource> getResourcesByUserId(String userId, String sceneId);

    List<Resource> getResourcesByRole(Long roleId, String sceneId);

    List<Resource> getAppResourcesByUser(String userId, String sceneId);

    List<Resource> getFormResourcesByApp(String userId, Long appReourceId, String sceneId);

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
}
