package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.OrganScene;
import cn.org.assetcloud.system.entity.OrganTree;
import cn.org.assetcloud.system.mapper.OrganSceneMapper;
import cn.org.assetcloud.system.mapper.OrganTreeMapper;

import cn.org.assetcloud.system.service.IOrganService;
import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.user.mapper.UserMapper;
import cn.org.assetcloud.system.utils.CommonUtils;
import cn.org.assetcloud.system.utils.MyNodeMerger;
import cn.org.assetcloud.system.utils.TreeNodeMerger;
import cn.org.assetcloud.system.vo.OrganTreeVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
@AllArgsConstructor
public class OrganServiceImpl extends ServiceImpl<OrganTreeMapper, OrganTree> implements IOrganService {

	private OrganTreeMapper organTreeMapper;

	private OrganSceneMapper organSceneMapper;

    private UserMapper userMapper;

    /**
     * 添加部门或单位节点
     * @param record
     * @return
     */
    public boolean addNode(OrganTree record){
        if(organTreeMapper.getNodeByName(record.getUnitName()) != null){
            //记录已存在
            return false;
        }
        if (record.getParentId() == null){
            record.setParentId("");
        }
        record.setCreatedTime(new Date());
        record.setStatus(1);
        record.setIsDeleted(0);
        return this.save(record);
    }


    /**
     * 批量新增节点
     * @param nodes
     * @return int
     */
    public boolean batchAddNodes(List<OrganTree> nodes){
        int i = organTreeMapper.batchInsertNode(nodes);
        return i >= 0;
    }

    @Override
    public List<User> getUsersByScene(String sceneId) {
        return userMapper.getUsersByScene(sceneId);
    }

    /**
     * 删除部门或单位节点
     * @param record
     * @return int
     */
    public int deleteNode(OrganTree record){
        record.setStatus(0);
        record.setDisableTime(new Date());
        record.setIsDeleted(1);
        return organTreeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 批量删除部门或单位节点
     * @param organTrees
     * @return int
     */
    public int batchDeleteNode(List<OrganTree> organTrees){
        for (OrganTree node : organTrees){
            node.setStatus(0);
            node.setDisableTime(new Date());
            node.setIsDeleted(1);
        }
        return organTreeMapper.batchUpdate(organTrees);
    }

    /**
     * 根据部门id获取信息
     * @param id
     * @return
     */
    public OrganTree getNode(String id){
        return organTreeMapper.selectByPrimaryKey(id);
    }

    /**
     * 编辑部门信息
     * @param record
     * @return int
     */
    /*public int updateNode(OrganTree record){
        return organTreeMapper.updateByPrimaryKeySelective(record);
    }*/
    public int updateNode(OrganTree record){
        return organTreeMapper.updateById(record);
    }



    /**
     * 获取组织树全部信息
     * @return
     */
    public OrganTree getMainTree(){
        List<OrganTree> items = organTreeMapper.selectAll();
        return TreeNodeMerger.merge(items);
    }

    public OrganTree getTreeByScene2(String sceneId){
        List<OrganScene> list = organTreeMapper.getTreeByScene(sceneId);
        List<OrganTree> organTrees = CommonUtils.NodeTransformer(list);
        return TreeNodeMerger.merge(organTrees);
    }

    public List<OrganTreeVO> getTreeByScene(String sceneId){
        List<OrganScene> list = organTreeMapper.getTreeByScene(sceneId);
        List<OrganTreeVO> voList = CommonUtils.nodeTransform(list);
        return MyNodeMerger.merge(voList);
    }

    @Override
    public List<OrganScene> getNodeByScene(String sceneId, String nodeId) {
        return organTreeMapper.getNodeByScene(sceneId, nodeId);
    }

    /**
     * 节点模糊检索
     * @param unitName
     * @return List<OrganTree>
     */
    public List<OrganTree> searchNode(String unitName){
        return organTreeMapper.searchNode(unitName);
    }

    public boolean nodeExists(String unitName){
        List<OrganTree> list = organTreeMapper.getByName(unitName);
        return list.size() > 0;
    }

//    @Override
    public boolean hasParent(String parentId) {
        OrganTree node = organTreeMapper.selectById(parentId);
        return node != null;
    }

    @Override
    public OrganTree selectAllWithoutMerge(String sceneId) {
        List<OrganTree> items = organTreeMapper.selectAll();
        List<OrganScene> organScenes = organTreeMapper.getTreeByScene(sceneId);
        items.stream()
                .filter(map -> organScenes.stream().anyMatch(map1 -> map.getId().equals(map1.getNodeId())))
                .forEach(map1 -> map1.setChecked(1));
        return TreeNodeMerger.merge(items);
    }
}
