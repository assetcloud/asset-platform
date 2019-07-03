package com.asset.controller.application;

import com.asset.bean.*;
import com.asset.common.UserUtils;
import com.asset.service.ApplicationService;
import com.asset.service.MenuService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 应用管理控制器
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {

    final private static Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private MenuService menuService;

    /*@RequestMapping(value = "/addApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加应用", notes = "应用添加",tags = "应用", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationName", value = "应用名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "新建成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean addApp(@RequestBody Application application){
        LOGGER.info(application.toString());
        int flag = applicationService.addApplication(application);
        if (flag < 0){
            LOGGER.info("应用新建失败");
            return RespBean.error("新建失败");
        }
        Menu menu = new Menu();
        flag = menuService.addAppMenu(menu, application);
        if (flag < 0){
            LOGGER.info("菜单添加失败");
            return RespBean.error("新建失败","");
        }
        return RespBean.ok("新建成功","");
    }*/
    @RequestMapping(value = "/addApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "添加应用", notes = "应用添加",tags = "应用", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationName", value = "应用名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "新建成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean addApp(@RequestBody Application application){
        int flag = applicationService.addApplication(application);
        if (flag < 0){
            LOGGER.info("应用新建失败");
            return RespBean.error("新建失败");
        }
        return RespBean.ok("新建成功","");
    }

    @RequestMapping(value = "/updateApp", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "修改应用", notes = "应用修改",tags = "应用", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean updateApp(@RequestBody Application application){
        LOGGER.info(application.toString());
        Application oldApp = applicationService.getById(application.getId());
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
    @ApiOperation(value = "删除应用", notes = "应用删除",tags = "应用", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean deleteApp(@RequestBody Application application){
        Application oldApp = applicationService.getById(application.getId());
        if(null == oldApp){
            return RespBean.error("数据错误");
        }
        oldApp.setStatus(0);
        int flag = applicationService.updateApplication(oldApp);
        if(flag < 0) {
            return RespBean.error("删除失败");
        } else {
            return RespBean.ok("删除成功");
        }
    }

    @RequestMapping(value = "/appList", method = RequestMethod.GET)
    @ApiOperation(value = "获取应用列表", tags = "应用", httpMethod = "GET")
    @ApiResponse(code = 200, message = "", response = java.util.List.class)
    public List<Application> getAppList(){
        return applicationService.getAppList();
    }



    @RequestMapping(value = "/publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "发布应用", tags = "应用", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200,message = "发布成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean appPublish(@RequestBody AppTemplate appTemplate){
        LOGGER.info(appTemplate.toString());
        Application app = applicationService.getById(appTemplate.getApplicationId());
        app.setIsPublished(1);
        int flag = applicationService.updateApplication(app);
        if(flag < 0) {
            return RespBean.error("发布失败");
        } else {
            appTemplate.setPublishTime(new Date());
            appTemplate.setStatus(true);
            appTemplate.setPublishAccount(UserUtils.getCurrentUser().getRealName());
            flag = applicationService.appPublish(appTemplate);
            if(flag < 0) {
                return RespBean.error("发布失败");
            }
            return RespBean.ok("发布成功");
        }
    }
}
