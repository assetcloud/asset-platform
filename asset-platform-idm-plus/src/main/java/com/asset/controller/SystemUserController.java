package com.asset.controller;

import com.asset.bean.Role;
import com.asset.bean.User;
import com.asset.bean.UserRole;
import com.asset.bean.UserScene;
import com.asset.common.SystemConstant;
import com.asset.service.*;
import com.asset.utils.Condition;
import com.asset.utils.Func;
import com.asset.vo.UserVO;
import com.asset.wrapper.RoleWrapper;
import com.asset.wrapper.UserWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 *
 * 系统用户控制器
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("sys/user")
@Api(value = "系统用户管理", tags = "系统用户管理")
public class SystemUserController {

    ISceneService sceneService;

    IUserRoleService userRoleService;

    IUserService userService;

    IUserSceneService userSceneService;

    IDictService dictService;

    IRoleService roleService;

    @ApiOperation(value = "获取不在某一场景下的用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("users/noScene")
    public R getUsersWithoutScene(@RequestParam String accountName, @RequestParam String realName
            , @RequestParam String email, @RequestParam String sceneId){
        if (Func.hasEmpty(sceneId)){
            return R.fail("参数错误");
        }
        accountName = accountName == null ? "" : accountName;
        realName = realName == null ? "" : realName;
        email = email == null ? "" : email;
        return R.data(userService.getUsersWithoutScene(accountName, realName, email, sceneId));
    }

    @ApiOperation(value = "获取所有用户（兼模糊搜索）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页数据量", defaultValue = "10", required = true, dataType = "int"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "phoneNumber", value = "真实姓名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "int")
    })
    @GetMapping("list")
    public R userList(@RequestParam Integer page, @RequestParam Integer size, @ApiIgnore @RequestParam Map<String, Object> user){
        Page pages = PageHelper.startPage(page, size);
        List<User> list = userService.list(Condition.getQueryWrapper(user, User.class).lambda().orderByAsc(User::getCreatedTime));
        UserWrapper userWrapper = new UserWrapper(userService, dictService, roleService);
        List<UserVO> userVOList = userWrapper.listNodeVO(list);
        PageInfo pageInfo = new PageInfo(userVOList);
        pageInfo.setTotal(pages.getTotal());
        return R.data(pageInfo);
    }

    @ApiOperation(value = "管理控制台，添加用户"
            , notes = "（已完成）必填项：accountName用户名;realName真实姓名;pwd密码;phoneNumber手机号;userEmail用户邮箱")
    @PostMapping("save")
    public R addUser(@RequestBody User user){
        if (Func.hasEmpty(user.getAccountName(), user.getRealName(), user.getPwd(), user.getPhoneNumber()
                , user.getUserEmail(), user.getStatus(), user.getRoleId())){
            return R.fail("参数错误");
        }
        return R.status(userService.saveUser(user));
    }

    @ApiOperation(value = "控制台中删除用户", notes = "（已完成，不对外开放）用户id集合")
    @ApiImplicitParam(value = "ids", required = true, dataTypeClass = String.class)
    @PostMapping("remove")
    public R removeUser(@RequestParam String ids){
        if (Func.hasEmpty(ids)){
            return R.fail("参数错误");
        }
        List<String> strings = Func.toStrList(",", ids);
        LinkedList<User> users = new LinkedList<>();
        strings.forEach(id -> {
            User user = new User();
            user.setId(id);
            user.setRemoveTime(new Date());
            user.setDisableTime(new Date());
            user.setStatus(0);
            users.add(user);
        });
        return R.status(userService.updateBatchById(users));
    }

    @ApiOperation(value = "控制台中，获取单个用户信息", notes = "（已完成，不对外开放）")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @GetMapping("detail")
    public R getUser(@RequestParam String userId){
        User user = userService.getById(userId);
        RoleWrapper roleWrapper = new RoleWrapper();
        user.setRoles(Collections.singletonList(roleService.getById(user.getRoleId())));
        UserWrapper userWrapper = new UserWrapper(userService, dictService, roleService);
        return R.data(userWrapper.entityVO(user));
    }

    @ApiOperation(value = "控制台编辑用户信息", notes = "（已完成）")
    @PostMapping("edit")
    public R editUser(@RequestBody User user){
        if (Func.hasEmpty(user.getId())){
            return R.fail("参数错误");
        }
        if (user.getRoleId() == 1){
            user.setAdmin(1);
        } else {
            user.setAdmin(0);
        }
        return R.status(userService.updateById(user));
    }

    @ApiOperation(value = "注册用户激活", notes = "（已完成）sceneId场景ID;userId用户ID;组织管理员审核时sceneId置null或\"\"")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "sceneId", required = true, name = "场景id", dataTypeClass = String.class),
            @ApiImplicitParam(value = "userId", required = true, name = "用户id", dataTypeClass = String.class)
    })
    @PostMapping(value = "active")
    @Transactional
    public R userActivate(@RequestParam String sceneId, @RequestParam String userId) {
        if (userService.getById(userId).getAdmin() == 1){
            return R.fail("注册为平台管理员的接口还没做");
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
        return R.success("用户审核通过");
    }

    @ApiOperation(value = "重置用户密码", notes = "已完成，不对外开放")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PostMapping("pwd/reset")
    public R passwordReset(String userId){
        return R.status(userService.resetPassword(userId));
    }

    @GetMapping("signify")
    @ApiOperation(value = "判断用户所属部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "userId", name = "用户id", required = true),
            @ApiImplicitParam(value = "sceneId", name = "场景id", required = true)
    })
    public R userSignify(@RequestParam String userId, @RequestParam String sceneId){
        UserScene record = userSceneService.getOne(Wrappers.<UserScene>lambdaQuery()
                .eq(UserScene::getUserId, userId).eq(UserScene::getSceneId, sceneId)
                .eq(UserScene::getStatus, 1));
        return R.data(record.getNodeId());
    }
}
