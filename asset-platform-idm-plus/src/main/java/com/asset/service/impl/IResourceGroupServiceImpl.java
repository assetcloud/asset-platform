package com.asset.service.impl;

import com.asset.bean.ResourceGroup;
import com.asset.mapper.ResourceGroupMapper;
import com.asset.service.IResourceGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author hjhu
 */

@Service
public class IResourceGroupServiceImpl extends ServiceImpl<ResourceGroupMapper, ResourceGroup> implements IResourceGroupService {
}
