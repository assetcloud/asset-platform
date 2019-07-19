package com.asset.service;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.mapper.*;
import com.asset.utils.CommonUtils;
import com.asset.utils.Func;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class SceneService {

    final static Logger LOGGER = LoggerFactory.getLogger(SceneService.class);

    @Autowired
    private SceneMapper sceneMapper;

    @Autowired
    private PlatRoleMapper platRoleMapper;

    @Autowired
    private PlatMenuMapper platMenuMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrganTreeMapper organTreeMapper;

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
     * 用户注册时添加场景
     * @param scene
     * @param user
     * @return
     */
    @Transactional
    public int addScene4User(Scene scene, User user) throws Exception {
        User var = userMapper.findUserByUsername(user.getAccountName());
        //如果用户名存在，返回错误
        if (var != null) {
            if (!var.getStatus() && var.getStage() == 1){
                throw new Exception("用户已注册，请联系管理员进行审核");
            } else if (var.getStatus() && var.getStage() == 2){
                throw new Exception("用户已存在");
            }
        }
        if(user.getId() == null){
            user.setId(uuidIdGenerator.generateId());
        }
        //待审核状态
        user.setStage(1);
        user.setStatus(false);
        user.setCreatedTime(new Date());
        //密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(user.getPassword());
        user.setPwd(encode);
        //添加用户
        userMapper.insertSelective(user);
        if (getSceneByName(scene.getSceneName()).size() > 0){
            //记录已存在
            throw new Exception("场景已存在");
        }
        if (scene.getId() == null){
            scene.setId(uuidIdGenerator.generateId());
        }
        //创建组织管理员
        PlatRole platRole = new PlatRole(scene.getId(), SystemConstant.SCENE_ADMIN_CH, SystemConstant.SCENE_ADMIN);
        platRoleMapper.insert(platRole);
        //为组织管理员初始化菜单
        List<PlatMenu> menus = platMenuMapper.selectAll();
        List<PlatMenuRole> menuRoles = new ArrayList<>();
        for (PlatMenu menu : menus) {
            PlatMenuRole v = new PlatMenuRole(menu.getId(), platRole.getId(), scene.getId());
            menuRoles.add(v);
        }
        platMenuMapper.batchAddPlatMenuRole(menuRoles);
        //用户与场景关联
        sceneMapper.userSceneBind(scene.getId(), user.getId(), platRole.getId());
        scene.setAddTime(new Date());
        scene.setIsDeleted(0);
        scene.setStatus(0);
        return sceneMapper.insert(scene);
    }

    /**
     * 新增场景
     * @param record
     * @return int
     */
    public int addSceneNormal(Scene record) {
        if (record.getId() == null){
            record.setId(uuidIdGenerator.generateId());
        }
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


}
