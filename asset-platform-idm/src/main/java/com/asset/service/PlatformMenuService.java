package com.asset.service;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.mapper.MenuMapper;
import com.asset.mapper.PlatMenuMapper;
import com.asset.mapper.PlatRoleMapper;
import com.asset.utils.MenuNodeMerger;
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

/**
 * Service
 * 平台菜单服务
 */
@Service
@Transactional
public class PlatformMenuService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformMenuService.class);

    @Autowired
    PlatMenuMapper platMenuMapper;

    @Autowired
    PlatRoleMapper platRoleMapper;

    /**
     * 根据用户获取平台级菜单
     * @return
     */
    public PlatMenu getPlatMenus(){
        User currentUser = UserUtils.getCurrentUser();
        List<UserScene> roles = platRoleMapper.getPlatRoles(currentUser.getId(), GlobalConstant.CURRENT_SCENE);
        List<PlatMenu> platMenus = platMenuMapper.getPlatMenusByRoles(roles);
        return MenuNodeMerger.merge(platMenus);
    }
}
