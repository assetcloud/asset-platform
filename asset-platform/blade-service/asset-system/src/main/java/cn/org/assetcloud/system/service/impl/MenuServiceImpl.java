package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.dto.MenuDTO;
import cn.org.assetcloud.system.entity.Menu;
import cn.org.assetcloud.system.entity.MenuRole;
import cn.org.assetcloud.system.mapper.MenuMapper;
import cn.org.assetcloud.system.mapper.RoleMapper;
import cn.org.assetcloud.system.service.IMenuRoleService;
import cn.org.assetcloud.system.service.IMenuService;
import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.vo.MenuVO;
import cn.org.assetcloud.system.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    MenuMapper menuMapper;

    RoleMapper roleMapper;

    IMenuRoleService menuRoleService;

    RedisTemplate redisTemplate;

    /**
     * 根据用户获取平台级菜单
     * @return
     */
    public Menu getMenus(String userId){
//        List<UserRole> roles = roleMapper.getRoles(userId);
//        List<Menu> menus = menuMapper.getMenusByRoles(roles);
//        return MenuNodeMerger.merge(menus);
        return null;
    }

    public Menu getFactoryMenus(String userId) {
//        List<UserRole> roles = roleMapper.getRoles(userId);
//        List<Menu> menus = menuMapper.getMenusByRoles(roles);
//        return MenuNodeMerger.getFactoryNode(menus);
        return null;
    }

    @Override
    public List<Menu> menuList(String code, String name) {
        return this.list(Wrappers.<Menu>query().lambda()
                .like(Menu::getCode, code)
                .like(Menu::getName, name)
                .eq(Menu::getIsDeleted, 0));
    }

    @Override
    public IPage<MenuVO> selectMenuPage(IPage<MenuVO> page, MenuVO menu) {
        return page.setRecords(baseMapper.selectMenuPage(page, menu));
    }

    @Override
    public List<MenuVO> routes(String roleId) {
        List<Menu> allMenus = baseMapper.allMenu();
        List<Menu> roleMenus = baseMapper.roleMenu(Func.toIntList(roleId));
        List<Menu> routes = new LinkedList<>(roleMenus);
        roleMenus.forEach(roleMenu -> recursion(allMenus, routes, roleMenu));
        routes.sort(Comparator.comparing(Menu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper();
        List<Menu> collect = routes.stream().filter(x -> Func.equals(x.getCategory(), 1)).collect(Collectors.toList());
        return menuWrapper.listNodeVO(collect);
    }

    public void recursion(List<Menu> allMenus, List<Menu> routes, Menu roleMenu) {
        Optional<Menu> menu = allMenus.stream().filter(x -> Func.equals(x.getId(), roleMenu.getParentId())).findFirst();
        if (menu.isPresent() && !routes.contains(menu.get())) {
            routes.add(menu.get());
            recursion(allMenus, routes, menu.get());
        }
    }

    @Override
    public List<MenuVO> buttons(String roleId) {
        List<Menu> buttons = baseMapper.buttons(Func.toIntList(roleId));
        MenuWrapper menuWrapper = new MenuWrapper();
        return menuWrapper.listNodeVO(buttons);
    }

    @Override
    public List<MenuVO> tree() {
        return ForestNodeMerger.merge(baseMapper.tree());
    }

    @Override
    public List<MenuVO> grantTree(User user) {
        return null;
//        return ForestNodeMerger.merge(user.getTenantCode().equals(BladeConstant.ADMIN_TENANT_CODE) ? baseMapper.grantTree() : baseMapper.grantTreeByRole(Func.toIntList(user.getRoleId())));
    }

    @Override
    public List<String> roleTreeKeys(String roleIds) {
        List<MenuRole> menuRoles = menuRoleService.list(Wrappers.<MenuRole>query().lambda()
                .in(MenuRole::getRoleId, Func.toIntList(roleIds)));
        return menuRoles.stream().map(roleMenu -> Func.toStr(roleMenu.getMenuId())).collect(Collectors.toList());
   }

    @Override
    public List<Kv> authRoutes(User user) {
        if (Func.isEmpty(user)) {
            return null;
        }
        List<MenuDTO> routes = baseMapper.authRoutes(Func.toIntList(String.valueOf(user.getRoleId())));
        List<Kv> list = new ArrayList<>();
        routes.forEach(route -> list.add(Kv.init().set(route.getPath(), Kv.init().set("authority", Func.toStrArray(route.getAlias())))));
        return list;
    }
}
