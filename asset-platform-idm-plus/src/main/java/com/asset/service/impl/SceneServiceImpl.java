package com.asset.service.impl;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.mapper.*;
import com.asset.service.*;
import com.asset.utils.CommonUtils;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    private IOrganSceneService organSceneService;

    @Autowired
    private IUserSceneService userSceneService;

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

    @Autowired
    private OrganSceneMapper organSceneMapper;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private IRoleGroupService roleGroupService;

    @Autowired
    private ISceneRelationService sceneRelationService;

    /**
     * 获取所有场景
     * @return List<Scene>
     */
    public List<Scene> getAllScene(){
        return sceneMapper.selectAll();
    }

    @Override
    public List<Scene> getSceneInvert(String userId, String sceneName) {
        return sceneMapper.getSceneInvert(userId, sceneName);
    }

    /**
     * 控制台-新增场景
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
        organSceneMapper.insert(organScene);
        return sceneMapper.insertSelective(record);
    }

    /**
     * 用户注册时，新增场景
     * @param record
     * @return int
     */
    public int addScene4Reg(Scene record, List<String> nodeList) {
        record.setId(uuidIdGenerator.generateId());
        record.setAddTime(new Date());
        record.setIsDeleted(0);
        record.setStatus(0);
        //在场景中添加顶级节点
        List<OrganScene> nodes = new ArrayList<>();
        OrganTree topNode = organTreeMapper.getTopNode();
        OrganScene organScene = CommonUtils.NodeTransformer(topNode);
        organScene.setSceneId(record.getId());
        nodes.add(organScene);
        // 在场景中添加至少一个非顶级节点
        List<OrganTree> treeNodes = organTreeMapper.selectBatchIds(nodeList);
        for (OrganTree treeNode : treeNodes) {
            OrganScene obj = CommonUtils.NodeTransformer(treeNode);
            obj.setSceneId(record.getId());
            nodes.add(obj);
        }
        organSceneService.saveBatch(nodes);
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

    public boolean userSceneBind(String sceneId, String userId){
        OrganScene rootNode = organSceneMapper.getRootNode(sceneId);
        UserScene userScene = new UserScene(sceneId, userId, rootNode.getNodeId(), 0);
        return userSceneMapper.insert(userScene) > 0;
    }

    @Override
    @Transactional
    public boolean userSceneBind(List<String> sceneIds, String userId) {
        for (String sceneId : sceneIds){
            // 为新用户设置默认部门
            OrganScene rootNode = organSceneMapper.getRootNode(sceneId);
            UserScene userScene = new UserScene(sceneId, userId, rootNode.getNodeId(), 0);
            userSceneMapper.insert(userScene);
            // 为新用户设置默认角色
            SceneRole sceneRole = sceneRoleService.getDefaultRole(sceneId);
            sceneRelationService.save(new SceneRelation(userId, sceneRole.getId()));
        }
        return true;
    }

    @Override
    public boolean addSceneMembers(List<String> userIds, String sceneId) {
        OrganScene rootNode = organSceneMapper.getRootNode(sceneId);
        List<UserScene> records = new ArrayList<>();
        userIds.forEach(map -> records.add(new UserScene(sceneId, map, rootNode.getNodeId(), 0, 1)));
        return userSceneService.saveBatch(records);
    }

    @Override
    public boolean sceneAvailable(String sceneId) {
        List<Scene> availableScene = sceneMapper.getAvailableScene(sceneId);
        return availableScene.size() > 0;
    }

    public boolean enableScene(String userId, String sceneId){
        Scene scene = sceneMapper.selectByPrimaryKey(sceneId);
        scene.setStatus(1);
        scene.setIsDeleted(0);
        sceneMapper.updateById(scene);
        return userSceneMapper.updateByUserAndScene(userId, sceneId) > 0;
    }

    @Override
    public List<OrganScene> getNodesByNameAlike(String keyword, String sceneId) {
        return sceneMapper.getNameAlike(keyword, sceneId);
    }

    @Override
    public boolean generateRoleInfo(Scene scene, String userId) {
        RoleGroup roleGroup = new RoleGroup(SystemConstant.DEFAULT_GROUP_NAME, 0, new Date(), scene.getId());
        roleGroupService.save(roleGroup);
        SceneRole roleAdmin = new SceneRole(scene.getId(), SystemConstant.SCENE_ADMIN_CH, SystemConstant.SCENE_ADMIN);
        SceneRole roleDefault = new SceneRole(scene.getId(), SystemConstant.SCENE_DEFAULT_CH, SystemConstant.SCENE_DEFAULT);
        List<SceneRole> list = new ArrayList<>();
        roleAdmin.setGroupId(roleGroup.getId());
        roleAdmin.setStatus(false);
        roleAdmin.setCreatedTime(new Date());
        roleAdmin.setEnableTime(new Date());
        roleDefault.setGroupId(roleGroup.getId());
        roleDefault.setStatus(false);
        roleDefault.setRoleDefault(1);
        roleDefault.setCreatedTime(new Date());
        roleDefault.setEnableTime(new Date());
        list.add(roleAdmin);
        list.add(roleDefault);
        sceneRoleService.addDefaultRole4Reg(scene.getId(), list);
        //将该用户设置为组织管理员
        return sceneRelationService.save(new SceneRelation(userId, roleAdmin.getId()));
    }
}
