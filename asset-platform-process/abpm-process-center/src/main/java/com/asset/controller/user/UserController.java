package com.asset.controller.user;

import com.asset.base.BaseController;
import com.asset.dto.SceneSelectDTO;
import com.asset.entity.User;
import com.asset.exception.ProcException;
import com.asset.dto.RegisterDTO;
import com.asset.service.FormInstService;
import com.asset.service.ProcInstService;
import com.asset.service.UserService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import com.asset.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @author hjhu
 */
@RestController
@Api(tags = "终端：用户角色管理（包含注册审批、工作场景选择流程发起）")
public class UserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ProcInstService procInstService;
    @Autowired
    UserService userService;
    @Autowired
    FormInstService formInstService;

    /**
     * 用户管理页面
     * @return
     */
    @ApiIgnore
    @RequestMapping("/userList")
    public String procDefIndex() {
        return "pages/identity/user_list";
    }

    /**
     * 查询用户数据
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    @ApiIgnore
    @RequestMapping("/queryUsers")
    @ResponseBody
    public R<PageGrids> queryUsers(@RequestParam("page") Integer pageNum,
                        @RequestParam("rows") Integer pageSize,
                        @RequestParam String id,
                        @RequestParam String displayName) {

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = userService.getUsers(pageNum, pageSize, id, displayName);
        return R.data(pageGrids);
    }

    /**
     * 添加用户
     *
     * @param displayName
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public String addUser(@RequestParam String displayName,
                          @RequestParam String phoneNumber,
                          @RequestParam String email) {
        User user = new User();
        user.setId("2");
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setAdmin(true);
        user.setStatus(true);
        user.setPwd("123456");
        user.setPhoneNumber(phoneNumber);
        user.setCreatedTime(new Date());
        int flag = userService.addUser(user);
        if (flag > 0) {
            return "pages/identity/user_list";
        } else {
            return "index";
        }
    }

    /**
     * 前端发起注册，生成一个固定表单样式，固定的表单样式以及对应的审批流程模型应该在数据库创建时就存在数据库表中
     * 下面内容在SpringBoot启动一开始就会创建的内容：
     * 1、固定的表单样式(as_form_model)只需要这个，同时把这个表单模型绑定的流程模型ID设为register即可
     * 2、对应的审批流程模型(act_de_model)、这个流程模型的节点信息、每个流程节点对应的所有表单项的权限都不需要在数据库中创建，我们在读取的时候会对这个注册审批流程进行判断，如果是这个，就会自动
     * @param dto
     * @return
     */
    @ApiOperation(value = "发起注册审批流程" , notes = "发起注册审批流程" , httpMethod = "POST")
    @RequestMapping(value = "/form_inst/register/save", method = RequestMethod.POST)
    public R<String[]> register(@RequestBody RegisterDTO dto) {
        String[] urls = null;
        try {
            urls = procInstService.createRegisterProcByXml(dto);
        } catch (Exception procException) {
            LOGGER.error(procException.getMessage());
            return R.fail("创建注册流程出错！");
        }

        return R.data(urls);
    }

    @ApiOperation(value = "发起工作场景选择流程" , notes = "发起工作场景选择审批流程" , httpMethod = "POST")
    @PostMapping(value = "/form_inst/select_scene")
    public R<String[]> selectScene(@RequestBody SceneSelectDTO dto){
        String[] urls = null;
        try {
            urls = procInstService.createSceneSelectProcByXml(dto);
        } catch (Exception procException) {
            LOGGER.error(procException.getMessage());
            return R.fail("创建注册流程出错！");
        }

        return R.data(urls);
    }

}
