
package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Role;
import com.asset.bean.RoleGroup;
import com.asset.bean.UserRole;
import com.asset.service.RoleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "添加角色组", notes = "新增角色组",tags = "角色", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleGroupName", value = "角色组名称", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "roleGroup",method = RequestMethod.POST)
    public RespBean addGroup(@RequestBody RoleGroup roleGroup){
        int flag = roleService.addRoleGroup(roleGroup);
        if (flag == -2){
            return RespBean.error("角色组已存在");
        } else if (flag < 0){
            return RespBean.error("系统错误");
        } else {
            return RespBean.ok("添加成功");
        }
    }

    @ApiOperation(value = "删除角色组", notes = "删除角色组",tags = "角色", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色组id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "roleGroupName", value = "角色组名称", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "已删除",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "roleGroup",method = RequestMethod.DELETE)
    public RespBean deleteGroup(@RequestBody RoleGroup roleGroup){
        int flag = roleService.deleteGroup(roleGroup.getId());
        if(flag < 0){
            return RespBean.error("系统错误");
        }
        return RespBean.ok("已删除");
    }

    @ApiOperation(value = "修改角色组", notes = "修改角色组名称",tags = "角色", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色组id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "roleGroupName", value = "新角色组名称", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "已删除",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "roleGroup",method = RequestMethod.PUT)
    public RespBean modifyGroup(@RequestBody RoleGroup roleGroup){
        int flag = roleService.modifyGroup(roleGroup);
        if(flag < 0){
            return RespBean.error("系统错误");
        }
        return RespBean.ok("修改成功");
    }

    @ApiOperation(value = "角色组转移",notes = "修改角色的角色组", tags = "角色", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "groupId", value = "新角色组id", required = true, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "角色转移成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "role2Group",method = RequestMethod.PUT)
    public RespBean role2Group(@RequestBody Role role){
        int flag = roleService.addRole2Group(role.getId(), role.getGroupId());
        if(flag < 0){
            RespBean.error("系统错误");
        }
        return RespBean.ok("角色转移成功");
    }

    @ApiOperation(value = "获取角色", notes = "根据角色组获取角色（树型结构）",tags = "角色", httpMethod = "GET")
    @RequestMapping(value = "roles",method = RequestMethod.GET)
    public List<RoleGroup> roles(){
        return roleService.rolesWithGroup();
    }

    /**
     * 返回所有角色信息（除管理员信息外）
     * @return List<Role>
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public List<Role> getRoleList(){
        return roleService.roles();
    }

    @ApiOperation(value = "添加角色成员",notes = "为特定角色添加成员", tags = "角色", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rid", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "users[]", value = "用户id数组", required = true, dataType = "String")
    })
    @RequestMapping(value = "/userToRole",method = RequestMethod.POST)
    /*public RespBean users2Role(@RequestParam(value = "users[]") String[] users, @RequestParam(value = "rid") Long rid){
        int flag = roleService.addUsers2Role(rid, users);
        if (flag < 0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功");
    }*/
    public RespBean users2Role(@RequestBody List<UserRole> userRoleList){
        int flag = roleService.addUsers2Role(userRoleList);
        if (flag < 0){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功");
    }
}

