package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.RoleGroup;
import cn.org.assetcloud.system.entity.SceneRole;
import cn.org.assetcloud.system.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */

@Mapper
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
     * @param userId
     * @param sceneId
     * @return
     */
    List<SceneRole> getRolesOwned(String userId, String sceneId);
}
