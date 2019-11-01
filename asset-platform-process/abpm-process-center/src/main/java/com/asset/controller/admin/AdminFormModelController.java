package com.asset.controller.admin;

import com.asset.entity.AsFormModelDO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.service.AdminFormModelService;
import com.asset.service.impl.AsFormModelService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 控制端表单模型
 *
 * @author YBY
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "控制端：表单模型管理")
public class AdminFormModelController {

    @Autowired
    AdminFormModelService adminFormModelService;
    @Autowired
    AsFormModelService asFormModelService;

    /**
     * 获取表单模型信息
     *
     * @param role
     * @param query
     * @return
     */
    @ApiOperation(value = "获取表单模型信息", httpMethod = "GET")
    @GetMapping("/form_model/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formName", value = "表单名称", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10", required = true, paramType = "query", dataType = "integer")
    })
    public R getFormModelListPlus(@ApiIgnore @RequestParam Map<String, Object> role,
                                  Query query) {
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<AsFormModelDO> queryWrapper = Condition.getQueryWrapper(role, AsFormModelDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminFormModelVO> list = new PageInfo<>(asFormModelService.listAdminFormModelInfo(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }

}
