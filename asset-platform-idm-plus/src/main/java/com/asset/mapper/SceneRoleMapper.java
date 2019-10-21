package com.asset.mapper;

import com.asset.bean.RoleGroup;
import com.asset.bean.SceneRole;
import com.asset.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Component
public interface SceneRoleMapper extends BaseMapper<SceneRole> {
    /**
     * 获取所有业务角色
     * @return List<SceneRole>
     */
    List<SceneRole> roles();

    /**
     * 场景中，根据角色组获取角色
     * @return
     */
    List<RoleGroup> groupRoles(String sceneId);

    /**
     * 获取所有业务级角色
     * @return
     */
    List<SceneRole> listAll(SceneRole sceneRole);

    SceneRole roleExist(String roleNameZh, String sceneCode);

    RoleGroup roleGroupExist(String roleGroupName, String sceneCode);

    List<SceneRole> rolesInGroup(Long groupId);

    SceneRole getDefaultRole(String sceneId);

    List<SceneRole> getRolesByScene(String sceneId);

    List<SceneRole> getAllByScene(String sceneId);

    int addRoleGroup(RoleGroup record);

    List<RoleGroup> getGroupByName(@Param("groupName") String groupName);

    int deleteGroup(Long id);

    int modifyGroup(Long id, @Param("groupName") String groupName);
    /**
     * 通过角色获取所属用户
     * @param roleId
     * @return
     */
    List<User> getUsersByRole(Long roleId, String userName);

    /**
     * 获取用户已经拥有的角色
     * @param userId 用户id
     * @param sceneId 场景id
     * @return List<SceneRole>
     */
    List<SceneRole> getRolesOwned(String userId, String sceneId);

    /**
     * 一个场景下，获取不属于目标角色的用户
     * @param sceneId 场景id
     * @param roleId 角色id
     * @return List<User>
     */
    List<User> getUsersByRoleInvert(String sceneId, Long roleId);
}
