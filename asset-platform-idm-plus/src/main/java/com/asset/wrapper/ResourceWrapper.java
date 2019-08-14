package com.asset.wrapper;

import com.asset.bean.Resource;
import com.asset.bean.ResourceGroup;
import com.asset.bean.Scene;
import com.asset.common.SystemConstant;
import com.asset.service.IDictService;
import com.asset.service.IResourceGroupService;
import com.asset.service.IResourceService;
import com.asset.service.ISceneService;
import com.asset.utils.ResourceVONodeMerger;
import com.asset.vo.ResourceGroupVO;
import com.asset.vo.ResourceVO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
@AllArgsConstructor
public class ResourceWrapper {

	IResourceService resourceService;

	IDictService dictService;

	ISceneService sceneService;

	IResourceGroupService resourceGroupService;

	public ResourceWrapper() {
	}

	public ResourceVO entityVO(Resource resource) {

		ResourceVO resourceVO = BeanUtil.copy(resource, ResourceVO.class);
		if (Func.equals(resource.getParentId(), SystemConstant.RESOURCE_TOP_PARENT_ID)) {
			resourceVO.setParentName(SystemConstant.TOP_PARENT_NAME);
		} else {
			Resource parent = resourceService.getById(resource.getId());
			resourceVO.setParentName(parent.getName());
		}
		String d1 = dictService.getValue("resource_category", Func.toInt(resource.getCategory()));
		if (Func.isNotEmpty(d1)) {
			resourceVO.setCategoryName(d1);
		}
		Scene scene = sceneService.getById(resource.getSceneId());
		if (Func.isNotEmpty(scene.getSceneName())){
			resourceVO.setSceneName(scene.getSceneName());
		}
		ResourceGroup group = resourceGroupService.getById(resource.getGroupId());
		if (Func.isNotEmpty(group)){
			resourceVO.setGroupName(group.getName());
		}
		return resourceVO;
	}

	public List<ResourceVO> listNodeVO(List<Resource> list) {

		List<ResourceVO> collect = list.stream().map(resource -> BeanUtil.copy(resource, ResourceVO.class)).collect(Collectors.toList());
		return ResourceVONodeMerger.merge(collect);
	}

	public List<ResourceGroupVO> listNodeVOWithGroup(Long appId, List<Resource> list) {

		List<ResourceGroup> groups = resourceGroupService.list(Wrappers.<ResourceGroup>lambdaQuery()
				.eq(ResourceGroup::getAppId, appId));
		ResourceGroup noGroup = new ResourceGroup();
		noGroup.setId(0L);
		noGroup.setName("未分组");
		groups.add(noGroup);
		List<ResourceGroupVO> collect = groups.stream().map(group -> BeanUtil.copy(group, ResourceGroupVO.class)).collect(Collectors.toList());
		for (ResourceGroupVO group : collect) {
			for (Resource resource : list) {
				if (resource.getGroupId().equals(group.getId())){
					ResourceVO resourceVO = BeanUtil.copy(resource, ResourceVO.class);
					group.getChildren().add(resourceVO);
				}
			}
		}
//		if (collect.get(collect.size()-1).getChildren().size() <= 0){
//			collect.remove(collect.size()-1);
//		}
		return collect;
	}
}
