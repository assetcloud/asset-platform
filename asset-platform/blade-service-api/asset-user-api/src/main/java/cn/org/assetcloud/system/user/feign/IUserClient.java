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
package cn.org.assetcloud.system.user.feign;


import cn.org.assetcloud.system.user.entity.User;
import org.springblade.common.constant.ApplConstant;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * User Feign接口类
 *
 * @author hjhu
 */
@FeignClient(
	value = ApplConstant.APPLICATION_USER_NAME
)
public interface IUserClient {

	String API_PREFIX = "/user";

	/**
	 * 获取用户信息
	 *
	 * @param account  账号
	 * @param password 密码
	 * @return
	 */
	@GetMapping(API_PREFIX + "/user-info")
	R<User> userInfo(@RequestParam("account") String account, @RequestParam("password") String password);

	/**
	 * 获取场景成员
	 * @param userIds 用户id集合
	 * @return
	 */
	@GetMapping(API_PREFIX + "/user-by-scene")
	R<List<User>> usersByScene(@RequestParam List<String> userIds);
}
