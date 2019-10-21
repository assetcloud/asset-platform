package com.asset.service;


import com.asset.bean.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 *  平台级角色服务类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Service
public interface ISceneRoleService extends IService<SceneRole> {

    /**
     * 根据角色组获取角色信息（与场景绑定）
     * @return List<RoleGroup>
     */
    List<RoleGroup> rolesWithGroup(String sceneId);

    /**
     * 获取所有角色信息
     * 仅平台管理员调用
     * @return List<SceneRole>
     */
    List<SceneRole> listAll(SceneRole sceneRole);

    /**
     * 该场景下，判断角色名称已被占用
     * @param record
     * @return
     */
    boolean roleExist(SceneRole record);

    /**
     * 该场景下，判断角色组名称已被占用
     * @param record
     * @return
     */
    boolean roleGroupExist(RoleGroup record);

    /**
     * 判断角色组下是否还有角色存在
     * @param groupId
     * @return
     */
    boolean rolesInGroup(Long groupId);

    /**
     * 获取场景中的默认角色
     * @param sceneId
     * @return
     */
    SceneRole getDefaultRole(String sceneId);

    /**
     * 新增场景时，为场景添加默认角色
     * @param sceneId
     * @return boolean
     */
    boolean addRoles4Scene(String sceneId);

    /**
     * 将场景中的默认角色有效化
     * @param sceneId
     * @return
     */
    boolean roleAvailable(String sceneId);

    /**
     * 用户注册时新增场景，场景中添加默认角色与组
     * @param sceneId
     * @return
     */
    boolean addDefaultRole4Reg(String sceneId, List<SceneRole> list);

    /**
     * 更新角色的角色组
     * @param record
     * @return
     */
    boolean updateGroupInfo(SceneRole record);

    /**
     * 为角色添加成员
     * @param roleId
     * @param userIds
     * @return
     */
    boolean addUsers2Role(Long roleId, List<String> userIds);

    /**
     * 角色信息模糊搜索
     * @param role
     * @return
     */
    List<SceneRole> getRoleByName(SceneRole role);

    /**
     * 新增角色组
     * @param record
     * @return
     */
    int addRoleGroup(RoleGroup record);

    /**
     * 删除角色组
     * @param id
     * @return
     */
    int deleteGroup(Long id);

    /**
     * 修改角色组名称
     * @param newRecord
     * @return
     */
    int modifyGroup(RoleGroup newRecord);

    /**
     * 根据角色获取用户
     * @param roleId
     * @return
     */
    List<User> getUsersByRole(Long roleId, String userName);

    /**
     * 根据角色list获取用户
     * @param roleIds
     * @return
     */
    List<String> getUsersByRoles(List<Long> roleIds);

    /**
     * 编辑角色权限
     * @param roleId
     * @param resourceIds
     * @return
     */
    boolean grant(@NotEmpty Long roleId, @NotEmpty List<Long> resourceIds);

    /**
     * 场景中为用户设置角色
     * @param userId
     * @param roleIds
     * @return
     */
    boolean setAuthority(String sceneId, @NotEmpty String userId, @NotEmpty List<Long> roleIds);

    /**
     * 获取场景中所有有效的角色
     * @param sceneId
     * @return
     */
    List<SceneRole> getRolesByScene(String sceneId);

    /**
     * 获取用户已经拥有的角色
     * @param userId
     * @return
     */
    List<SceneRole> getRolesOwned(String userId, String sceneId);

    /**
     * 获取不在当前
     * @param roleId
     * @return
     */
    List<User> getUsersByRoleInvert(String sceneId, Long roleId);
}
