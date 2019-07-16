package com.asset.utils;

import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;

public class CommonUtils implements BaseUtils{

    /**
     * 组织节点转化为单据（组织与场景关联）
     * @param organTree
     * @return OrganScene
     */
    public static OrganScene NodeTransformer(OrganTree organTree){
        OrganScene organScene = new OrganScene();
        organScene.setNodeId(organTree.getId());
        organScene.setParentId(organTree.getParentId());
        organScene.setUnitName(organTree.getUnitName());
        organScene.setStatus(1);
        return organScene;
    }
}
