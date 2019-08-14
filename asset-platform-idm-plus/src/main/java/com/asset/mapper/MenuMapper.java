package com.asset.mapper;

import com.asset.bean.*;
import com.asset.dto.MenuDTO;
import com.asset.vo.MenuVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByRoles(List<UserRole> roleIds);

    int batchAddMenuRole(List<MenuRole> records);

    List<Menu> selectAll();

    List<Menu> selectAll4SAdmin();

    /**
     * 自定义分页
     *
     * @param page
     * @param menu
     * @return
     */
    List<MenuVO> selectMenuPage(IPage page, MenuVO menu);

    /**
     * 树形结构
     *
     * @return
     */
    List<MenuVO> tree();

    /**
     * 授权树形结构
     *
     * @return
     */
    List<MenuVO> grantTree();

    /**
     * 授权树形结构
     *
     * @param roleId
     * @return
     */
    List<MenuVO> grantTreeByRole(List<Integer> roleId);

    /**
     * 所有菜单
     *
     * @return
     */
    List<Menu> allMenu();

    /**
     * 权限配置菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> roleMenu(List<Integer> roleId);

    /**
     * 菜单树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> routes(List<Integer> roleId);

    /**
     * 按钮树形结构
     *
     * @param roleId
     * @return
     */
    List<Menu> buttons(List<Integer> roleId);

    /**
     * 获取配置的角色权限
     *
     * @param roleIds
     * @return
     */
    List<MenuDTO> authRoutes(List<Integer> roleIds);
}
