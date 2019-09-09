package com.asset.controller.user;


import com.asset.entity.ApplicationDO;
import com.asset.service.ApplicationService;
import com.asset.utils.Constants;
import com.asset.utils.R;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                @ApiResponse(code = 200,message = "新建成功",response = R.class),
                @ApiResponse(code = 500,message = "系统错误",response = R.class)
        })
        public R addApp(@RequestBody ApplicationDO application){
            try {
                applicationService.addApplication(application);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("应用新建失败");
                return R.fail("新建失败");
            }
        return R.success("新建成功");
    }

    @RequestMapping(value = "/updateApp", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "修改应用", notes = "应用修改", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功",response = R.class),
            @ApiResponse(code = 500,message = "系统错误",response = R.class)
    })
    public R updateApp(@RequestBody ApplicationDO application){
        LOGGER.info(application.toString());
        ApplicationDO oldApp = applicationService.getById(application.getId());
        if(null == oldApp){
            return R.fail("数据错误");
        }
        application.setStatus(oldApp.getStatus());
        application.setCreatedTime(oldApp.getCreatedTime());
        application.setIsPublished(oldApp.getIsPublished());
        int flag = applicationService.updateApplication(application);
        if(flag < 0) {
            return R.fail("修改失败");
        } else {
            return R.success("修改成功");
        }
    }

    @RequestMapping(value = "/deleteApp", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "删除应用", notes = "应用删除", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功",response = R.class),
            @ApiResponse(code = 500,message = "系统错误",response = R.class)
    })
    public R deleteApp(@RequestParam(value = "app_id") String appId){
        ApplicationDO oldApp = applicationService.getById(appId);
        if(null == oldApp){
            return R.fail("数据错误");
        }
        //删除某个应用需要先保证该应用下表单都已经失效
        if (applicationService.checkFormContain(appId))
            return R.fail("无法删除，该应用下还有表单模型存在！");

        oldApp.setStatus(0);
        oldApp.setRemoveTime(new Date());
        int flag = applicationService.updateApplication(oldApp);
        if(flag < 0) {
            return R.fail("删除失败");
        } else {
            return R.success("删除成功");
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
    public R publishApp(@RequestBody ApplicationDO rec)
    {
        //这里本质就是在数据库中修改一下是否publish这一项
        rec.setIsPublished(Constants.APP_PUBLISHED);
        int i = applicationService.updateApplication(rec);
        if(i == Constants.DATABASE_SUCCESS)
            return R.success("发布成功");
        else
            return R.fail("发布失败！");
    }

    /**
     * 从数据库中找到已发布的应用模板列表
     * @return
     */
    @RequestMapping(value = "/model/show",method = RequestMethod.GET)
    public R getPublishedApp(){
        List<ApplicationDO> list = applicationService.getPublishedApp();
        if(list == null)
        {
            return R.success("没有发布的应用模板！");
        }
        return R.data(list);
    }


}
