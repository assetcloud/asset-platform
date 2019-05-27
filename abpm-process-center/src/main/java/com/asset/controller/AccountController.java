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
@RequestMapping(value = "flowable-idm")
public class AccountController {

    @RequestMapping(value = "/app/authentication", method = RequestMethod.GET)
    public boolean login(){
        return true;
    }

    @RequestMapping(value = "/app/rest/account")
    public String account() {
//        ModelAndView modelAndView = new ModelAndView();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("json","{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
//                + "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
//                + ":["
//                + "\"access-idm\","
//                + "\"access-task\","
//                + "\"access-modeler\","
//                + "\"access-admin\""
//                + "]}\n");
//        modelAndView.addAllObjects(map);
        return "{\"id\":\"admin\",\"firstName\":\"Test\",\"lastName\":\"Administrator\",\"email\":\"admin@flowable.org\","
                + "\"fullName\":\"Test Administrator\",\"groups\":[],\"privileges\""
                + ":["
                + "\"access-idm\","
                + "\"access-task\","
                + "\"access-modeler\","
                + "\"access-admin\""
                + "]}\n";
//        return modelAndView;
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
