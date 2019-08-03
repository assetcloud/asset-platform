package com.asset.controller;

import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.AdminProcTaskVO;
import com.asset.javabean.RespBean;
import com.asset.service.AdminFormModelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制端表单模型
 * @author YBY
 */
@RestController
@RequestMapping("/admin")
public class AdminFormModelController {

    @Autowired
    AdminFormModelService adminFormModelService;

    @GetMapping("/form_model/list")
    public RespBean getFormModelList(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<AdminFormModelVO> list = new PageInfo<>(adminFormModelService.getFormModels());
        return RespBean.ok("",list);
    }

}
