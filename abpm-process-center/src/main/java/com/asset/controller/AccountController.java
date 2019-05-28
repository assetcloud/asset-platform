package com.asset.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @author yby
 * @time 190527 1423
 * @version 1.0_190527 1423
 */
@RestController
public class AccountController {

    @RequestMapping(value = "/app/authentication", method = RequestMethod.GET)
    public boolean login(){
        return true;
    }

    /**
     * postman测试成功
     * @return
     */
    @RequestMapping(value = {"/flowable-idm/app/rest/account"})
    public String accountIdm() {
        return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
                + "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
                + ":["
                + "\"access-idm\","
                + "\"access-task\","
                + "\"access-modeler\","
                + "\"access-admin\""
                + "]}\n";
    }


    /**
     * postman测试成功
     * @return
     */
    @RequestMapping(value = {"/flowable-idm/app/logout"},method = RequestMethod.GET)
    public String accountLogout() {
        return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
                + "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
                + ":["
                + "\"access-idm\","
                + "\"access-task\","
                + "\"access-modeler\","
                + "\"access-admin\""
                + "]}\n";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
