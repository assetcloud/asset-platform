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

import com.asset.bean.Role;
import com.asset.vo.RoleVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author hjhu
 */
public class RoleWrapper{

	public RoleWrapper() {
	}

	public RoleVO entityVO(Role role) {
		return BeanUtil.copy(role, RoleVO.class);
	}

	public List<RoleVO> listNodeVO(List<Role> list) {
		return list.stream().map(this::entityVO).collect(Collectors.toList());
	}

}
