package com.asset.controller;

import com.asset.bean.User;
import com.asset.common.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    /*@Autowired
    private ResourceService resourceService;

    @RequestMapping("/sysmenu")
    public List<Resource> sysmenu() {
        return resourceService.getResourcesByUserId();
    }

    @RequestMapping("/func_menu")
    public List<Resource> sysmenu1() {
        return resourceService.getResourcesByUserId();
    }*/

    @RequestMapping("/user")
    public User currentUser() {
        return UserUtils.getCurrentUser();
    }
}
