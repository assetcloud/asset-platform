package com.asset.wrapper;

import com.asset.bean.ResourceGroup;
import com.asset.bean.Scene;
import com.asset.service.IResourceGroupService;
import com.asset.service.ISceneService;
import com.asset.utils.Func;
import com.asset.vo.ResourceGroupVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ResourceGroupWrapper {

    IResourceGroupService resourceGroupService;

    ISceneService sceneService;

    public ResourceGroupWrapper() {
    }

    public ResourceGroupVO entityVO(ResourceGroup resourceGroup) {
        ResourceGroupVO resourceGroupVO = BeanUtil.copy(resourceGroup, ResourceGroupVO.class);
        Scene scene = sceneService.getById(resourceGroup.getSceneId());
        if (Func.isNotEmpty(scene.getSceneName())){
            resourceGroupVO.setSceneName(scene.getSceneName());
        }
        return resourceGroupVO;
    }

    public List<ResourceGroupVO> listNodeVO(List<ResourceGroup> list) {
        return list.stream().map(resourceGroup -> BeanUtil.copy(resourceGroup, ResourceGroupVO.class)).collect(Collectors.toList());
    }
}
