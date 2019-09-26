package org.springblade.system.user.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.model.Query;
import org.springblade.core.tool.api.R;
import org.springblade.system.user.common.Condition;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.service.IUserService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * 系统用户控制器
 *
 * @author hjhu
 */
@RestController
@AllArgsConstructor
@RequestMapping("sys/user")
@Api(value = "系统用户管理", tags = "系统用户管理")
public class SystemUserController {

//    ISceneService sceneService;
//
//    IUserRoleService userRoleService;
//
//    IUserSceneService userSceneService;

	IUserService userService;

//    @ApiOperation(value = "获取不在某一场景下的用户", notes = "已完成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
//    })
//    @GetMapping("users/noScene")
//    public RespBean getUsersWithoutScene(@RequestParam String accountName, @RequestParam String realName
//            , @RequestParam String email, @RequestParam String sceneId){
//        if (Func.hasEmpty(sceneId)){
//            return RespBean.paramError();
//        }
//        accountName = accountName == null ? "" : accountName;
//        realName = realName == null ? "" : realName;
//        email = email == null ? "" : email;
//        return RespBean.data(userService.getUsersWithoutScene(accountName, realName, email, sceneId));
//    }

	@GetMapping("list")
	@ApiOperation(value = "获取所有用户（兼模糊搜索）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每页数据量", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "realName", value = "realName", required = true, dataType = "string")
    })
    public R userList(Query query, @ApiIgnore @RequestParam Map<String, Object> user){
        PageHelper.startPage(query.getPage(), query.getSize());
		List<User> users = userService.list(Condition.getQueryWrapper(user, User.class)
			.lambda().orderByDesc(User::getCreatedTime));
        return R.data(new PageInfo<>(users));
    }

//    @ApiOperation(value = "管理控制台，添加用户"
//            , notes = "（已完成）必填项：accountName用户名;realName真实姓名;pwd密码;phoneNumber手机号;userEmail用户邮箱")
//    @PostMapping("add")
//    public RespBean addUser(@RequestBody User user){
//        if (Func.hasEmpty(user.getAccountName(), user.getRealName(), user.getPwd(), user.getPhoneNumber()
//                , user.getUserEmail(), user.getStatus(), user.getAdmin())){
//            return RespBean.paramError();
//        }
//        return RespBean.status(userService.saveUser(user));
//    }
//
//    @ApiOperation(value = "控制台中删除用户", notes = "（已完成，不对外开放）用户id集合")
//    @ApiImplicitParam(value = "ids", required = true, dataTypeClass = String.class)
//    @PostMapping("delete")
//    public RespBean removeUser(@RequestParam String ids){
//        if (Func.hasEmpty(ids)){
//            return RespBean.paramError();
//        }
//        List<String> strings = Func.toStrList(",", ids);
//        LinkedList<User> users = new LinkedList<>();
//        strings.forEach(id -> {
//            User user = new User();
//            user.setId(id);
//            user.setRemoveTime(new Date());
//            user.setDisableTime(new Date());
//            user.setStatus(false);
//            user.setStage(0);
//            users.add(user);
//        });
//        return RespBean.status(userService.updateBatchById(users));
//    }
//
//    @ApiOperation(value = "控制台中，获取单个用户信息", notes = "（已完成，不对外开放）")
//    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
//    @GetMapping("detail")
//    public RespBean getUser(@RequestParam String userId){
//        return RespBean.data(userService.getById(userId));
//    }
//
//    @ApiOperation(value = "控制台编辑用户信息", notes = "（已完成，不对外开放）accountName")
//    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
//    @PutMapping("edit")
//    public RespBean editUser(@RequestBody User user){
//        if (Func.hasEmpty(user.getId())){
//            return RespBean.paramError();
//        }
//        return RespBean.status(userService.updateById(user));
//    }
//
//    @ApiOperation(value = "注册用户激活", notes = "（已完成）sceneId场景ID;userId用户ID;组织管理员审核时sceneId置null或\"\"")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "sceneId", required = true, name = "场景id", dataTypeClass = String.class),
//            @ApiImplicitParam(value = "userId", required = true, name = "用户id", dataTypeClass = String.class)
//    })
//    @PostMapping(value = "active")
//    @Transactional
//    public RespBean userActivate(@RequestParam String sceneId, @RequestParam String userId) {
//        if (userService.getById(userId).getAdmin() == 1){
//            return RespBean.error("注册为平台管理员的接口还没做");
//        }
//        userService.enableUser(userId);
//        //设置平台级权限
//        UserRole userRole = new UserRole();
//        userRole.setCreatedTime(new Date());
//        userRole.setUid(userId);
//        userRole.setStatus(1);
//        userRole.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
//        userRoleService.save(userRole);
//        sceneService.enableScene(userId, sceneId);
//        return RespBean.ok("用户审核通过");
//    }
//
//    @ApiOperation(value = "重置用户密码", notes = "已完成，不对外开放")
//    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
//    @PutMapping("pwd/reset")
//    public RespBean passwordReset(String userId){
//        return RespBean.status(userService.resetPassword(userId));
//    }
//
//    @GetMapping("signify")
//    @ApiOperation(value = "判断用户所属部门", notes = "已完成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "userId", name = "用户id", required = true),
//            @ApiImplicitParam(value = "sceneId", name = "场景id", required = true)
//    })
//    public R userSignify(@RequestParam String userId, @RequestParam String sceneId){
//        UserScene record = userSceneService.getOne(Wrappers.<UserScene>lambdaQuery()
//                .eq(UserScene::getUserId, userId).eq(UserScene::getSceneId, sceneId)
//                .eq(UserScene::getStatus, 1));
//        return R.data(record.getNodeId());
//    }
}
