package com.asset.controller;

import com.asset.base.BaseController;
import com.asset.entity.User;
import com.asset.service.UserService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author hjhu
 */
@Controller
public class UserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 用户管理页面
     * @return
     */
    @RequestMapping("/userList")
    public String procDefIndex() {
        return "pages/identity/user_list";
    }

    /**
     * 查询用户数据
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    @RequestMapping("/queryUsers")
    @ResponseBody
    public PageGrids queryUsers(@RequestParam("page") Integer pageNum,
                                @RequestParam("rows") Integer pageSize,
                                @RequestParam String id,
                                @RequestParam String displayName) {

        if(pageNum==null ||pageNum<=0){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = userService.getUsers(pageNum, pageSize, id, displayName);
        return pageGrids;
    }

    /**
     * 添加用户
     * @param displayName
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
//    public String addUser(@RequestParam String displayName,
//                             @RequestParam String phoneNumber,
//                             @RequestParam String email) {
    public String addUser(@RequestParam String displayName,
                          @RequestParam String phoneNumber,
                          @RequestParam String email) {
        User user = new User();
        user.setId("2");
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setAdmin(true);
        user.setStatus(true);
        user.setPwd("123456");
        user.setPhoneNumber(phoneNumber);
        user.setCreatedTime(new Date());
        int flag = userService.addUser(user);
        if (flag > 0){
            return "pages/identity/user_list";
        }else {
            return "index";
        }
    }
}
