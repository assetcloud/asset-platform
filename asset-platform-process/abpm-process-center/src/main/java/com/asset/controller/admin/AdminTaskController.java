package com.asset.controller.admin;


import com.asset.entity.AsFormInstDO;
import com.asset.javabean.AdminTaskVO;
import com.asset.service.impl.TaskServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.asset.utils.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "控制端：任务节点管理")
public class AdminTaskController {

    TaskServiceImpl asFormInstService;

    /**
     * 任务节点信息
     * @return
     */
    @ApiOperation(value = "获取任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formModelId", value = "表单模型id", required = false , paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10",required = true , paramType = "query", dataType = "integer")
    })
    @GetMapping(value = "/task/list")
    public R<PageInfo<AdminTaskVO>> showTasks(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<AsFormInstDO> queryWrapper = Condition.getQueryWrapper(role, AsFormInstDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminTaskVO> list = new PageInfo<>(asFormInstService.listAdminProcTaskInfo(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }





}

