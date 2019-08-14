package com.asset.service;

import com.asset.bean.ResourceGroup;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IResourceGroupService extends IService<ResourceGroup> {

    /**
     * 检测资源分组是否存在
     * @param resourceGroup
     * @return
     */
    boolean groupExists(ResourceGroup resourceGroup);
}
