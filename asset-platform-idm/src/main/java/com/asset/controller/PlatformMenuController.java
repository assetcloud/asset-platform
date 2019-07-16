package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.service.PlatformMenuService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 平台级菜单控制器
 * @author hjhu
 */
@RequestMapping(value = "plat")
@RestController
public class PlatformMenuController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformMenuController.class);

    @Autowired
    PlatformMenuService platformMenuService;

    @ApiOperation(value = "获取平台资源", notes = "获取平台资源，无需传入参数", tags = "平台资源", httpMethod = "GET")
    @GetMapping("menus")
    public RespBean getPlatMenus(){
        return RespBean.ok(SystemConstant.GET_SUCCESS, platformMenuService.getPlatMenus());
    }

    /**
     * 获取应用工场下的菜单资源
     * @return
     */
    @ApiOperation(value = "应用工厂级资源", notes = "获取应用工厂级资源，无需获取权限", tags = "平台级资源", httpMethod = "GET")
    @GetMapping("factory/menus")
    public RespBean menusInFactory(){
        return RespBean.ok("", platformMenuService.getFactoryMenus());
    }
}
