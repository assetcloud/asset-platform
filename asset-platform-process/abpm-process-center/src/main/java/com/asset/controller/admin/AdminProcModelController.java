package com.asset.controller.admin;


import com.asset.entity.ActDeModelDO;
import com.asset.javabean.AdminProcModelVO;
import com.asset.service.impl.ActDeModelService;
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
 *  控制台展示流程模型信息
 * </p>
 *
 * @author YBY
 * @since 2019-08-04
 */
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Api(tags = "控制端：流程模型管理")
public class AdminProcModelController {

    ActDeModelService actDeModelService;

    /**
     * 获取表单模型信息
     * @param role
     * @param query
     * @return
     */
    @ApiOperation(value = "获取表单模型信息",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10",required = true , paramType = "query", dataType = "integer")
    })
    @GetMapping("/proc_model/list")
    public R<PageInfo<AdminProcModelVO>> listProcModel(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<ActDeModelDO> queryWrapper = Condition.getQueryWrapper(role, ActDeModelDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminProcModelVO> list = new PageInfo<>(actDeModelService.listAdminProcModelInfo(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }
}

