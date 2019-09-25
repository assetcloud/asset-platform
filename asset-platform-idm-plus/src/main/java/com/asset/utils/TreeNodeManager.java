package com.asset.utils;

import com.asset.bean.OrganTree;
import com.asset.vo.OrganTreeVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树节点管理类
 *
 * @author hjhu
 *
 */
public class TreeNodeManager {

    public List<OrganTree> list;// 树的所有节点

    public TreeNodeManager(OrganTree[] items) {
        list = new ArrayList<>();
        list.addAll(Arrays.asList(items));
    }

    public TreeNodeManager(List<OrganTree> items) {
        this.list = items;
    }

//    public TreeNodeManager(List<OrganTreeVO> items) {
//        this.treeVOList = items;
//    }

    public List<OrganTree> getList() {
        return list;
    }

    public void setList(List<OrganTree> list) {
        this.list = list;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id
     *            节点ID
     * @return 对应的节点对象
     */
    public OrganTree getTreeNodeAT(String id) {
        for (OrganTree treeNode : list) {
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
    public OrganTree getRoot() {
        for (OrganTree treeNode : list) {
            if (treeNode.getId().equals("top"))
            return treeNode;
        }
        return null;
    }
}
