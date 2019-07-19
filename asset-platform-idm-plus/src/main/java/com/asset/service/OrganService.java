package com.asset.service;

import com.asset.bean.OrganTree;
import com.asset.common.SystemConstant;
import com.asset.mapper.OrganTreeMapper;
import com.asset.mapper.UuidIdGenerator;
import com.asset.utils.TreeNodeMerger;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
public class OrganService extends ServiceImpl<OrganTreeMapper, OrganTree> {

    final static Logger LOGGER = LoggerFactory.getLogger(OrganService.class);

    @Autowired
    OrganTreeMapper organTreeMapper;

    @Autowired
    UuidIdGenerator uuidIdGenerator;

    /**
     * 添加部门或单位节点
     * @param record
     * @return
     */
    public int addNode(OrganTree record){
        if(organTreeMapper.getNodeByName(record.getUnitName()) != null){
            //记录已存在
            return SystemConstant.RECORD_ALREADY_EXISTS;
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
        return organTreeMapper.insert(record);
    }

    /**
     * 批量新增节点
     * @param nodes
     * @return int
     */
    /*public int batchAdd(List<OrganScene> nodes){
        List<OrganScene> newTree = new ArrayList<>();
        for (OrganScene node : nodes) {
            node = organTreeMapper.selectByPrimaryKey(node.getId());
            newTree.add(node);
        }
        return organTreeMapper.batchInsert(newTree);
    }*/

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
    /*public List<OrganTree> getMainTree(){
        return organTreeMapper.getMainTree();
    }*/
    public OrganTree getMainTree(){
        List<OrganTree> items = organTreeMapper.selectAll();
        return TreeNodeMerger.merge(items);
    }

    public List<OrganTree> getTreeByScene(String sceneId){
        return organTreeMapper.getTreeByScene(sceneId);
    }

    /**
     * 节点模糊检索
     * @param unitName
     * @return List<OrganTree>
     */
    public List<OrganTree> searchNode(String unitName){
        return organTreeMapper.searchNode(unitName);
    }
}
