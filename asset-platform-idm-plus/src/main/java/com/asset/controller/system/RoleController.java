
package com.asset.controller.system;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.service.IRoleService;
import com.asset.service.ISceneRoleService;
import com.asset.service.impl.SceneRoleServiceImpl;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
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
@RequestMapping(value = "scene/role")
public class RoleController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

//    /**
//     **
//     * 新增角色
//     * @param roleName
//     * @param status
//     * @return RespBean
//     */
//    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
//    public RespBean addRole(@RequestParam("roleName") String roleName,
//                            @RequestParam("roleNameZh") String roleNameZh,
//                            @RequestParam("status") int status) {
//        SceneRole roleOld = new SceneRole();
//        roleOld.setRoleName(roleName);
//        roleOld.setRoleNameZh(roleNameZh);
//        roleOld.setCreatedTime(new Date());
//        roleOld.setEnableTime(new Date());
//        roleOld.setRoleDescription("测试角色");
//        if(status == 1){
//            roleOld.setStatus(true);
//        }
//        LOGGER.info("新增角色: {}", roleOld.getRoleName());
//        int flag = roleService.addRole(roleOld);
//        if (flag == 1) {
//            return RespBean.ok("角色添加成功!");
//        }
//        return RespBean.error("角色添加失败!");
//    }
//
//    @ApiOperation(value = "添加角色组", notes = "新增角色组",tags = "角色", httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleGroupName", value = "角色组名称", required = true, dataType = "String")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "添加成功",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "roleGroup",method = RequestMethod.POST)
//    public RespBean addGroup(@RequestBody RoleGroup roleGroup){
//        int flag = roleService.addRoleGroup(roleGroup);
//        if (flag == -2){
//            return RespBean.error("角色组已存在");
//        } else if (flag < 0){
//            return RespBean.error("系统错误");
//        } else {
//            return RespBean.ok("添加成功");
//        }
//    }
//
//    @ApiOperation(value = "删除角色组", notes = "删除角色组",tags = "角色", httpMethod = "DELETE")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "角色组id", required = true, dataType = "Long")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "已删除",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "roleGroup",method = RequestMethod.DELETE)
//    public RespBean deleteGroup(@RequestBody RoleGroup roleGroup){
//        int flag = roleService.deleteGroup(roleGroup.getId());
//        if(flag < 0){
//            return RespBean.error("系统错误");
//        }
//        return RespBean.ok("已删除");
//    }
//
//    @ApiOperation(value = "修改角色组", notes = "修改角色组名称",tags = "角色", httpMethod = "PUT")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "角色组id", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "roleGroupName", value = "新角色组名称", required = true, dataType = "String")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "已删除",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "roleGroup",method = RequestMethod.PUT)
//    public RespBean modifyGroup(@RequestBody RoleGroup roleGroup){
//        int flag = roleService.modifyGroup(roleGroup);
//        if(flag < 0){
//            return RespBean.error("系统错误");
//        }
//        return RespBean.ok("修改成功");
//    }
//
//    @ApiOperation(value = "角色转移",notes = "修改角色的角色组", tags = "角色", httpMethod = "PUT")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "groupId", value = "新角色组id", required = true, dataType = "Integer")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "角色转移成功",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "role2Group",method = RequestMethod.PUT)
//    public RespBean role2Group(@RequestBody SceneRole roleOld){
//        int flag = roleService.addRole2Group(roleOld.getId(), roleOld.getGroupId());
//        if(flag < 0){
//            RespBean.error("系统错误");
//        }
//        return RespBean.ok("角色转移成功");
//    }
//
//    @ApiOperation(value = "获取角色", notes = "根据角色组获取角色（树型结构）",tags = "角色", httpMethod = "GET")
//    @RequestMapping(value = "roles",method = RequestMethod.GET)
//    public List<RoleGroup> roles(){
//        return roleService.rolesWithGroup();
//    }
//
//    /**
//     * 返回所有角色信息（除管理员信息外）
//     * @return List<SceneRole>
//     */
//    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
//    public List<SceneRole> getRoleList(){
//        return roleService.roles();
//    }
//
//    @ApiOperation(value = "添加角色成员",notes = "为特定角色添加成员", tags = "角色", httpMethod = "POST")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userRoleList", value = "用户id与角色id的json数组", required = true, example = "123")
//    })
//    @RequestMapping(value = "/userToRole",method = RequestMethod.POST)
//    public RespBean users2Role(@RequestBody List<UserRole> userRoleList){
//        int flag = roleService.addUsers2Role(userRoleList);
//        if (flag < 0){
//            return RespBean.error("添加失败");
//        }
//        return RespBean.ok("添加成功");
//    }
//
//    @ApiOperation(value = "角色检索",notes = "通过角色名称获取（模糊搜索）", tags = "角色", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleNameZh", value = "角色名称", required = true, dataType = "String")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "检索成功",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "/roleSearch",method = RequestMethod.GET)
//    public RespBean roleSearch(@RequestBody SceneRole roleOld){
//        SceneRole target = roleService.getRoleByName(roleOld.getRoleNameZh());
//        LOGGER.info(roleOld.getRoleNameZh());
//        if (target == null){
//            return RespBean.error(SystemConstant.ROLE_NOT_FOUND);
//        }
//        return RespBean.ok(SystemConstant.GET_SUCCESS, target);
//    }
//
//    @ApiOperation(value = "删除角色成员",notes = "为特定角色批量删除成员", tags = "角色", httpMethod = "DELETE")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userRoleList", value = "用户角色列表", required = true, dataType = "List<UserRole>")
//    })
//    @RequestMapping(value = "/remove",method = RequestMethod.DELETE)
//    public RespBean removeRoleMember(@RequestBody List<UserRole> userRoleList){
//        int flag = roleService.batchDelete(userRoleList);
//        if (flag < 0){
//            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
//        }
//        return RespBean.ok(SystemConstant.DELETE_SUCCESS);
//    }
//
//    @RequestMapping(value = "/rights", method = RequestMethod.POST)
//    public RespBean saveRight(){
//        return RespBean.ok(SystemConstant.ADD_SUCCESS);
//    }
//
//    @ApiOperation(value = "获取角色成员",notes = "通过角色获取角色成员", tags = "角色", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
//    })
//    @ApiResponses({
//            @ApiResponse(code = 200,message = "获取成功",response = RespBean.class),
//            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
//    })
//    @RequestMapping(value = "/users/{roleId}",method = RequestMethod.GET)
//    public RespBean getUsersByRole(@PathVariable Long roleId){
//        List<User> users = roleService.getUsersByRole(roleId);
//        if (users.size() == 0){
//            return RespBean.ok(SystemConstant.USERS_NOT_FOUND);
//        }
//        return RespBean.ok(SystemConstant.GET_SUCCESS, users);
//    }
}
