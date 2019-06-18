package com.asset.controller.system;

import com.asset.bean.*;
import com.asset.service.MenuService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    final static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/app/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加菜单", notes = "添加完应用后调用该接口",tags = "菜单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationId", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicationName", value = "应用名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean addMenu(@RequestBody Application application){
        LOGGER.info("{}",application.toString());
        Menu menu = new Menu();
        int flag = menuService.addAppMenu(menu, application);
        if (flag < 0){
            RespBean.error("添加失败", flag);
        }
        return RespBean.ok("添加成功", flag);
    }

    @ApiOperation(value = "添加菜单（表单类型）", notes = "保存表单时调用该接口",tags = "菜单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationId", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formModelId", value = "表单id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formName", value = "应用图标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/form/add", method = RequestMethod.POST)
    public RespBean addMenu(@RequestBody FormModelInfo formModelInfo){
        Menu menu = menuService.getMenuByPath(formModelInfo.getApplicationId());
        if(menu == null){
            return RespBean.error("数据错误","");
        }
        int flag = menuService.addFormMenu(menu, formModelInfo);
        if(flag < 0){
            return RespBean.error("添加失败", flag);
        }
        return RespBean.ok("添加成功");
    }

    @ApiOperation(value = "获取菜单", notes = "通过当前用户角色获取菜单",tags = "菜单", httpMethod = "GET")
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public List<Menu> getMenusByRole(){
        return menuService.getMenusByCurrentUser();
    }

    @ApiOperation(value = "获取菜单", notes = "通过用户id获取菜单",tags = "菜单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/menus/{id}", method = RequestMethod.GET)
    public List<Menu> getMenusByRoleId(@PathVariable String id){
        return menuService.getMenusByUserId(id);
    }

    @ApiOperation(value = "获取应用菜单", notes = "通过当前用户角色获取应用资源，主要用于首页内容展现",tags = "菜单", httpMethod = "GET")
    @RequestMapping(value = "/app/menus", method = RequestMethod.GET)
    public List<Menu> getAppMenusByRole(){
        return menuService.getAppMenusByRole();
    }

    @ApiOperation(value = "获取表单菜单", notes = "通过点击应用，展现可访问的表单资源",tags = "菜单", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/form/menus/{appId}", method = RequestMethod.GET)
    public List<Menu> getFormMenusByApp(@PathVariable String appId){
        return menuService.getFormMenusByApp(appId);
    }
}
