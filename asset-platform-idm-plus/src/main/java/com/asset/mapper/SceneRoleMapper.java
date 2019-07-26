package com.asset.mapper;

import com.asset.bean.RoleGroup;
import com.asset.bean.SceneRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
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
     * 按照角色组获取角色
     * 场景管理员常用
     * @return
     */
    List<RoleGroup> groupRoles();

    SceneRole roleExist(String roleNameZh, String sceneCode);

    RoleGroup roleGroupExist(String roleGroupName, String sceneCode);

    List<SceneRole> rolesInGroup(Long groupId);

    SceneRole getDefaultRole(String sceneId);

    List<SceneRole> getRolesByScene(String sceneId);

    List<SceneRole> getAllByScene(String sceneId);
}
