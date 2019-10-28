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
package com.asset.vo;

import com.asset.bean.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tool.node.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * 视图实体类
 *
 * @author hjhu
 */

@Data
@ApiModel(value = "RoleVO对象", description = "RoleVO对象")
public class RoleVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 父节点ID
	 */
	private String roleNameZh;

	/**
	 * 上级角色
	 */
	private String roleName;

	/**
	 * 是否可用
	 */
	private Integer isDeleted;
}
