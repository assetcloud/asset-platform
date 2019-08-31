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

import com.asset.bean.Menu;
import com.asset.bean.Scene;
import com.asset.common.SystemConstant;
import com.asset.service.IDictService;
import com.asset.service.IMenuService;
import com.asset.service.ISceneService;
import com.asset.vo.MenuVO;
import com.asset.vo.SceneVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

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
