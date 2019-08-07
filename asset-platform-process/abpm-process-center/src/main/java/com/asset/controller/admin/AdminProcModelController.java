package com.asset.controller.admin;


import com.asset.entity.ActDeModel;
import com.asset.entity.AsFormModel;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.AdminProcModelVO;
import com.asset.javabean.RespBean;
import com.asset.service.impl.ActDeModelServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(tags = "控制端流程模型管理")
public class AdminProcModelController {

    ActDeModelServiceImpl actDeModelService;

    /**
     * 获取表单模型信息
     * @param role
     * @param query
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10",required = true , paramType = "query", dataType = "integer")
    })
    @GetMapping("/proc_model/list")
    public RespBean listProcModel(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        QueryWrapper<ActDeModel> queryWrapper = Condition.getQueryWrapper(role, ActDeModel.class);
        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminProcModelVO> list = new PageInfo<>(actDeModelService.listAdminProcModelInfo(queryWrapper));
        return RespBean.ok("",list);
    }
}

