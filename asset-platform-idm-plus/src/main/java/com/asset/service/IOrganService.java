package com.asset.service;

import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;
import com.asset.bean.User;
import com.asset.vo.OrganTreeVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IOrganService extends IService<OrganTree> {

    /**
     * 添加部门或单位节点
     * @param record
     * @return
     */
    boolean addNode(OrganTree record);

    /**
     * 删除部门或单位节点
     * @param record
     * @return int
     */
    int deleteNode(OrganTree record);

    /**
     * 批量删除部门或单位节点
     * @param organTrees
     * @return int
     */
    int batchDeleteNode(List<OrganTree> organTrees);

    /**
     * 编辑部门信息
     * @param record
     * @return int
     */
    int updateNode(OrganTree record);

    /**
     * 获取组织树全部信息
     * @return
     */
    OrganTree getMainTree();

    /**
     * 通过场景获取组织结构树（不包含顶级节点）
     * @param sceneId
     * @return
     */
    OrganTree getTreeByScene2(String sceneId);

    /**
     * 通过场景获取组织结构树（不包含顶级节点）
     * @param sceneId
     * @return
     */
    List<OrganTreeVO> getTreeByScene(String sceneId);


    /**
     * 通过场景获取组织结构树中的特定节点（不包含顶级节点）
     * @param sceneId
     * @return
     */
    List<OrganScene> getNodeByScene(String sceneId, String nodeId);
    /**
     * 节点模糊检索
     * @param unitName
     * @return List<OrganTree>
     */
    List<OrganTree> searchNode(String unitName);

    /**
     * 判断组织节点是否已存在
     * @param unitName
     * @return
     */
    boolean nodeExists(String unitName);

    /**
     * 判断父节点是否存在
     * @param parentId
     * @return boolean
     */
    boolean hasParent(String parentId);

    /**
     * 批量新增组织部门
     * @param nodes
     * @return
     */
    boolean batchAddNodes(List<OrganTree> nodes);

    /**
     * 获取单个场景下的所有用户
     * @param sceneId
     * @return
     */
    List<User> getUsersByScene(String sceneId);
    /**
     * 获取主组织树（散点式）
     * @return OrganTree
     */
    OrganTree selectAllWithoutMerge(String sceneId);
}
