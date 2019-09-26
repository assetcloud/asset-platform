package cn.org.assetcloud.system.service;

import cn.org.assetcloud.system.entity.ResourceGroup;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IResourceGroupService extends IService<ResourceGroup> {

    /**
     * 检测资源分组是否存在
     * @param resourceGroup
     * @return
     */
    boolean groupExists(ResourceGroup resourceGroup);
}
