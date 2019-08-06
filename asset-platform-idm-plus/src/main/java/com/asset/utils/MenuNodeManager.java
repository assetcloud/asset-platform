package com.asset.utils;

import com.asset.bean.OrganTree;
import com.asset.bean.Menu;

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

    public List<Menu> list;// 树的所有节点


    public MenuNodeManager(Menu [] items) {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(items));
    }

    public MenuNodeManager(List<Menu> items) {
        list = new ArrayList<>();
        list.addAll(items);
    }

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id
     *            节点ID
     * @return 对应的节点对象
     */
    public Menu getTreeNodeAT(Integer id) {
        for (Menu treeNode : list) {
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
    public Menu getRoot() {
        for (Menu treeNode : list) {
            if (treeNode.getParentId() == 0)
                return treeNode;
        }
        return null;
    }
}
