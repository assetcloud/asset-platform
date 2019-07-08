package com.asset.utils;

import com.asset.bean.OrganTree;
import com.asset.bean.PlatMenu;

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
    public static PlatMenu merge(PlatMenu[] items){
        MenuNodeManager menuNodeManager = new MenuNodeManager(items);
        for (PlatMenu node : items) {
            if(node.getParentId() != 0){
                PlatMenu t = menuNodeManager.getTreeNodeAT(node.getParentId());
                t.getChildren().add(node);
            }
        }
        return menuNodeManager.getRoot();
    }

    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     */
    public static PlatMenu merge(List<PlatMenu> items){
        MenuNodeManager menuNodeManager = new MenuNodeManager(items);
        for (PlatMenu node : items) {
            if(node.getParentId() != 0){
                PlatMenu t = menuNodeManager.getTreeNodeAT(node.getParentId());
                t.getChildren().add(node);
            }
        }
        return menuNodeManager.getRoot();
    }
}
