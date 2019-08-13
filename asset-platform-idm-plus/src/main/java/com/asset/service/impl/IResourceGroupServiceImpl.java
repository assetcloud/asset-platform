package com.asset.service.impl;

import com.asset.bean.ResourceGroup;
import com.asset.mapper.ResourceGroupMapper;
import com.asset.service.IResourceGroupService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hjhu
 */

@Service
public class IResourceGroupServiceImpl extends ServiceImpl<ResourceGroupMapper, ResourceGroup> implements IResourceGroupService {

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
