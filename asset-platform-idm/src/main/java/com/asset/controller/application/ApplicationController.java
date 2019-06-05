package com.asset.controller.application;

import com.asset.bean.Application;
import com.asset.bean.Resource;
import com.asset.bean.RespBean;
import com.asset.bean.User;
import com.asset.common.UserUtils;
import com.asset.service.ApplicationService;
import com.asset.service.ResourceService;
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

    @RequestMapping(value = "/addApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RespBean addApp(@RequestBody Application application){
        LOGGER.info(application.toString());
        application.setCreatedTime(new Date());
        application.setStatus(1);
        application.setIsPublished(0);
        int flag = applicationService.addApplication(application);
        if (flag < 0){
            LOGGER.info("应用新建失败");
            return RespBean.error("新建失败");
        } else {
            LOGGER.info("应用新建成功");
            return RespBean.ok("新建成功");
        }
    }

    @RequestMapping(value = "/updateApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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

    @RequestMapping(value = "/deleteApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
    public List<Application> getAppList(){
        return applicationService.getAppList();
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public RespBean appPublish(@RequestBody Application application){
        Application app = applicationService.getById(application.getId());
        app.setIsPublished(1);
        int flag = applicationService.updateApplication(app);
        if(flag < 0) {
            return RespBean.error("发布失败");
        } else {
            return RespBean.ok("发布成功");
        }
    }
}
