package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Staff;
import com.asset.bean.User;
import com.asset.mapper.UuidIdGenerator;
import com.asset.service.StaffService;
import com.asset.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hjhu on 2019/5/27.
 */

@RestController
public class UserNormalController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserNormalController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    /**
     * 用户登录
     * @return RespBean
     */
    @RequestMapping("/login_p")
    public RespBean login() {
        return RespBean.error("尚未登录，请登录!");
    }

    /**
     * 用户注册
     * @param user
     * @return RespBean
     */
    @RequestMapping(value = "/userReg", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册操作",tags = "用户管理", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "用户密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "注册成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean userReg(@RequestBody User user){
        LOGGER.info("用户注册：{}", user.toString());
        int flag = userService.insertUser(user);
        if(flag == -1){
            return RespBean.error("用户已存在");
        }
        if(flag < 0){
            return RespBean.error("注册失败");
        }else{
            return RespBean.ok("注册成功");
        }
    }

    @RequestMapping(value = "/userAudit/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "用户审核", notes = "对注册后用户进行审核", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "用户审核成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    public RespBean userAudit(@PathVariable("userId") String userId){
        Staff staff = new Staff();
        User user = userService.getUserById(userId);
        staff.setStaffName(user.getRealName());
        staff.setGender(user.getGender());
        staff.setUserId(user.getId());
        staff.setContactPhoneNumber(user.getPhoneNumber());
        staff.setStaffBirthday(user.getUserBirthday());
        staff.setCertificateType(String.valueOf(user.getCertificateType()));
        staff.setCertificateNumber(user.getCertificateNumber());
        staff.setStatus("1");
        staff.setRemoveTag(0);
        staff.setUserCreatedTag("1");
        staff.setAssetStatus("1");
        staff.setFamilyAddress(user.getUserAddress());
        Map<String, Object> map = staffService.addStaff(staff);
        int flag = Integer.parseInt(String.valueOf(map.get("flag")));
        if(flag < 0){
            return RespBean.error("系统错误");
        }
        user.setStaffId(String.valueOf(map.get("staffId")));
        flag = userService.updateUserById(user);
        if (flag < 0){
            return RespBean.error("系统错误");
        }
        return RespBean.ok("用户审核成功");
    }

    @ApiOperation(value = "获取用户", notes = "根据角色获取用户", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/users/{roleId}", method = RequestMethod.GET)
    public List<User> userAudit(@PathVariable("roleId") Long roleId){
        return userService.getUsersByRole(roleId);
    }
}
