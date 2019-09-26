package cn.org.assetcloud.system.wrapper;

import cn.org.assetcloud.system.entity.RoleGroup;
import cn.org.assetcloud.system.entity.Scene;
import cn.org.assetcloud.system.entity.SceneRole;
import cn.org.assetcloud.system.service.IRoleGroupService;
import cn.org.assetcloud.system.service.ISceneRoleService;
import cn.org.assetcloud.system.service.ISceneService;
import cn.org.assetcloud.system.vo.SceneRoleVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictClient;

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

	IDictClient dictClient;

	public SceneRoleWrapper() {
	}

	public SceneRoleVO entityVO(SceneRole sceneRole) {
		SceneRoleVO sceneRoleVO = BeanUtil.copy(sceneRole, SceneRoleVO.class);
		RoleGroup roleGroup = roleGroupService.getById(sceneRole.getGroupId());
		R<String> d1 = dictClient.getValue("scene_role_type", sceneRole.getRoleType());
		if (d1.isSuccess()){
			sceneRoleVO.setRoleType(d1.getData());
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
