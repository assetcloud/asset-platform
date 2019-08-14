package com.asset.controller.user;


import com.asset.entity.ApplicationDO;
import com.asset.javabean.RespBean;
import com.asset.service.ApplicationService;
import com.asset.utils.Constants;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * 应用管理控制器
 * @author YBY and HHJ
 * @time 190720
 * @version 1.0_190720
 */
@RestController
@RequestMapping("/application")
@Api(tags = "终端：应用管理")
public class ApplicationController {

    final private static Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;



    @RequestMapping(value = "/addApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加应用", notes = "应用添加", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200,message = "新建成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean addApp(@RequestBody ApplicationDO application){
        try {
            applicationService.addApplication(application);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("应用新建失败");
            return RespBean.error("新建失败");
        }
        return RespBean.ok("新建成功","");
    }

    @RequestMapping(value = "/updateApp", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "修改应用", notes = "应用修改", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean updateApp(@RequestBody ApplicationDO application){
        LOGGER.info(application.toString());
        ApplicationDO oldApp = applicationService.getById(application.getId());
        if(null == oldApp){
            return RespBean.error("数据错误");
        }
        application.setStatus(oldApp.getStatus());
        application.setCreatedTime(oldApp.getCreatedTime());
        application.setIsPublished(oldApp.getIsPublished());
        int flag = applicationService.updateApplication(application);
        if(flag < 0) {
            return RespBean.error("修改失败");
        } else {
            return RespBean.ok("修改成功");
        }
    }

    @RequestMapping(value = "/deleteApp", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除应用", notes = "应用删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean deleteApp(@RequestParam(value = "app_id") String appId){
        ApplicationDO oldApp = applicationService.getById(appId);
        if(null == oldApp){
            return RespBean.error("数据错误");
        }
        //删除某个应用需要先保证该应用下表单都已经失效
        if (applicationService.checkFormContain(appId))
            return RespBean.error("无法删除，该应用下还有表单模型存在！");

        oldApp.setStatus(0);
        oldApp.setRemoveTime(new Date());
        int flag = applicationService.updateApplication(oldApp);
        if(flag < 0) {
            return RespBean.error("删除失败");
        } else {
            return RespBean.ok("删除成功");
        }
    }

    @RequestMapping(value = "/appList", method = RequestMethod.GET)
    @ApiOperation(value = "获取应用列表",  httpMethod = "GET")
    @ApiResponse(code = 200, message = "", response = List.class)
    public List<ApplicationDO> getAppList(){
        return applicationService.getAppList();
    }



//

    /**
     * 发布应用
     * @param rec
     * @return
     */
    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public RespBean publishApp(@RequestBody ApplicationDO rec)
    {
        //这里本质就是在数据库中修改一下是否publish这一项
        rec.setIsPublished(Constants.APP_PUBLISHED);
        int i = applicationService.updateApplication(rec);
        if(i == Constants.DATABASE_SUCCESS)
            return RespBean.ok("");
        else
            return RespBean.error("发布失败！");
    }

    /**
     * 从数据库中找到已发布的应用模板列表
     * @return
     */
    @RequestMapping(value = "/model/show",method = RequestMethod.GET)
    public RespBean getPublishedApp(){
        List<ApplicationDO> list = applicationService.getPublishedApp();
        if(list == null)
        {
            return RespBean.ok("没有发布的应用模板！");
        }
        return RespBean.ok("",list);
    }


}
