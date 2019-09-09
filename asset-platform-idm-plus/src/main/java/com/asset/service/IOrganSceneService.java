package com.asset.service;

import com.asset.bean.OrganScene;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-23
 */
public interface IOrganSceneService extends IService<OrganScene> {

    /**
     * （场景中）批量删除部门节点
     * @param nodeIds
     * @param sceneId
     * @return
     */
    boolean batchRemove(List<String> nodeIds, String sceneId);

    /**
     * （场景中）批量删除成员
     * @param userIds
     * @param sceneId
     * @return boolean
     */
    boolean batchRemoveMembers(List<String> userIds, String sceneId);

    /**
     * 新增组织部门
     * @param treeIds
     * @param sceneId
     * @return
     */
    boolean addNodes(String treeIds, String sceneId);
}
