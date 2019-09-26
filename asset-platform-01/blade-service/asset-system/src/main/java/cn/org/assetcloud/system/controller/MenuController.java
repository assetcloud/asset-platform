package cn.org.assetcloud.system.controller;

import cn.org.assetcloud.system.entity.Menu;
import cn.org.assetcloud.system.entity.MenuRole;
import cn.org.assetcloud.system.service.IMenuRoleService;
import cn.org.assetcloud.system.service.IMenuService;
import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.vo.MenuVO;
import cn.org.assetcloud.system.wrapper.MenuWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("menu")
@Api(value = "菜单管理", tags = "菜单管理")
public class MenuController {

    IMenuService menuService;

    IDictClient dictClient;

    IMenuRoleService menuRoleService;

//    @ApiOperation(value = "应用工厂级资源", notes = "userId用户id", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "userId", name = "用户id", required = true, dataTypeClass = java.lang.String.class)
//    })
//    @GetMapping("factory/menus")
//    public R menusInFactory(@RequestParam("userId") String userId){
//        return R.data(menuService.getFactoryMenus(userId));
//    }

//    @GetMapping("list")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code", value = "菜单编号", paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "name", value = "菜单名称", paramType = "query", dataType = "string")
//    })
//    @ApiOperation(value = "获取菜单列表", notes = "已完成-管理员访问", tags = "平台级资源")
//    public R menuList(@RequestParam String code, @RequestParam String name){
//        List<Menu> menuList = menuService.menuList(code, name);
//        if (Func.hasEmpty(code) && Func.hasEmpty(name)){
//            return R.data(MenuNodeMerger.merge(menuList));
//        }
//        return R.data(menuList);
//    }

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "详情", notes = "传入menu")
    public R<MenuVO> detail(Menu menu) {
        Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
        MenuWrapper menuWrapper = new MenuWrapper(menuService, dictClient);
        return R.data(menuWrapper.entityVO(detail));
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "菜单编号", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "菜单名称", paramType = "query", dataType = "string")
    })
    @ApiOperation(value = "列表", notes = "传入menu")
    public R<List<MenuVO>> list(@ApiIgnore @RequestParam Map<String, Object> menu) {
        @SuppressWarnings("unchecked")
        List<Menu> list = menuService.list(Condition.getQueryWrapper(menu, Menu.class).lambda()
                .orderByAsc(Menu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper(menuService, dictClient);
        return R.data(menuWrapper.listNodeVO(list));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入menu实体")
    public R submit(@Valid @RequestBody Menu menu) {
        return R.status(menuService.saveOrUpdate(menu));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperation(value = "删除", notes = "传入ids")
    @Transactional
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        List<Integer> menuIds = Func.toIntList(ids);
        Collection<Menu> menus = menuService.listByIds(menuIds);
        menus.forEach(menu -> menu.setIsDeleted(1));
        menuRoleService.remove(Wrappers.<MenuRole>query().lambda().in(MenuRole::getMenuId, Func.toIntList(ids)));
        return R.status(menuService.updateBatchById(menus));
    }

    /**
     * 前端菜单数据
     */
    @GetMapping("/routes")
    @ApiOperation(value = "终端菜单数据", notes = "传roleId即可", position = 3)
    public R<List<MenuVO>> routes(User user) {
        List<MenuVO> list = menuService.routes(String.valueOf(user.getRoleId()));
        return R.data(list);
    }

    /**
     * 前端按钮数据
     */
    @GetMapping("/buttons")
    @ApiOperation(value = "前端按钮数据", notes = "前端按钮数据", position = 4)
    public R<List<MenuVO>> buttons(User user) {
        List<MenuVO> list = menuService.buttons(String.valueOf(user.getRoleId()));
        return R.data(list);
    }

    /**
     * 获取菜单树形结构
     */
    @GetMapping("/tree")
    @ApiOperation(value = "树形结构", notes = "树形结构", position = 5)
    public R<List<MenuVO>> tree() {
        List<MenuVO> tree = menuService.tree();
        return R.data(tree);
    }

    /**
     * 获取权限分配树形结构
     */
    @GetMapping("/grant-tree")
    @ApiOperation(value = "权限分配树形结构", notes = "尚未使用")
    public R<List<MenuVO>> grantTree(User user) {
        return R.data(menuService.grantTree(user));
    }

    /**
     * 获取权限分配树形结构
     */
    @GetMapping("/role-tree-keys")
    @ApiOperation(value = "角色所分配的树", notes = "已完成")
    public R<List<String>> roleTreeKeys(String roleIds) {
        return R.data(menuService.roleTreeKeys(roleIds));
    }

    /**
     * 获取配置的角色权限
     */
    @GetMapping("auth-routes")
    @ApiOperation(value = "菜单的角色权限")
    public R<List<Kv>> authRoutes(User user) {
        return R.data(menuService.authRoutes(user));
    }
}
