package cn.org.assetcloud.system.user.controller;

import cn.org.assetcloud.system.entity.UserRole;
import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.user.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.SystemConstant;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

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

	IUserService userService;

    @ApiOperation(value = "获取不在某一场景下的用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "string"),
			@ApiImplicitParam(name = "accountName", value = "用户名", required = true, dataType = "string"),
			@ApiImplicitParam(name = "realName", value = "用户真实姓名", required = true, dataType = "string"),
			@ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "string")
    })
    @GetMapping("users-scene-invert")
    public R getUsersWithoutScene(@ApiIgnore @RequestParam Map<String, Object> user, @RequestParam String sceneId){
//        accountName = accountName == null ? "" : accountName;
//        realName = realName == null ? "" : realName;
//        email = email == null ? "" : email;

		// TODO 待修改为feign调用
//		QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(user, User.class);
//		queryWrapper.lambda().in(User::getId, new ArrayList<User>());
//		List<User> list = userService.list(queryWrapper);
        return R.data(userService.getUsersWithoutScene(user.get("accountName").toString(), user.get("realName").toString(),
			user.get("email").toString(), sceneId));
    }

	@GetMapping("list")
	@ApiOperation(value = "获取所有用户（兼模糊搜索）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", defaultValue = "1", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页数据量", defaultValue = "10", required = true, dataType = "int"),
            @ApiImplicitParam(name = "realName", value = "realName", required = true, dataType = "string")
    })
    public R userList(Query query, @ApiIgnore @RequestParam Map<String, Object> user){
        PageHelper.startPage(query.getCurrent(), query.getSize());
		List<User> users = userService.list(Condition.getQueryWrapper(user, User.class)
			.lambda().orderByDesc(User::getCreatedTime));
        return R.data(new PageInfo<>(users));
    }

    @ApiOperation(value = "管理控制台，添加用户"
            , notes = "（已完成）必填项：accountName用户名;realName真实姓名;pwd密码;phoneNumber手机号;userEmail用户邮箱")
    @PostMapping("save")
    public R addUser(@RequestBody User user){
        return R.status(userService.save(user));
    }

    @ApiOperation(value = "控制台中删除用户", notes = "（已完成，不对外开放）用户id集合")
    @ApiImplicitParam(value = "ids", required = true, dataType = "string")
    @PostMapping("remove")
    public R removeUser(@RequestParam String ids){
        List<String> strings = Func.toStrList(",", ids);
        LinkedList<User> users = new LinkedList<>();
        strings.forEach(id -> {
            User user = new User();
            user.setId(id);
            user.setRemoveTime(new Date());
            user.setDisableTime(new Date());
            user.setStatus(0);
            user.setStage(0);
            users.add(user);
        });
        return R.status(userService.updateBatchById(users));
    }

    @ApiOperation(value = "控制台中，获取单个用户信息", notes = "（已完成，不对外开放）")
    @ApiImplicitParam(value = "userId", required = true, dataType = "string")
    @GetMapping("detail")
    public R getUser(@RequestParam String userId){
        return R.data(userService.getById(userId));
    }

    @ApiOperation(value = "控制台编辑用户信息", notes = "（已完成，不对外开放）accountName")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PutMapping("update")
    public R editUser(@RequestBody User user){
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
//        if (userService.getById(userId).getAdmin() == 1){
//            return RespBean.error("注册为平台管理员的接口还没做");
//        }
        userService.enableUser(userId);
        //设置平台级权限
        UserRole userRole = new UserRole();
        userRole.setCreatedTime(new Date());
        userRole.setUid(userId);
        userRole.setStatus(1);
        userRole.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
        // TODO 改为feign调用
//        userRoleService.save(userRole);
//        sceneService.enableScene(userId, sceneId);
        return R.success("用户审核通过");
    }

    @ApiOperation(value = "重置用户密码", notes = "已完成，不对外开放")
    @ApiImplicitParam(value = "userId", required = true, dataTypeClass = String.class)
    @PutMapping("pwd/reset")
    public R passwordReset(String userId){
        return R.status(userService.resetPassword(userId));
    }
}
