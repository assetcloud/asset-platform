package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.common.SystemConstant;
import com.asset.service.IMenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取平台级资源", notes = "userId用户id", tags = "平台级资源", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", required = true, dataTypeClass = java.lang.String.class)
    })
    @GetMapping("menus")
    public RespBean getPlatMenus(@RequestParam("userId") String userId){
        return RespBean.data(menuService.getMenus(userId));
    }

    @ApiOperation(value = "应用工厂级资源", notes = "userId用户id", tags = "平台级资源", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", required = true, dataTypeClass = java.lang.String.class)
    })
    @GetMapping("factory/menus")
    public RespBean menusInFactory(@RequestParam("userId") String userId){
        return RespBean.data(menuService.getFactoryMenus(userId));
    }
}
