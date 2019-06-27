package com.asset.mapper;

import com.asset.bean.Role;
import com.asset.bean.RoleGroup;
import com.asset.bean.User;
import com.asset.bean.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {

    List<Role> roles();
    int deleteByPrimaryKey(Integer id);
    int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKey(Role record);
    int addRoleGroup(RoleGroup record);
    List<RoleGroup> getGroupByName(@Param("groupName") String groupName);
    int deleteGroup(Long id);
    int modifyGroup(Long id, @Param("groupName") String groupName);
    int addRoleToGroup(@Param("rid") Long rid, @Param("groupId") Long groupId);
    List<RoleGroup> groupRoles();
    int addUsersToRole(List<UserRole> users);
    Role roleSearchByName(String roleNameZh);
    int batchDeleteRoleMember(List<UserRole> userRoleList);
    List<User> getUsersByRole(@Param("roleId") Long roleId);
}
