package com.asset.controller;

import com.asset.base.BaseController;
import com.asset.utils.Constants;
import com.asset.utils.Parametermap;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lichao
 */
@Controller
@ApiIgnore
public class IndexController extends BaseController {

    @Autowired
    IdentityService identityService;

    @GetMapping("/")
    public String loginIndex() {
        Object obj = this.getSession().getAttribute(Constants.SESSION_USER);
        if (obj != null) {
            return "redirect:index";
        }
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login() {
        Parametermap parametermap = getParametermap();
        Object username = parametermap.get("username");
        Object password = parametermap.get("password");
        System.out.println(username + "##################" + password);
        Map<String, String> map = new HashMap<>();
        User user = identityService.createUserQuery().userId(username.toString()).singleResult();
        if (user != null) {
            if (password != null && password.equals(user.getPassword())) {
                map.put("status", "success");
                getSession().setAttribute(Constants.SESSION_USER, user);
            } else {
                map.put("status", "fail");
            }
        }else {
            map.put("status", "fail");
        }
        map.put("status", "success");
        return map;
    }

    @RequestMapping(value="/logout")
    public String logout() {
        getSession().removeAttribute(Constants.SESSION_USER);
        return "redirect:/";
    }
}
