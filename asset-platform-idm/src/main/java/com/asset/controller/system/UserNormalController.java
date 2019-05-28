package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.User;
import com.asset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by hjhu on 2019/5/27.
 */

@RestController
public class UserNormalController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserNormalController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @return RespBean
     */
    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

    /**
     * 用户注册
     * @param accountName
     * @param pwd
     * @param realName
     * @param admin
     * @param status
     * @return
     */
    @RequestMapping(value = "/userReg", method = RequestMethod.POST)
    public RespBean userReg(@RequestParam("accountName") String accountName,
                            @RequestParam("pwd") String pwd,
                            @RequestParam("realName") String realName,
                            @RequestParam("admin") int admin,
                            @RequestParam("status") int status){
        LOGGER.info("用户注册：{}-{}-{}-{}", accountName, pwd, realName, admin, status);
        User user = new User();
        user.setAccountName(accountName);
        user.setPwd(pwd);
        user.setRealName(realName);
        user.setAdmin(admin);
        if (status == 1){
            user.setStatus(true);
        } else {
            user.setStatus(false);
        }
        user.setCreatedTime(new Date());
        user.setUserAddress("浙江省杭州市江干区");
        int flag = userService.insertUser(user);
        if(flag < 0){
            return RespBean.error("用户添加失败");
        }else{
            return RespBean.ok("用户添加成功");
        }
    }
}
