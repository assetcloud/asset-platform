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
package cn.org.assetcloud.system.vo;

import cn.org.assetcloud.system.entity.SceneRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * 视图实体类
 *
 * @author hjhu
 */
@Data
@ApiModel(value = "UserVO对象", description = "UserVO对象")
public class UserVO{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 角色名
	 */
	private String roleNameZh;

	/**
	 * 性别
	 */
	private String sexName;

	/**
	 * 所属部门id
	 */
	private String nodeName;

	/**
	 * 联系电话
	 */
	private String phoneNumber;

	/**
	 * 邮箱
	 */
	private String userEmail;

	/**
	 * 是否部门负责人
	 */
	private Integer nodePrincipal;

	/**
	 * 用户所属业务角色
	 */
	private List<SceneRole> sceneRoles;
}
