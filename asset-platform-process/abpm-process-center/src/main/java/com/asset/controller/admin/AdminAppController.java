package com.asset.controller.admin;

import com.asset.entity.AsApplicationDO;
import com.asset.javabean.AdminAppInfoVO;
import com.asset.service.impl.AsApplicationService;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.asset.utils.R;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 控制台管理应用、分组、表单模型以及对应的流程模型资源
 * @author YBY
 */
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Api(tags = "控制端：应用管理")
public class AdminAppController {

    AsApplicationService asApplicationService;

    /**
     * 获取应用下表单资源信息
     * @return
     */
    @ApiOperation(value = "获取应用下表单资源信息",httpMethod = "GET")
    @GetMapping("/app_info")
    public R<PageInfo<AdminAppInfoVO>> getAppInfo(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<AsApplicationDO> queryWrapper = Condition.getQueryWrapper(role, AsApplicationDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminAppInfoVO> list = new PageInfo<>(asApplicationService.getAppInfos(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }



}
