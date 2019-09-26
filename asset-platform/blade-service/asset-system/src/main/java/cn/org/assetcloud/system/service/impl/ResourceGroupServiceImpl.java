package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.ResourceGroup;
import cn.org.assetcloud.system.mapper.ResourceGroupMapper;
import cn.org.assetcloud.system.service.IResourceGroupService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hjhu
 */

@Service
@AllArgsConstructor
public class ResourceGroupServiceImpl extends ServiceImpl<ResourceGroupMapper, ResourceGroup> implements IResourceGroupService {

	private ResourceGroupMapper resourceGroupMapper;

    @Override
    public boolean groupExists(ResourceGroup resourceGroup) {

        Integer count;
        if (resourceGroup.getId() == null){
            count = baseMapper.selectCount(Wrappers.<ResourceGroup>lambdaQuery()
                    .eq(ResourceGroup::getAppId, resourceGroup.getAppId())
                    .eq(ResourceGroup::getName, resourceGroup.getName()));
        } else {
            count = baseMapper.selectCount(Wrappers.<ResourceGroup>lambdaQuery()
                    .eq(ResourceGroup::getAppId, resourceGroup.getAppId())
                    .eq(ResourceGroup::getName, resourceGroup.getName())
                    .notIn(ResourceGroup::getId, resourceGroup.getId()));
        }
        return count > 0;
    }
}
