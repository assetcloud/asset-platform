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
package cn.org.assetcloud.system.wrapper;

import cn.org.assetcloud.system.entity.Scene;
import cn.org.assetcloud.system.service.ISceneService;
import cn.org.assetcloud.system.vo.SceneVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@AllArgsConstructor
public class SceneWrapper {

	ISceneService sceneService;

	public SceneWrapper() {
	}

	public SceneVO entityVO(Scene scene) {
		return BeanUtil.copy(scene, SceneVO.class);
	}

	public List<SceneVO> listNodeVO(List<Scene> list) {
		return list.stream().map(scene -> BeanUtil.copy(scene, SceneVO.class)).collect(Collectors.toList());
	}
}
