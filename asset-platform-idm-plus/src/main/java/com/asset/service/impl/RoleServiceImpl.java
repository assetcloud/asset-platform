package com.asset.service.impl;


import com.asset.bean.MenuRole;
import com.asset.bean.Role;
import com.asset.mapper.RoleMapper;
import com.asset.service.IMenuRoleService;
import com.asset.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    IMenuRoleService menuRoleService;

    @Override
    public List<Role> getAll() {
        return baseMapper.selectList(Wrappers.<Role>query().lambda()
                .orderByAsc(Role::getSort));
    }

    @Override
    public boolean grant(@NotEmpty Integer roleId, @NotEmpty List<Long> menuIds) {
        menuRoleService.remove(Wrappers.<MenuRole>update().lambda()
                .eq(MenuRole::getRoleId, roleId));
        List<MenuRole> menuRoles = new ArrayList<>();
        menuIds.forEach(menuId -> {
            MenuRole menuRole = new MenuRole();
            menuRole.setMenuId(menuId);
            menuRole.setRoleId(roleId);
            menuRoles.add(menuRole);
        });
        return menuRoleService.saveBatch(menuRoles);
    }

    @Override
    public boolean batchDelete(@NotEmpty List<Integer> roleIds) {
        List<Role> roles = new ArrayList<>();
        roleIds.forEach(roleId->{
            Role role = new Role();
            role.setId(roleId);
            role.setIsDeleted(1);
            roles.add(role);
        });
        return this.updateBatchById(roles);
    }

    @Override
    public boolean roleExists(Role role) {
        return this.count(Wrappers.<Role>query().lambda()
                .eq(Role::getRoleNameZh, role.getRoleNameZh())
                .eq(Role::getIsDeleted, 0)) > 0;
    }

    @Override
    public Role getRoleName(Integer roleId) {
        return baseMapper.selectById(roleId);
    }

    @Override
    public List<Role> getAllRolesSelective() {
        return this.list(Wrappers.<Role>lambdaQuery().select(Role::getId, Role::getRoleNameZh, Role::getRoleName, Role::getIsDeleted)
                .orderByAsc(Role::getSort));
    }
}
