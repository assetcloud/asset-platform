package com.asset.service.impl;


import com.asset.bean.Role;
import com.asset.mapper.RoleMapper;
import com.asset.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
