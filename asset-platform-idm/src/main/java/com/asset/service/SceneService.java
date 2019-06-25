package com.asset.service;

import com.asset.bean.OrganTree;
import com.asset.bean.Scene;
import com.asset.common.SystemConstant;
import com.asset.mapper.OrganTreeMapper;
import com.asset.mapper.SceneMapper;
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
public class SceneService {

    final static Logger LOGGER = LoggerFactory.getLogger(SceneService.class);

    @Autowired
    SceneMapper sceneMapper;

    @Autowired
    UuidIdGenerator uuidIdGenerator;

    /**
     * 获取所有场景
     * @return List<Scene>
     */
    public List<Scene> getAllScene(){
        return sceneMapper.selectAll();
    }

    /**
     * 新增场景
     * @param record
     * @return int
     */
    public int addScene(Scene record){
        if (getSceneByName(record.getSceneName()).size() > 0){
            //记录已存在
            return SystemConstant.RECORD_ALREADY_EXISTS;
        }
        if (record.getId() == null){
            record.setId(uuidIdGenerator.generateId());
        }
        record.setAddTime(new Date());
        record.setIsDeleted(0);
        record.setStatus(1);
        return sceneMapper.insert(record);
    }

    /**
     * 删除场景
     * @param sceneId
     * @return int
     */
    public int deleteScene(String sceneId){
        return sceneMapper.deleteScene(sceneId);
    }

    public int updateSceneInfo(Scene scene){
        return sceneMapper.updateSceneName(scene.getId(), scene.getSceneName());
    }

    /**
     * 通过名称查询场景
     * @param sceneName
     * @return List<Scene>
     */
    public List<Scene> getSceneByName(String sceneName){
        return sceneMapper.selectByName(sceneName);
    }

    /**
     * 通过名称模糊查询场景
     * @param sceneName
     * @return List<Scene>
     */
    public Scene findSceneByNameAlike(String sceneName){
        return sceneMapper.selectByNameAlike(sceneName);
    }
}
