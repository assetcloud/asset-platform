package com.asset.controller.admin;


import com.asset.entity.AsFormInst;
import com.asset.javabean.AdminTaskVO;
import com.asset.javabean.RespBean;
import com.asset.service.impl.TaskServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * <p>
 *  控制台展示任务节点信息
 * </p>
 *
 * @author YBY
 * @since 2019-08-04
 */
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminTaskController {

    TaskServiceImpl asFormInstService;

    /**
     * 任务节点信息
     * @return
     */
    @GetMapping(value = "/task/list")
    public RespBean showTasks(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        QueryWrapper<AsFormInst> queryWrapper = Condition.getQueryWrapper(role, AsFormInst.class);
        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminTaskVO> list = new PageInfo<>(asFormInstService.listAdminProcTaskInfo(queryWrapper));
        return RespBean.ok("",list);
    }



}

