package com.asset.service.impl;

import com.asset.bean.Menu;
import com.asset.bean.UserRole;
import com.asset.mapper.MenuMapper;
import com.asset.mapper.RoleMapper;
import com.asset.service.IMenuService;
import com.asset.utils.MenuNodeMerger;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户获取平台级菜单
     * @return
     */
    public Menu getMenus(String userId){
        List<UserRole> roles = roleMapper.getRoles(userId);
        List<Menu> menus = menuMapper.getMenusByRoles(roles);
        return MenuNodeMerger.merge(menus);
    }

    public Menu getFactoryMenus(String userId) {
//        User currentUser = UserUtils.getCurrentUser();
//        String currentScene = (String) redisTemplate.opsForValue().get(UserUtils.getCurrentUser().getId());
        List<UserRole> roles = roleMapper.getRoles(userId);
        List<Menu> menus = menuMapper.getMenusByRoles(roles);
        return MenuNodeMerger.getFactoryNode(menus);
    }
}
