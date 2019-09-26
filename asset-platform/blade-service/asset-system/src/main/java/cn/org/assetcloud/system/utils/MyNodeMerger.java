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

import java.util.List;
import java.util.Objects;

public class MyNodeMerger {

    public static <E extends MyNode> List<E> merge(List<E> items) {
        MyNodeManager<E> nodeManager = new MyNodeManager(items);
        items.forEach((forestNode) -> {
            if (!Objects.equals(forestNode.getParentId(), "")) {
                MyNode node = nodeManager.getTreeNodeAT(forestNode.getParentId());
                if (Objects.nonNull(node)) {
                    node.getChildren().add(forestNode);
                } else {
                    nodeManager.addParentId(forestNode.getId());
                }
            }
        });
        return nodeManager.getRoot();
    }
}
