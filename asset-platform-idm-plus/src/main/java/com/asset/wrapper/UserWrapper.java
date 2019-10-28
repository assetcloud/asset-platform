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
package com.asset.wrapper;

import com.asset.bean.User;
import com.asset.service.IDictService;
import com.asset.service.IRoleService;
import com.asset.service.IUserService;
import com.asset.vo.SceneRoleVO;
import com.asset.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.ObjectUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@AllArgsConstructor
@Slf4j
public class UserWrapper{

	IUserService userService;

	IDictService dictService;

	IRoleService roleService;

	public UserWrapper() {
	}

	public UserVO entityVO(User user) {
		UserVO userVO = BeanUtil.copy(user, UserVO.class);
		if (ObjectUtil.isNotEmpty(user.getRoleId())){
			String roleName = roleService.getRoleName(user.getRoleId()).getRoleNameZh();
			userVO.setRoleNameZh(roleName);
		}
		String gender = dictService.getValue("sex", Func.toInt(user.getGender()));
		if (Func.isNotEmpty(gender)) {
			userVO.setSexName(gender);
		}
		return userVO;
	}

	public List<UserVO> listNodeVO(List<User> list) {
		return list.stream().map(this::entityVO).collect(Collectors.toList());
	}
//
//	public List<UserVO> listNodeVOWithSceneRoles(List<User> list) {
//		for (User user1 : list) {
//			user1.getSceneRoles().forEach(sceneRole -> BeanUtil.copy(sceneRole, SceneRoleVO.class));
//		}
//		return list.stream().map(user -> BeanUtil.copy(user, UserVO.class)).collect(Collectors.toList());
//	}
}
