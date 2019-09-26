package cn.org.assetcloud.system.utils;

import cn.org.assetcloud.system.entity.Resource;

import java.util.List;

/**
 * 节点归并类
 * @author hjhu
 *
 */
public class ResourceNodeMerger {

    /**
     * 将节点数组归并为一棵树（填充节点的children域）
     * 时间复杂度为O(n^2)
     * @param items 节点域
     * @return
     */
    public static Resource merge(Resource[] items){
        ResourceNodeManager resourceNodeManager = new ResourceNodeManager(items);
        for (Resource node : items) {
            if(node.getParentId() != 0){
                Resource t = resourceNodeManager.getTreeNodeAT(node.getParentId());
                t.getChildren().add(node);
            }
        }
        return resourceNodeManager.getRoot();
    }

    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     */
    public static Resource merge(List<Resource> items){
        ResourceNodeManager resourceNodeManager = new ResourceNodeManager(items);
        for (Resource node : items) {
            if(node.getParentId() != 0){
                Resource t = resourceNodeManager.getTreeNodeAT(node.getParentId());
                t.getChildren().add(node);
            }
        }
        return resourceNodeManager.getRoot();
    }

    /**
     *  将节点数组归并为一棵树（填充节点的children域）
     *  时间复杂度为O(n^2)
     * @param items 节点域
     * @return OrganTree
     */
    public static Resource getFactoryNode(List<Resource> items){
        ResourceNodeManager resourceNodeManager = new ResourceNodeManager(items);
        for (Resource node : items) {
            if(node.getParentId() != 0){
                Resource t = resourceNodeManager.getTreeNodeAT(node.getParentId());
                t.getChildren().add(node);
            }
        }
        return resourceNodeManager.getTreeNodeAT((long) 4);
    }
}
