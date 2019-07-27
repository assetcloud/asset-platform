package com.asset.service.impl;

import com.asset.bean.UserRole;
import com.asset.mapper.UserRoleMapper;
import com.asset.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
