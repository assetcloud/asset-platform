package cn.org.assetcloud.system.wrapper;

import cn.org.assetcloud.system.entity.ResourceGroup;
import cn.org.assetcloud.system.entity.Scene;
import cn.org.assetcloud.system.service.IResourceGroupService;
import cn.org.assetcloud.system.service.ISceneService;
import cn.org.assetcloud.system.vo.ResourceGroupVO;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;

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
