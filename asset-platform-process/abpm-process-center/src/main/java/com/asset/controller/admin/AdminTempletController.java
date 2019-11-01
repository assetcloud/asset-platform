package com.asset.controller.admin;

import com.asset.entity.AsTempletDO;
import com.asset.javabean.AdminTempletVO;
import com.asset.service.impl.AsTempletFormModelServiceImpl;
import com.asset.service.impl.AsTempletServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.asset.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 负责对模板资源进行管理
 */
@Api(value = "控制端：对模板资源进行管理")
@RestController
@RequestMapping(value = "/admin/templet")
public class AdminTempletController {

    @Autowired
    AsTempletServiceImpl templetService;
    @Autowired
    AsTempletFormModelServiceImpl templetFormModelService;

    @ApiOperation("获取模板信息")
    @GetMapping(value = "/list")
    public R showTempletInfo(@ApiIgnore @RequestParam Map<String, Object> role, Query query)
    {
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<AsTempletDO> queryWrapper = Condition.getQueryWrapper(role, AsTempletDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminTempletVO> list = new PageInfo<>(templetService.selectAdminList(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }

    @ApiOperation(value = "获取模板信息",notes = "点击表单模型资源Id可以显示相应的表单样式")
    @GetMapping(value = "/form_sheet")
    public R showTempletFormModelSheet(@RequestParam("templet_form_model_id")
                                                   String templetFormModelId)
    {
        String modelSheet = templetFormModelService.selectTempletResource(templetFormModelId).getModelSheet();
        return R.data(modelSheet);
    }
}
