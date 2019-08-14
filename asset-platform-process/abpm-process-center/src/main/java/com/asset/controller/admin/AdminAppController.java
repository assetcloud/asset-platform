package com.asset.controller.admin;

import com.asset.entity.ActDeModel;
import com.asset.entity.AsApplication;
import com.asset.javabean.AdminAppInfoVO;
import com.asset.javabean.AdminFormModelVO;
import com.asset.javabean.AdminProcModelVO;
import com.asset.javabean.RespBean;
import com.asset.service.AdminAppService;
import com.asset.service.impl.AsApplicationServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    AsApplicationServiceImpl asApplicationService;

    /**
     * 获取应用下表单资源信息
     * @return
     */
    @GetMapping("/app_info")
    public RespBean getAppInfo(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        QueryWrapper<AsApplication> queryWrapper = Condition.getQueryWrapper(role, AsApplication.class);
        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminAppInfoVO> list = new PageInfo<>(asApplicationService.getAppInfos(queryWrapper));
        return RespBean.ok("",list);
    }



}
