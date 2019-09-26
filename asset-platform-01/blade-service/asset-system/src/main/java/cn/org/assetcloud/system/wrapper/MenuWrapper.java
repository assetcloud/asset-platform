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

import cn.org.assetcloud.system.entity.Menu;
import cn.org.assetcloud.system.service.IMenuService;
import cn.org.assetcloud.system.vo.MenuVO;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.SystemConstant;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@AllArgsConstructor
public class MenuWrapper{

	IMenuService menuService;

	private IDictClient dictClient;

	public MenuWrapper() {
	}

	public MenuVO entityVO(Menu menu) {
		MenuVO menuVO = BeanUtil.copy(menu, MenuVO.class);
		if (Func.equals(menu.getParentId(), SystemConstant.TOP_PARENT_ID)) {
			menuVO.setParentName(SystemConstant.TOP_PARENT_NAME);
		} else {
			Menu parent = menuService.getById(menu.getParentId());
			menuVO.setParentName(parent.getName());
		}

		R<String> d1 = dictClient.getValue("menu_category", Func.toInt(menuVO.getCategory()));
		R<String> d2 = dictClient.getValue("button_func", Func.toInt(menuVO.getAction()));
		R<String> d3 = dictClient.getValue("yes_no", Func.toInt(menuVO.getIsOpen()));
		if (d1.isSuccess()) {
			menuVO.setCategoryName(d1.getData());
		}
		if (d2.isSuccess()) {
			menuVO.setActionName(d2.getData());
		}
		if (d3.isSuccess()) {
			menuVO.setIsOpenName(d3.getData());
		}
//		String d1 = dictClient.getValue("menu_category", Func.toInt(menuVO.getCategory()));
//		String d2 = dictClient.getValue("button_func", Func.toInt(menuVO.getAction()));
//		String d3 = dictClient.getValue("yes_no", Func.toInt(menuVO.getIsOpen()));
//		if (Func.isNotEmpty(d1)) {
//			menuVO.setCategoryName(d1);
//		}
//		if (Func.isNotEmpty(d2)) {
//			menuVO.setActionName(d2);
//		}
//		if (Func.isNotEmpty(d3)) {
//			menuVO.setIsOpenName(d3);
//		}
		return menuVO;
	}


	public List<MenuVO> listNodeVO(List<Menu> list) {
		List<MenuVO> collect = list.stream().map(menu -> BeanUtil.copy(menu, MenuVO.class)).collect(Collectors.toList());
		return ForestNodeMerger.merge(collect);
	}
}
