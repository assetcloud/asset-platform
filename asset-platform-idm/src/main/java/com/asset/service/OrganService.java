package com.asset.service;

import com.asset.bean.AppTemplate;
import com.asset.bean.Application;
import com.asset.bean.OrganTree;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.mapper.AppTemplateMapper;
import com.asset.mapper.ApplicationMapper;
import com.asset.mapper.OrganTreeMapper;
import com.asset.mapper.UuidIdGenerator;
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
public class OrganService {

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
            return SystemConstant.NODE_ALREADY_EXISTS;
        }
        if(record.getId() == null){
            record.setId(uuidIdGenerator.generateId());
        }
        if (record.getParentId() == null){
            record.setParentId("0");
        }
        record.setCreatedTime(new Date());
        record.setStatus(true);
        record.setIsDeleted(0);
        return organTreeMapper.insert(record);
    }

    /**
     * 删除部门或单位节点
     * @param record
     * @return
     */
    public int deleteNode(OrganTree record){
        record = organTreeMapper.selectByPrimaryKey(record.getId());
        record.setStatus(false);
        record.setDisableTime(new Date());
        record.setIsDeleted(1);
        return organTreeMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据部门id获取信息
     * @param id
     * @return
     */
    public OrganTree getNode(String id){
        return organTreeMapper.selectByPrimaryKey(id);
    }
}
