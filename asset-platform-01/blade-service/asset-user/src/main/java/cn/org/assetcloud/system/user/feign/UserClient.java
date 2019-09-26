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
import cn.org.assetcloud.system.user.service.IUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户服务Feign实现类
 *
 * @author hjhu
 */
@RestController
@AllArgsConstructor
public class UserClient implements IUserClient {

	IUserService service;

	@Override
	@GetMapping(API_PREFIX + "/user-info")
	public R<User> userInfo(String account, String password) {
		return R.data(service.getUser(account, password));
	}

	@Override
	@GetMapping(API_PREFIX + "/user-by-scene")
	public R<List<User>> usersByScene(List<String> userIds) {
		return R.data(service.list(Wrappers.<User>query().lambda().in(User::getId, userIds)));
	}
}
