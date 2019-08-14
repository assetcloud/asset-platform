package com.asset.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceVONodeManager<T extends RNode> {

    private List<T> list;

    private List<Long> parentIds = new ArrayList();

    public ResourceVONodeManager(List<T> items) {
        this.list = items;
    }

    public RNode getTreeNodeAT(long id) {
        Iterator var2 = this.list.iterator();

        RNode forestNode;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            forestNode = (RNode)var2.next();
        } while(forestNode.getId() != id);

        return forestNode;
    }

    public void addParentId(Long parentId) {
        this.parentIds.add(parentId);
    }

    public List<T> getRoot() {

        List<T> roots = new ArrayList();
        Iterator var2 = this.list.iterator();

        while(true) {
            T forestNode;
            do {
                if (!var2.hasNext()) {
                    return roots;
                }

                forestNode = (T) var2.next();
            } while(forestNode.getParentId() != 0 && !this.parentIds.contains(forestNode.getId()));

            roots.add(forestNode);
        }
    }
}
