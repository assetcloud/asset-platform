package com.asset.service;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.mapper.AppTemplateMapper;
import com.asset.mapper.ApplicationMapper;
import com.asset.mapper.MenuMapper;
import com.asset.mapper.UuidIdGenerator;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 将应用添加到菜单表中，并赋予给系统管理员和默认角色
     * @param menu
     * @param application
     * @return int
     */
    public int addAppMenu(Menu menu, Application application){
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
        if(menuMapper.insert(menu) < 0){
            return -1;
        }
        if(addMenu4Admin(menu) < 0){
            return -1;
        }
        return 1;
    }

    /**
     * 添加表单类型的菜单，并赋予给系统管理员和默认角色
     * @param parentMenu
     * @param formModelInfo
     * @return
     */
    public int addFormMenu(Menu parentMenu, FormModelInfo formModelInfo){
        Menu menu = new Menu();
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
        if(menuMapper.insert(menu) < 0){
            return -1;
        }
        if(addMenu4Admin(menu) < 0){
            return -1;
        }
        if(addFuncMenu(menu) < 0){
            return -1;
        }
        return 1;
    }

    /**
     * 将操作添加为菜单
     * @param formMenu
     * @return int
     */
    public int addFuncMenu(Menu formMenu){
        List<Menu> menuList = SystemConstant.MENU_LIST;
        for(int i = 0; i<menuList.size();i++){
            Menu menu = menuList.get(i);
            menu.setParentId(formMenu.getId());
            menu.setPath(formMenu.getPath() + menu.getPath());
            menu.setIsDeleted(0);
            menu.setAddTime(new Date());
        }
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
     * 为当前角色查询菜单
     * @return
     */
    public List<Menu> getMenusByUserId(String userId){
        return menuMapper.getMenusByUser(userId);
    }

    /**
     * 通过用户id获取应用资源
     * @return List<Menu>
     */
    public List<Menu> getAppMenusByRole(){
        return menuMapper.getAppMenusByUser(UserUtils.getCurrentUser().getId());
    }

    /**
     * 通过用户id和应用id获取表单资源
     * @return List<Menu>
     */
    public List<Menu> getFormMenusByApp(String appId){
        return menuMapper.getFormMenusByApp(UserUtils.getCurrentUser().getId(), appId);
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
}
