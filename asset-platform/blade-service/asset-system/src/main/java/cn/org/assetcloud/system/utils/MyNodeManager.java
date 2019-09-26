/*
 * Copyright (c) 2019.
 * *
 *  * All rights Reserved, Designed By hjhu
 *  * @Title:  ${file_name}
 *  * @Package ${package_name}
 *  * @Description:    ${todo}(用一句话描述该文件做什么)
 *  * @author: HDU
 *  * @date:   ${date} ${time}
 *  * @version V1.0
 *  * @Copyright: ${year} www.assetcloud.org.cn Inc. All rights reserved.
 *
 */

package cn.org.assetcloud.system.utils;

import cn.org.assetcloud.system.vo.MyNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class MyNodeManager<E extends MyNode>{

    private List<E> list;

    private List<String> parentIds = new ArrayList();

    public MyNodeManager(List<E> items){
        this.list = items;
    }

    /**
     * 获取目标节点
     * @param id
     * @return
     */
    public MyNode getTreeNodeAT(String id) {
        Iterator var = this.list.iterator();

        MyNode node;
        do {
            if (!var.hasNext()) {
                return null;
            }

            node = (MyNode) var.next();
        } while(!node.getId().equals(id));

        return node;
    }

    public void addParentId(String parentId) {
        this.parentIds.add(parentId);
    }

    public List<E> getRoot() {

        List<E> roots = new ArrayList();
        Iterator var2 = this.list.iterator();

        while(true) {
            E forestNode;
            do {
                if (!var2.hasNext()) {
                    return roots;
                }

                forestNode = (E) var2.next();
            } while(!Objects.equals(forestNode.getParentId(), "") && !this.parentIds.contains(forestNode.getId()));
            roots.add(forestNode);
        }
    }
}
