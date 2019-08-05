package com.asset.controller.system;

import com.asset.bean.Menu;
import com.asset.bean.User;
import com.asset.service.IDictService;
import com.asset.service.IMenuService;
import com.asset.utils.Condition;
import com.asset.vo.MenuVO;
import com.asset.wrapper.MenuWrapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
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

    IDictService dictService;

//    @ApiOperation(value = "应用工厂级资源", notes = "userId用户id", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "userId", name = "用户id", required = true, dataTypeClass = java.lang.String.class)
//    })
//    @GetMapping("factory/menus")
//    public RespBean menusInFactory(@RequestParam("userId") String userId){
//        return RespBean.data(menuService.getFactoryMenus(userId));
//    }

//    @GetMapping("list")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "code", value = "菜单编号", paramType = "query", dataType = "string"),
//            @ApiImplicitParam(name = "name", value = "菜单名称", paramType = "query", dataType = "string")
//    })
//    @ApiOperation(value = "获取菜单列表", notes = "已完成-管理员访问", tags = "平台级资源")
//    public RespBean menuList(@RequestParam String code, @RequestParam String name){
//        List<Menu> menuList = menuService.menuList(code, name);
//        if (Func.hasEmpty(code) && Func.hasEmpty(name)){
//            return RespBean.data(MenuNodeMerger.merge(menuList));
//        }
//        return RespBean.data(menuList);
//    }

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperation(value = "详情", notes = "传入menu")
    public R<MenuVO> detail(Menu menu) {
        Menu detail = menuService.getOne(Condition.getQueryWrapper(menu));
        MenuWrapper menuWrapper = new MenuWrapper(menuService, dictService);
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
                .eq(Menu::getIsDeleted, 0)
                .orderByAsc(Menu::getSort));
        MenuWrapper menuWrapper = new MenuWrapper(menuService, dictService);
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
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        Collection<Menu> menus = menuService.listByIds(Func.toIntList(ids));
        menus.forEach(menu -> menu.setIsDeleted(1));
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
    @ApiOperation(value = "权限分配树形结构", notes = "unfinished")
    public R<List<MenuVO>> grantTree(User user) {
        return R.data(menuService.grantTree(user));
    }

    /**
     * 获取权限分配树形结构
     */
    @GetMapping("/role-tree-keys")
    @ApiOperation(value = "角色所分配的树", notes = "unfinished", position = 7)
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
