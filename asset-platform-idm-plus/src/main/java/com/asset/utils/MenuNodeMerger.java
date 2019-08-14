package com.asset.utils;

import com.asset.bean.Menu;

import java.util.List;

/**
 * 节点归并类
 * @author hjhu
 *
 */
public class MenuNodeMerger {

    /**
     * 将节点数组归并为一棵树（填充节点的children域）
     * 时间复杂度为O(n^2)
     * @param items 节点域
     * @return
     */
//    public static Menu merge(Menu[] items){
//        MenuNodeManager menuNodeManager = new MenuNodeManager(items);
//        for (Menu node : items) {
//            if(node.getParentId() != 0){
//                Menu t = menuNodeManager.getTreeNodeAT(node.getParentId());
//                t.getChildren().add(node);
//            }
//        }
//        return menuNodeManager.getRoot();
//    }
//
//    /**
//     *  将节点数组归并为一棵树（填充节点的children域）
//     *  时间复杂度为O(n^2)
//     * @param items 节点域
//     * @return OrganTree
//     */
//    public static Menu merge(List<Menu> items){
//        MenuNodeManager menuNodeManager = new MenuNodeManager(items);
//        for (Menu node : items) {
//            if(node.getParentId() != 0){
//                Menu t = menuNodeManager.getTreeNodeAT(node.getParentId());
//                t.getChildren().add(node);
//            }
//        }
//        return menuNodeManager.getRoot();
//    }
//
//    /**
//     *  将节点数组归并为一棵树（填充节点的children域）
//     *  时间复杂度为O(n^2)
//     * @param items 节点域
//     * @return OrganTree
//     */
//    public static Menu getFactoryNode(List<Menu> items){
//        MenuNodeManager menuNodeManager = new MenuNodeManager(items);
//        for (Menu node : items) {
//            if(node.getParentId() != 0){
//                Menu t = menuNodeManager.getTreeNodeAT(node.getParentId());
//                t.getChildren().add(node);
//            }
//        }
//        /**
//         * 获取应用工场（或系统管理的id）下的菜单
//         */
//        return menuNodeManager.getTreeNodeAT((long) 4);
//    }
}
