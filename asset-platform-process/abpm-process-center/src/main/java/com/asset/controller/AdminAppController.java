package com.asset.controller;

import com.asset.javabean.AdminAppInfoVO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.RespBean;
import com.asset.service.AdminAppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制台管理应用、分组、表单模型以及对应的流程模型资源
 * @author YBY
 */
@RestController
@RequestMapping("/admin")
public class AdminAppController {

    @Autowired
    AdminAppService adminAppService;

    @GetMapping("/app_info")
    public RespBean getAppInfo(@RequestParam(defaultValue = "1") int pageNo,
                               @RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<AdminAppInfoVO> list = new PageInfo<>(adminAppService.getAppInfos());
        return RespBean.ok("",list);
    }
}
