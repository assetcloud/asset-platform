package com.asset.service;

import com.asset.bean.Role;
import com.asset.bean.RoleGroup;
import com.asset.bean.User;
import com.asset.mapper.RoleMapper;
import com.asset.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2018/05/28.
 */
@Service
@Transactional
public class RoleService{

    @Autowired
    RoleMapper roleMapper;

    public List<Role> roles() {
        return roleMapper.roles();
    }

    public List<RoleGroup> rolesWithGroup() {
        return roleMapper.groupRoles();
    }

    public int addRole(Role record) {
        if (!record.getRoleName().startsWith("ROLE_")) {
            record.setRoleName("ROLE_" + record.getRoleName());
        }
        return roleMapper.insert(record);
    }

    public int addRoleGroup(RoleGroup record){
        if(roleMapper.getGroupByName(record.getRoleGroupName()).size() > 0){
            return -2;
        }
        record.setAddTime(new Date());
        return roleMapper.addRoleGroup(record);
    }

    public int deleteGroup(Long id){
        return roleMapper.deleteGroup(id);
    }

    public int modifyGroup(RoleGroup newRecord){
        return roleMapper.modifyGroup(newRecord.getId(), newRecord.getRoleGroupName());
    }

    public int addRole2Group(Long rid, Long groupId){
        return roleMapper.addRoleToGroup(rid, groupId);
    }

    public int deleteRoleById(int id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
