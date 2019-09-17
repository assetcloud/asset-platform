package com.asset.service.impl;

import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;
import com.asset.bean.User;
import com.asset.common.SystemConstant;
import com.asset.mapper.OrganSceneMapper;
import com.asset.mapper.OrganTreeMapper;
import com.asset.mapper.UserMapper;
import com.asset.mapper.UuidIdGenerator;
import com.asset.service.IOrganService;
import com.asset.utils.CommonUtils;
import com.asset.utils.MyNodeMerger;
import com.asset.utils.TreeNodeMerger;
import com.asset.vo.OrganTreeVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.tool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
public class OrganServiceImpl extends ServiceImpl<OrganTreeMapper, OrganTree> implements IOrganService {

    final static Logger LOGGER = LoggerFactory.getLogger(OrganServiceImpl.class);

    @Autowired
    OrganTreeMapper organTreeMapper;

    @Autowired
    OrganSceneMapper organSceneMapper;

    @Autowired
    UuidIdGenerator uuidIdGenerator;

    @Autowired
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
        if(record.getId() == null){
            record.setId(uuidIdGenerator.generateId());
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
        // 数据VO化
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

    @Override
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
