package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.service.IRoleGroupService;
import com.asset.service.ISceneRoleService;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "scene/role")
public class SceneRoleController {

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private IRoleGroupService roleGroupService;

    @ApiOperation(value = "获取所有业务角色信息", notes = "获取所有业务角色信息")
    @GetMapping("list")
    public RespBean getAllRoles(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam(value = "page") Integer page
            , @RequestParam(value = "size", defaultValue = "20", required = true) Integer size){
        PageHelper.startPage(page, size);
        List<SceneRole> sceneRoleList = sceneRoleService.getAllRoles();
        if (Func.isNull(sceneRoleList)){
            return RespBean.ok(SystemConstant.SCENE_NOT_FOUND);
        }
        PageInfo<SceneRole> pageInfo = new PageInfo<>(sceneRoleList);
        return RespBean.ok("", pageInfo);
    }

    @ApiOperation(value = "获取一个场景下的所有角色", notes = "根据角色组获取角色（树型结构）",tags = "角色", httpMethod = "GET")
    @RequestMapping(value = "roles/group",method = RequestMethod.GET)
    public RespBean rolesWithGroup(){
        return RespBean.data(sceneRoleService.rolesWithGroup());
    }

    /**
     **
     * 新增角色
     * @param sceneRole
     * @return RespBean
     */
    @ApiOperation(value = "新增业务级角色", notes = "传SceneRole实体类;roleNameZh角色中文名称;groupId所属角色组;sceneCode所属场景编号（以上变量必填）")
    @PostMapping(value = "add")
    public RespBean addRole(@RequestBody SceneRole sceneRole) {
        if (Func.hasEmpty(sceneRole.getRoleNameZh(), sceneRole.getGroupId(), sceneRole.getSceneCode())){
            return RespBean.error("传入参数错误");
        }
        if (sceneRoleService.roleExist(sceneRole)){
            return RespBean.error("角色名称已被使用，请更换后重试");
        }
        sceneRole.setCreatedTime(new Date());
        sceneRole.setEnableTime(new Date());
        sceneRole.setRoleDefault(0);
        //TODO:是否需要管理英文角色名
        sceneRole.setRoleName("ROLE_NORMAL");
        sceneRole.setStatus(true);
        return RespBean.status(sceneRoleService.save(sceneRole));
    }

    @ApiOperation(value = "添加角色组", notes = "传RoleGroup实体;roleGroupName角色组名称;sceneCode场景编号;（以上变量必填）",tags = "业务角色", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleGroupName", value = "角色组名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneCode", value = "场景编号", required = true, dataType = "String")
    })
    @PostMapping(value = "group/add")
    public RespBean addGroup(@RequestBody RoleGroup roleGroup){
        if (Func.hasEmpty(roleGroup.getRoleGroupName(), roleGroup.getSceneCode())){
            return RespBean.error("传入参数错误");
        }
        if (sceneRoleService.roleGroupExist(roleGroup)){
            return RespBean.error("角色组名称已被使用，请更换后重试");
        }
        roleGroup.setAddTime(new Date());
        roleGroup.setIsDeleted(0);
        return RespBean.status(roleGroupService.save(roleGroup));
    }

    @ApiOperation(value = "删除角色组", notes = "传入RoleGroup实体类数组;id:角色组id;必填",tags = "角色", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleGroupList", value = "角色组的id数组", required = true)
    })
    @DeleteMapping(value = "group/remove")
    public RespBean deleteGroup(@RequestBody List<RoleGroup> roleGroupList){
        for (RoleGroup roleGroup : roleGroupList) {
            if (Func.hasEmpty(roleGroup.getId())){
                return RespBean.error("参数错误");
            }
            if (sceneRoleService.rolesInGroup(roleGroup.getId())){
                return RespBean.error(String.format("角色组:%s 下还有角色存在，请转移后重试"
                        , roleGroupService.getById(roleGroup.getId()).getRoleGroupName()));
            }
            roleGroup.setIsDeleted(1);
        }
        return RespBean.status(roleGroupService.updateBatchById(roleGroupList));
    }

    @ApiOperation(value = "修改角色组", notes = "主要是修改角色组名称;传RoleGroup实体;id:角色组id;roleGroupName:角色组名称;以上必填",tags = "角色", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色组id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "roleGroupName", value = "新角色组名称", required = true, dataType = "String")
    })
    @PutMapping(value = "group/edit")
    public RespBean modifyGroup(@RequestBody RoleGroup roleGroup){
        if (Func.hasEmpty(roleGroup.getId())){
            return RespBean.error("参数错误");
        }
        return RespBean.status(roleGroupService.updateById(roleGroup));
    }

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
//    public RespBean role2Group(@RequestBody Role role){
//        int flag = roleService.addRole2Group(role.getId(), role.getGroupId());
//        if(flag < 0){
//            RespBean.error("系统错误");
//        }
//        return RespBean.ok("角色转移成功");
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
//    public RespBean roleSearch(@RequestBody Role role){
//        Role target = roleService.getRoleByName(role.getRoleNameZh());
//        LOGGER.info(role.getRoleNameZh());
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
