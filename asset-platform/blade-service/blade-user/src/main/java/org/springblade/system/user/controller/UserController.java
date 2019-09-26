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
package org.springblade.system.user.controller;


import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author hjhu
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

	@GetMapping("test")
	public R<String> get(@RequestParam String s){
		return R.data(s);
	}

//	private IUserService userService;
//
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

}
