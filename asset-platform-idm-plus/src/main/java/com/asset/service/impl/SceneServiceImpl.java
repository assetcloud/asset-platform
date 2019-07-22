package com.asset.service.impl;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.mapper.*;
import com.asset.service.ISceneService;
import com.asset.utils.CommonUtils;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements ISceneService {

    final static Logger LOGGER = LoggerFactory.getLogger(SceneServiceImpl.class);

    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private UserSceneMapper userSceneMapper;

    @Autowired
    private OrganTreeMapper organTreeMapper;

    @Autowired
    UuidIdGenerator uuidIdGenerator;

    @Autowired
    MenuMapper menuMapper;

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
    public int addSceneNormal(Scene record) {
        record.setId(uuidIdGenerator.generateId());
        record.setAddTime(new Date());
        record.setIsDeleted(0);
        record.setStatus(1);
        OrganTree topNode = organTreeMapper.getTopNode();
        OrganScene organScene = CommonUtils.NodeTransformer(topNode);
        organScene.setSceneId(record.getId());
        sceneMapper.addNode(organScene);
        return sceneMapper.insertSelective(record);
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

    public int updateSceneSelective(Scene scene) {
        Scene existedScene = sceneMapper.selectByPrimaryKey(scene.getId());
        if (Func.isNull(existedScene)){
            throw new RuntimeException("场景id不正确");
        }
        return sceneMapper.updateByPrimaryKeySelective(scene);
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
    public List<Scene> findSceneByNameAlike(String sceneName){
        return sceneMapper.selectByNameAlike(sceneName);
    }

    public List<Scene> getScenesByUser(String userId){
        return sceneMapper.getScenesByUser(userId);
    }

    /**
     * 场景判空
     * @param sceneId
     * @return
     */
    public boolean isSceneEmpty(String sceneId){
        int cnt =  sceneMapper.getSceneNodes(sceneId);
        return cnt == 0;
    }

    @Override
    public boolean addUserScene(List<UserScene> userScenes) {
        return false;
    }

    /**
     * 判断用户是否属于某个场景
     * @param userId
     * @param sceneId
     * @return
     */
    @Override
    public boolean hasScene(String userId, String sceneId) {
        UserScene userScene = userSceneMapper.hasScene(userId, sceneId);
        return null != userScene;
    }

    public boolean userSceneBind(String sceneId, String userId, Long roleId){
        int flag = sceneMapper.userSceneBind(sceneId, userId, roleId);
        return flag > 0;
    }

    @Override
    public boolean sceneAvailable(String sceneId) {
        List<Scene> availableScene = sceneMapper.getAvailableScene(sceneId);
        return availableScene.size() > 0;
    }
}
