package cn.org.assetcloud.system.service.impl;

import cn.org.assetcloud.system.entity.UserRole;
import cn.org.assetcloud.system.mapper.UserRoleMapper;
import cn.org.assetcloud.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
