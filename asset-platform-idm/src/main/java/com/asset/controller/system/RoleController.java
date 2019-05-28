
package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Role;
import com.asset.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by hjhu on 2019/5/28.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     **
     * 新增角色
     * @param roleName
     * @param status
     * @return RespBean
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public RespBean addRole(@RequestParam("roleName") String roleName,
                            @RequestParam("roleNameZh") String roleNameZh,
                            @RequestParam("status") int status) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setRoleNameZh(roleNameZh);
        role.setCreatedTime(new Date());
        role.setEnableTime(new Date());
        role.setRoleDescription("测试角色");
        if(status == 1){
            role.setStatus(true);
        }
        LOGGER.info("新增角色: {}", role.getRoleName());
        int flag = roleService.addRole(role);
        if (flag == 1) {
            return RespBean.ok("角色添加成功!");
        }
        return RespBean.error("角色添加失败!");
    }

    /**
     * 返回所有角色信息（除管理员信息外）
     * @return List<Role>
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public List<Role> getRoleList(){
        return roleService.roles();
    }

}

