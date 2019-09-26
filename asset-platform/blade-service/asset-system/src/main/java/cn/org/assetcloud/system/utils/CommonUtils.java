package cn.org.assetcloud.system.utils;

import cn.org.assetcloud.system.entity.OrganScene;
import cn.org.assetcloud.system.entity.OrganTree;
import cn.org.assetcloud.system.vo.OrganTreeVO;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 组织节点转化为单据（组织与场景关联）
     * @param organScenes
     * @return OrganScene
     */
    public static List<OrganTree> NodeTransformer(List<OrganScene> organScenes){
        ArrayList<OrganTree> organTrees = new ArrayList<>();
        for (OrganScene organScene : organScenes) {
            OrganTree node = new OrganTree();
            node.setId(organScene.getNodeId());
            node.setParentId(organScene.getParentId());
            node.setUnitName(organScene.getUnitName());
            organTrees.add(node);
        }
        return organTrees;
    }


    /**
     * 组织节点转化为单据（组织与场景关联）
     * @param organScenes
     * @return OrganScene
     */
    public static List<OrganTreeVO> nodeTransform(List<OrganScene> organScenes){
        ArrayList<OrganTreeVO> organTrees = new ArrayList<>();
        for (OrganScene organScene : organScenes) {
            OrganTreeVO node = new OrganTreeVO();
            node.setId(organScene.getNodeId());
            node.setParentId(organScene.getParentId());
            node.setUnitName(organScene.getUnitName());
            organTrees.add(node);
        }
        return organTrees;
    }

}
