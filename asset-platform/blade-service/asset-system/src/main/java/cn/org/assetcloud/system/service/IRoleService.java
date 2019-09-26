package cn.org.assetcloud.system.service;


import cn.org.assetcloud.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  平台级角色服务类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取所有平台级角色
     * @return
     */
    List<Role> getAll();

    /**
     * 编辑平台角色权限
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean grant(Integer roleId, List<Long> menuIds);

    /**
     * 批量删除角色
     * @param roleIds
     * @return boolean
     */
    boolean batchDelete(List<Integer> roleIds);

    /**
     * 判断角色是否存在
     * @param role
     * @return
     */
    boolean roleExists(Role role);

    /**
     * 获取角色名称
     * @param roleId
     * @return
     */
    Role getRoleName(Integer roleId);
}
