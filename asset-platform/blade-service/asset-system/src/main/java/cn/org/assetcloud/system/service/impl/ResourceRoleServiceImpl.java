package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.ResourceRole;
import cn.org.assetcloud.system.mapper.ResourceGroupMapper;
import cn.org.assetcloud.system.mapper.ResourceRoleMapper;
import cn.org.assetcloud.system.service.IResourceRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ResourceRoleServiceImpl extends ServiceImpl<ResourceRoleMapper, ResourceRole> implements IResourceRoleService {

	private ResourceGroupMapper resourceGroupMapper;
}
