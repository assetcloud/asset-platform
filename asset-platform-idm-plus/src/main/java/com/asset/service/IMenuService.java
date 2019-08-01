package com.asset.service;

import com.asset.bean.Menu;
import com.asset.bean.User;
import com.asset.vo.MenuVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.core.tool.support.Kv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IMenuService extends IService<Menu> {

    Menu getMenus(String userId);

    Menu getFactoryMenus(String userId);

    List<Menu> menuList(String code, String name);

    /**
     * 自定义分页
     *
     * @param page
     * @param menu
     * @return
     */
    IPage<MenuVO> selectMenuPage(IPage<MenuVO> page, MenuVO menu);

    /**
     * 菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> routes(String roleId);

    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> buttons(String roleId);

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

    /**
     * 授权树形结构
     *
     * @param user
     * @return
     */
    List<MenuVO> grantTree(User user);

    /**
     * 默认选中节点
     *
     * @param roleIds
     * @return
     */
    List<String> roleTreeKeys(String roleIds);

    /**
     * 获取配置的角色权限
     *
     * @param user
     * @return
     */
    List<Kv> authRoutes(User user);
}
