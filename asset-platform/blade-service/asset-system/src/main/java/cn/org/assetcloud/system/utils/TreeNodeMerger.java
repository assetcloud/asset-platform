package cn.org.assetcloud.system.utils;

import cn.org.assetcloud.system.entity.OrganTree;

import java.util.List;

/**
 * 节点归并类
 * @author hjhu
 *
 */
public class TreeNodeMerger {

    /**
     * 将节点数组归并为一棵树（填充节点的children域）
     * 时间复杂度为O(n^2)
     * @param items 节点域
     * @return
     */
    public static OrganTree merge(OrganTree[] items){
        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
        for (OrganTree treeNode : items) {
            if(!treeNode.getParentId().equals("")){
                OrganTree t = treeNodeManager.getTreeNodeAT(treeNode.getParentId());
                t.getChildren().add(treeNode);
            }
        }
        return treeNodeManager.getRoot();
    }

    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     *//*
    public static OrganTree merge(List<OrganTree> items){
        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
        for (OrganTree treeNode : items) {
            if(!treeNode.getParentId().equals("")){
                OrganTree t = treeNodeManager.getTreeNodeAT(treeNode.getParentId());
                t.getChildren().add(treeNode);
            }
        }
        return treeNodeManager.getRoot();
    }*/

    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     */
    public static OrganTree merge(List<OrganTree> items){
        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
        for (OrganTree treeNode : items) {
            if(!treeNode.getParentId().equals("")){
                OrganTree t = treeNodeManager.getTreeNodeAT(treeNode.getParentId());
                t.getChildren().add(treeNode);
            }
        }
        return treeNodeManager.getRoot();
    }


    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     */
//    public static OrganTreeVO mergeVO(List<OrganTreeVO> items){
//        TreeNodeManager treeNodeManager = new TreeNodeManager(items);
//        for (OrganTreeVO treeNode : items) {
//            if(!treeNode.getParentId().equals("")){
//                OrganTree t = treeNodeManager.getTreeNodeAT(treeNode.getParentId());
//                t.getChildren().add(treeNode);
//            }
//        }
//        return treeNodeManager.getRoot();
//    }
}
