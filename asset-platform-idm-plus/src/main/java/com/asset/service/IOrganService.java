package com.asset.service;

import com.asset.bean.OrganTree;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface IOrganService extends IService<OrganTree> {

    /**
     * 添加部门或单位节点
     * @param record
     * @return
     */
    int addNode(OrganTree record);

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

    List<OrganTree> getTreeByScene(String sceneId);

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
}
