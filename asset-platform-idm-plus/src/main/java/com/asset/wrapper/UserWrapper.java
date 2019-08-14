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
import com.asset.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

import java.util.List;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@AllArgsConstructor
public class UserWrapper{

	IUserService userService;

	IDictService dictService;

	IRoleService roleService;

	public UserWrapper() {
	}

	public UserVO entityVO(User user) {
		UserVO userVO = BeanUtil.copy(user, UserVO.class);
		String roleName = roleService.getRoleName(user.getRoleId()).getRoleNameZh();
		userVO.setRoleNameZh(roleName);
		String gender = dictService.getValue("sex", Func.toInt(user.getGender()));
		if (Func.hasEmpty(gender)) {
			userVO.setSexName(gender);
		}
		return userVO;
	}

}
