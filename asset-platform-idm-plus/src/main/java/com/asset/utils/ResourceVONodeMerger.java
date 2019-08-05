package com.asset.utils;

import java.util.List;

public class ResourceVONodeMerger {

    public ResourceVONodeMerger() {
    }

    public static <T extends RNode> List<T> merge(List<T> items) {
        @SuppressWarnings("unchecked")
        ResourceVONodeManager<T> forestNodeManager = new ResourceVONodeManager(items);
        items.forEach((forestNode) -> {
            if (forestNode.getParentId() != 0) {
                RNode node = forestNodeManager.getTreeNodeAT(forestNode.getParentId());
                if (node != null) {
                    node.getChildren().add(forestNode);
                } else {
                    forestNodeManager.addParentId(forestNode.getId());
                }
            }

        });
        return forestNodeManager.getRoot();
    }
}
