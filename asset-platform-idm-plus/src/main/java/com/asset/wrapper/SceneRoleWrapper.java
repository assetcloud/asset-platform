package com.asset.wrapper;

import com.asset.bean.Resource;
import com.asset.bean.RoleGroup;
import com.asset.bean.Scene;
import com.asset.bean.SceneRole;
import com.asset.common.SystemConstant;
import com.asset.service.*;
import com.asset.utils.ResourceVONodeMerger;
import com.asset.vo.ResourceVO;
import com.asset.vo.SceneRoleVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author hjhu
 */
@AllArgsConstructor
public class SceneRoleWrapper {

	ISceneRoleService sceneRoleService;

	IRoleGroupService roleGroupService;

	ISceneService sceneService;

	IDictService dictService;

	public SceneRoleWrapper() {
	}

	public SceneRoleVO entityVO(SceneRole sceneRole) {
		SceneRoleVO sceneRoleVO = BeanUtil.copy(sceneRole, SceneRoleVO.class);
		RoleGroup roleGroup = roleGroupService.getById(sceneRole.getGroupId());
		String d1 = dictService.getValue("scene_role_type", sceneRole.getRoleType());
		if (Func.isNotEmpty(d1)){
			sceneRoleVO.setRoleType(d1);
		}
		if (Func.isNotEmpty(roleGroup)) {
			sceneRoleVO.setGroupName(roleGroup.getRoleGroupName());
		}
		Scene scene = sceneService.getById(roleGroup.getSceneCode());
		if (Func.isNotEmpty(scene.getSceneName())){
			sceneRoleVO.setSceneName(scene.getSceneName());
		}
		return sceneRoleVO;
	}

	public List<SceneRoleVO> listNodeVO(List<SceneRole> list) {
		return list.stream().map(sceneRole -> BeanUtil.copy(sceneRole, SceneRoleVO.class)).collect(Collectors.toList());
	}
}
