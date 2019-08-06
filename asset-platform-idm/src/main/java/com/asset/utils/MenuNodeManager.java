package com.asset.utils;

import com.asset.bean.OrganTree;
import com.asset.bean.PlatMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树节点管理类
 *
 * @author hjhu
 *
 */
public class MenuNodeManager {

    public List<PlatMenu> list;// 树的所有节点


    public MenuNodeManager(PlatMenu [] items) {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(items));
    }

    public MenuNodeManager(List<PlatMenu> items) {
        list = new ArrayList<>();
        list.addAll(items);
    }

    public List<PlatMenu> getList() {
        return list;
    }

    public void setList(List<PlatMenu> list) {
        this.list = list;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id
     *            节点ID
     * @return 对应的节点对象
     */
    public PlatMenu getTreeNodeAT(Long id) {
        for (PlatMenu treeNode : list) {
            if (treeNode.getId() == id)
                return treeNode;
        }
        return null;
    }

    /**
     * 获取树的根节点
     *
     * @return 一棵树的根节点
     */
    public PlatMenu getRoot() {
        for (PlatMenu treeNode : list) {
            if (treeNode.getParentId() == 0)
                return treeNode;
        }
        return null;
    }
}
