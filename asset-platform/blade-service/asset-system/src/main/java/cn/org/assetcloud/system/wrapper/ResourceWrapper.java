package cn.org.assetcloud.system.wrapper;

import cn.org.assetcloud.system.entity.Resource;
import cn.org.assetcloud.system.entity.ResourceGroup;
import cn.org.assetcloud.system.entity.Scene;
import cn.org.assetcloud.system.service.IResourceGroupService;
import cn.org.assetcloud.system.service.IResourceService;
import cn.org.assetcloud.system.service.ISceneService;
import cn.org.assetcloud.system.utils.ResourceVONodeMerger;
import cn.org.assetcloud.system.vo.ResourceGroupVO;
import cn.org.assetcloud.system.vo.ResourceVO;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.SystemConstant;
import org.springblade.core.tool.api.R;
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
public class ResourceWrapper {

	IResourceService resourceService;

	IDictClient dictClient;

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
		R<String> d1 = dictClient.getValue("resource_category", Func.toInt(resource.getCategory()));
		if (d1.isSuccess()) {
			resourceVO.setCategoryName(d1.getData());
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
