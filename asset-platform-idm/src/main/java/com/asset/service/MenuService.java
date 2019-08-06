package com.asset.service;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.mapper.AppTemplateMapper;
import com.asset.mapper.ApplicationMapper;
import com.asset.mapper.MenuMapper;
import com.asset.mapper.UuidIdGenerator;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by hjhu on 2019/6/3.
 */

@Service
@Transactional
public class MenuService {

    final static Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将应用添加到菜单表中，并赋予给系统管理员和默认角色
     * @param application
     * @return int
     */
    public int addAppMenu(Application application) throws Exception {
        Menu menu = new Menu();
        menu.setCode(SystemConstant.CODE_APP);
        menu.setName(application.getApplicationName());
        menu.setIconCls(application.getIconCls());
        if (application.getId() != null){
            menu.setPath(application.getId());
        }else {
            return -2;
        }
        menu.setLevel(0);
        menu.setSort(0);
        menu.setParentId((long) 0);
        menu.setIsDeleted(0);
        menu.setAddTime(new Date());
        menu.setCategory(1);
        menu.setGroupId("");
        menu.setGroupName("");
        //从redis获取当前场景
        String currentScene = (String) redisTemplate.opsForValue().get(UserUtils.getCurrentUser().getId());
        menu.setSceneId(currentScene);
//        if(menuMapper.findAppMenuByName(application.getApplicationName(), GlobalConstant.CURRENT_SCENE).size() > 0){
        if(menuMapper.findAppMenuByName(application.getApplicationName(), currentScene).size() > 0){
            throw new Exception("应用名称已被使用，请更换后再试");
        }
        menuMapper.insert(menu);
        addMenu4Admin(menu);
        return 1;
    }
    /**
     * 添加表单类型的菜单，并赋予给系统管理员和默认角色
     * @param parentMenu
     * @param formModelInfo
     * @return int
     */
    public int addFormMenu(Menu parentMenu, FormModelInfo formModelInfo) throws Exception {
        String currentScene = (String) redisTemplate.opsForValue().get(UserUtils.getCurrentUser().getId());
//        if(menuMapper.findFormMenuByName(formModelInfo.getFormName(), GlobalConstant.CURRENT_SCENE).size() > 0){
        if(menuMapper.findFormMenuByName(formModelInfo.getFormName(), currentScene).size() > 0){
            throw new Exception("表单名称已被使用，请更换后再试");
        }
        Menu menu = new Menu();
        //1-应用；2-表单；3-表单操作
        menu.setCategory(2);
        menu.setParentId(parentMenu.getId());
        menu.setAddTime(new Date());
        menu.setIsDeleted(0);
        menu.setSort(0);
        menu.setLevel(0);
        menu.setPath(formModelInfo.getFormModelId());
        menu.setName(formModelInfo.getFormName());
        menu.setIconCls(formModelInfo.getIconCls());
        menu.setCode(SystemConstant.CODE_FORM);
        // 为表单设置分组
        menu.setGroupId(formModelInfo.getGroupId());
        menu.setGroupName(formModelInfo.getGroupName());
        //为表单设置当前场景，从redis获取
        menu.setSceneId(currentScene);
        menuMapper.insert(menu);
        addMenu4Admin(menu);
        return addFuncMenu(menu);
    }

    /**
     * 将操作添加为菜单
     * @param formMenu
     * @return int
     */
    public int addFuncMenu(Menu formMenu) throws CloneNotSupportedException {
        Menu [] array = SystemConstant.MENUS;
        List<Menu> menuList = new ArrayList<>();
        for (Menu value : array) {
            //操作型菜单深拷贝
            Menu var = (Menu) value.clone();
            var.setParentId(formMenu.getId());
            var.setPath(formMenu.getPath() + var.getPath());
            var.setIsDeleted(0);
            var.setAddTime(new Date());
            menuList.add(var);
        }
        /*for (Menu menu : menuList) {
            menu.setParentId(formMenu.getId());
            menu.setPath(formMenu.getPath() + menu.getPath());
            menu.setIsDeleted(0);
            menu.setAddTime(new Date());
        }*/
        int flag = menuMapper.batchInsert(menuList);
        if(flag < 0){
            return -1;
        }
        //为管理员增加这些权限
        flag = menuMapper.batchInsertMenuRole(menuList);
        if(flag < 0){
            return -1;
        }
        return 1;
    }

    /**
     * 复制应用时，将应用，对应的表单及操作权限都赋予给系统管理员
     * @param menu
     * @return int
     */
    public int addMenuAll(Menu menu){
        //TODO:复制应用时，将应用，对应的表单及操作权限都赋予给系统管理员
        return 1;
    }

    /**
     * 为当前角色查询菜单
     * @return
     */
    public List<Menu> getMenusByCurrentUser(){
        return menuMapper.getMenusByUser(UserUtils.getCurrentUser().getId());
    }

    /**
     * 为当前用户获取菜单
     * @return
     */
    public List<Menu> getMenusByUserId(String userId){
        return menuMapper.getMenusByUser(userId);
    }

    /**
     *
     * @param roleId
     * @return List<Menu>
     */
    public List<Menu> getMenusByRoleId(Long roleId){
        return menuMapper.getMenusByRole(roleId);
    }


    /**
     * 通过用户id获取应用资源
     * @return List<Menu>
     */
    /*public List<Menu> getAppMenusByUser(){
        return menuMapper.getAppMenusByUser(UserUtils.getCurrentUser().getId());
    }*/
    public List<Menu> getAppMenusByUser(String sceneId){
        return menuMapper.getMenusByUserAndScene(UserUtils.getCurrentUser().getId(), sceneId);
    }

    /**
     * 通过用户id和应用id获取表单资源
     * @return List<Menu>
     */
    public List<Menu> getFormMenusByApp(String appId, String sceneId){
        return menuMapper.getFormMenusByApp(UserUtils.getCurrentUser().getId(), appId, sceneId);
    }

    /**
     * 为系统管理员增加菜单
     * @param menu
     * @return
     */
    public int addMenu4Admin(Menu menu){
        return menuMapper.addMenuRole(menu.getId(),SystemConstant.ADMIN_ROLE_ID);
    }

    /**
     * 为系统默认角色增加菜单
     * @param menu
     * @return
     */
    public int addMenu4DefaultRole(Menu menu){
        return menuMapper.addMenuRole(menu.getId(),SystemConstant.DEFAULT_ROLE_ID);
    }

    /**
     * 按照应用的id，查询该记录是否已经存在
     * @param applicationId
     * @return
     */
    public Menu getMenuByPath(String applicationId){
        return menuMapper.getByPath(applicationId);
    }

    /**
     * 更新表单信息（主要用于绑定表单流程与场景）
     * @return
     */
    public int updateFormInfo(String formModelId, String sceneId){
        return menuMapper.updateFormInfo(formModelId, sceneId);
    }
}
