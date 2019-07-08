package com.asset.controller;

import com.asset.bean.PlatMenu;
import com.asset.bean.RespBean;
import com.asset.bean.User;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.PlatformMenuService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 平台级菜单控制器
 * @author hjhu
 */
@RequestMapping(value = "/plat/menu")
@RestController
public class PlatformMenuController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformMenuController.class);

    @Autowired
    PlatformMenuService platformMenuService;

    @ApiOperation(value = "获取平台资源", notes = "获取平台资源", tags = "平台资源", httpMethod = "GET")
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public RespBean getPlatMenus(){
        PlatMenu rootNode = platformMenuService.getPlatMenus();
        return RespBean.ok(SystemConstant.GET_SUCCESS, rootNode);
    }
}
