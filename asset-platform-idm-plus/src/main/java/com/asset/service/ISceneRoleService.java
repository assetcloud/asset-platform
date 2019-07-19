package com.asset.service;


import com.asset.bean.RoleGroup;
import com.asset.bean.SceneRole;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.stereotype.Service;

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
     * 获取所有角色信息
     * 仅平台管理员调用
     * @return List<SceneRole>
     */
    List<SceneRole> getAllRoles();
    /**
     * 根据角色组获取角色信息（与场景绑定）
     * @return List<RoleGroup>
     */
    List<RoleGroup> rolesWithGroup();
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
}
