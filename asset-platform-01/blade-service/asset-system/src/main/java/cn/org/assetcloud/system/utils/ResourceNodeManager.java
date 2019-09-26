package cn.org.assetcloud.system.utils;

import cn.org.assetcloud.system.entity.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树节点管理类
 *
 * @author hjhu
 *
 */
public class ResourceNodeManager {

    public List<Resource> list;// 树的所有节点


    public ResourceNodeManager(Resource [] items) {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(items));
    }

    public ResourceNodeManager(List<Resource> items) {
        list = new ArrayList<>();
        list.addAll(items);
    }

    public List<Resource> getList() {
        return list;
    }

    public void setList(List<Resource> list) {
        this.list = list;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id
     *            节点ID
     * @return 对应的节点对象
     */
    public Resource getTreeNodeAT(Long id) {
        for (Resource treeNode : list) {
            if (treeNode.getId().equals(id))
                return treeNode;
        }
        return null;
    }

    /**
     * 获取树的根节点
     *
     * @return 一棵树的根节点
     */
    public Resource getRoot() {
        for (Resource treeNode : list) {
            if (treeNode.getParentId() == 0)
                return treeNode;
        }
        return null;
    }
}
