/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.org.assetcloud.system.user.controller;


import cn.org.assetcloud.system.user.entity.User;
import cn.org.assetcloud.system.user.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.SystemConstant;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author hjhu
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

	private IUserService userService;

//	private IDictClient dictClient;
//
//	/**
//	 * 查询单条
//	 */
//	@ApiOperation(value = "查看详情", notes = "传入id", position = 1)
//	@GetMapping("/detail")
//	public R<UserVO> detail(User user) {
//		User detail = userService.getOne(Condition.getQueryWrapper(user));
//		UserWrapper userWrapper = new UserWrapper(userService, dictClient);
//		return R.data(userWrapper.entityVO(detail));
//	}
//
//	/**
//	 * 用户列表
//	 */
//	@GetMapping("/list")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "account", value = "账号名", paramType = "query", dataType = "string"),
//		@ApiImplicitParam(name = "realName", value = "姓名", paramType = "query", dataType = "string")
//	})
//	@ApiOperation(value = "列表", notes = "传入account和realName", position = 2)
//	public R<IPage<UserVO>> list(@ApiIgnore @RequestParam Map<String, Object> user, Query query, BladeUser bladeUser) {
//		QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(user, User.class);
//		IPage<User> pages = userService.page(Condition.getPage(query), (!bladeUser.getTenantCode().equals(BladeConstant.ADMIN_TENANT_CODE)) ? queryWrapper.lambda().eq(User::getTenantCode, bladeUser.getTenantCode()) : queryWrapper);
//		UserWrapper userWrapper = new UserWrapper(userService, dictClient);
//		return R.data(userWrapper.pageVO(pages));
//	}
//
//	/**
//	 * 新增或修改
//	 */
//	@PostMapping("/submit")
//	@ApiOperation(value = "新增或修改", notes = "传入User", position = 3)
//	public R submit(@Valid @RequestBody User user) {
//		return R.status(userService.submit(user));
//	}
//
//	/**
//	 * 修改
//	 */
//	@PostMapping("/update")
//	@ApiOperation(value = "修改", notes = "传入User", position = 3)
//	public R update(@Valid @RequestBody User user) {
//		return R.status(userService.updateById(user));
//	}
//
//	/**
//	 * 删除
//	 */
//	@PostMapping("/remove")
//	@ApiOperation(value = "删除", notes = "传入地基和", position = 4)
//	public R remove(@RequestParam String ids) {
//		return R.status(userService.deleteLogic(Func.toIntList(ids)));
//	}
//
//
//	/**
//	 * 设置菜单权限
//	 *
//	 * @param userIds
//	 * @param roleIds
//	 * @return
//	 */
//	@PostMapping("/grant")
//	@ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合", position = 5)
//	public R grant(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds,
//				   @ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds) {
//		boolean temp = userService.grant(userIds, roleIds);
//		return R.status(temp);
//	}
//
//	@PostMapping("/reset-password")
//	@ApiOperation(value = "初始化密码", notes = "传入userId集合", position = 5)
//	public R resetPassword(@ApiParam(value = "userId集合", required = true) @RequestParam String userIds) {
//		boolean temp = userService.resetPassword(userIds);
//		return R.status(temp);
//	}

	@RequestMapping(value = "/userReg", method = RequestMethod.POST)
	@ApiOperation(value = "用户注册"
		, notes = "（已完成）用户注册操作（用户信息用json传输,场景信息用query形式）;accountName账号;pwd密码;realName真实姓名;admin是否为平台管理员;" +
		"sceneIds场景id数组;")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "accountName", value = "用户账号", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pwd", value = "用户密码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "realName", value = "用户真实姓名", required = true, dataType = "String"),
		@ApiImplicitParam(name = "admin", value = "是否为总管理员", required = true, dataType = "Integer")
	})
	@Transactional
	public R userReg(@RequestBody User user, @RequestParam(value = "sceneId") String sceneIds) {
		Map<String, String> jsonMap = new HashMap<>();
		if (userService.userExists(user.getAccountName())){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.fail("用户名已被占用，请更换后重试");
		}
		//用户设置并新增
		user.setStage(1);
		user.setStatus(0);
		user.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
		user.setCreatedTime(new Date());
		userService.insertUser(user);
		//绑定场景与用户
		// TODO Feign调用
//		sceneService.userSceneBind(Func.toStrList(",", sceneIds), user.getId());
		jsonMap.put("userId", user.getId());
		jsonMap.put("sceneIds", sceneIds);
		return R.data(jsonMap);
	}

	@ApiOperation(value = "获取用户", notes = "根据角色获取用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "long")
	})
	@RequestMapping(value = "/users/{roleId}", method = RequestMethod.GET)
	public List<User> userAudit(@PathVariable("roleId") Long roleId){
		return userService.getUsersByRole(roleId);
	}
}
