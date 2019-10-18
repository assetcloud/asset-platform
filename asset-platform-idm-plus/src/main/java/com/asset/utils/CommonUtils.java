package com.asset.utils;

import com.asset.apiBean.RemoteUnitInfo;
import com.asset.apiBean.RemoteUserInfo;
import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;
import com.asset.bean.User;
import com.asset.vo.OrganTreeVO;
import org.springframework.beans.BeanUtils;

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

    /**
     * 远程组织数据转化
     * @param organTreeArrayList 组织节点list
     * @param remoteUnitInfoList 远程组织数据list
     */
    public static void remoteOrganTransformer(List<OrganTree> organTreeArrayList, List<RemoteUnitInfo> remoteUnitInfoList){
        if (remoteUnitInfoList.size() <= 0){
            return;
        }
        OrganTree organTree = new OrganTree();
        for (RemoteUnitInfo info : remoteUnitInfoList) {
            organTree.setUnitName(info.getUnitName());
            organTree.setId(info.getUnitCode());
            organTree.setParentId(info.getParentUnitCode());
            switch (info.getStatus()){
                case 1:
                    organTree.setIsDeleted(0);
                    organTree.setStatus(1);
                case 2:
                    organTree.setIsDeleted(1);
                    organTree.setStatus(0);
                default:
                    organTree.setIsDeleted(0);
                    organTree.setStatus(1);
            }
            organTreeArrayList.add(organTree);
        }
    }

    public static void remoteUserTransformer(List<User> userList, List<RemoteUserInfo> remoteUserInfoList){
        if (remoteUserInfoList.size() <= 0){
            return;
        }
        User user = new User();
        for (RemoteUserInfo info : remoteUserInfoList) {
            user.setAccountName(info.getAccountName());
            user.setPhoneNumber(info.getMobilePhoneNumber());
            user.setRealName(info.getUserName());
            user.setId(info.getUserCode());
            switch (info.getStatus()){
                case 1:
                    user.setStatus(1);
                case 2:
                    user.setStatus(0);
                default:
                    user.setStatus(1);
            }
            userList.add(user);
        }
    }
}
