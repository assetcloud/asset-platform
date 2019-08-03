package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Scene;
import com.asset.bean.User;
import com.asset.bean.UserRole;
import com.asset.common.SystemConstant;
import com.asset.common.model.UserPageParam;
import com.asset.service.*;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 *
 * 系统用户控制器
 */
@RestController
@AllArgsConstructor
@RequestMapping("sys/user")
@Api(value = "系统用户管理", tags = "系统用户管理")
public class SystemUserController {

    ISceneService sceneService;

    IUserRoleService userRoleService;

    IUserService userService;

    @ApiOperation(value = "获取不在某一场景下的用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("users/noScene")
    public RespBean getUsersWithoutScene(@RequestParam String accountName, @RequestParam String realName
            , @RequestParam String email, @RequestParam String sceneId){
        if (Func.hasEmpty(sceneId)){
            return RespBean.paramError();
        }
        accountName = accountName == null ? "" : accountName;
        realName = realName == null ? "" : realName;
        email = email == null ? "" : email;
        return RespBean.data(userService.getUsersWithoutScene(accountName, realName, email, sceneId));
    }

    @ApiOperation(value = "获取所有用户（兼模糊搜索）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页数据量", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "userPageParam", value = "UserPageParam", required = true, dataTypeClass = UserPageParam.class)
    })
    @PostMapping("list")
    public RespBean userList(@RequestParam Integer page, @RequestParam Integer size, @RequestBody UserPageParam userPageParam){
        PageHelper.startPage(page, size);
        return RespBean.data(new PageInfo<>(userService.allUsers(userPageParam)));
    }

    @ApiOperation(value = "管理控制台，添加用户"
            , notes = "（已完成）必填项：accountName用户名;realName真实姓名;pwd密码;phoneNumber手机号;userEmail用户邮箱")
    @PostMapping("add")
    public RespBean addUser(@RequestBody User user){
        if (Func.hasEmpty(user.getAccountName(), user.getRealName(), user.getPwd(), user.getPhoneNumber()
                , user.getUserEmail(), user.getStatus(), user.getAdmin())){
            return RespBean.paramError();
        }
        return RespBean.status(userService.saveUser(user));
    }

    @ApiOperation(value = "控制台中删除用户", notes = "（已完成，不对外开放）")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PostMapping("delete")
    public RespBean removeUser(@RequestParam String userId){
        if (Func.hasEmpty(userId)){
            return RespBean.paramError();
        }
        return RespBean.status(userService.removeUser(userId));
    }

    @ApiOperation(value = "控制台中，获取单个用户信息", notes = "（已完成，不对外开放）")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @GetMapping("detail")
    public RespBean getUser(@RequestParam String userId){
        return RespBean.data(userService.getById(userId));
    }

    @ApiOperation(value = "控制台编辑用户信息", notes = "（已完成，不对外开放）accountName")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PutMapping("edit")
    public RespBean editUser(@RequestBody User user){
        if (Func.hasEmpty(user.getId())){
            return RespBean.paramError();
        }
        return RespBean.status(userService.updateById(user));
    }

    @ApiOperation(value = "注册用户激活", notes = "（已完成）sceneId场景ID;userId用户ID;组织管理员审核时sceneId置null或\"\"")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "sceneId", required = true, name = "场景id", dataTypeClass = String.class),
            @ApiImplicitParam(value = "userId", required = true, name = "用户id", dataTypeClass = String.class)
    })
    @PostMapping(value = "active")
    @Transactional
    public RespBean userActivate(@RequestParam String sceneId, @RequestParam String userId) {
        if (userService.getById(userId).getAdmin() == 1){
            return RespBean.error("注册为平台管理员的接口还没做");
        }
        userService.enableUser(userId);
        //设置平台级权限
        UserRole userRole = new UserRole();
        userRole.setCreatedTime(new Date());
        userRole.setUid(userId);
        userRole.setStatus(1);
        userRole.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
        userRoleService.save(userRole);
        sceneService.enableScene(userId, sceneId);
        return RespBean.ok("用户审核通过");
    }

    @ApiOperation(value = "重置用户密码", notes = "已完成，不对外开放")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PutMapping("pwd/reset")
    public RespBean passwordReset(String userId){
        return RespBean.status(userService.resetPassword(userId));
    }
}
