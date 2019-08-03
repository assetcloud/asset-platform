package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.service.IResourceRoleService;
import com.asset.service.IRoleGroupService;
import com.asset.service.ISceneRelationService;
import com.asset.service.ISceneRoleService;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "scene/role")
@Api(value = "业务角色管理", tags = "业务角色")
public class SceneRoleController {

    ISceneRoleService sceneRoleService;

    IRoleGroupService roleGroupService;

    ISceneRelationService sceneRelationService;

    @ApiOperation(value = "获取所有业务角色", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "page", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "size", defaultValue = "20", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "sceneRole", required = true, dataTypeClass = SceneRole.class)
    })
    @RequestMapping(value = "list/all",method = RequestMethod.GET)
    public RespBean listAll(@RequestParam Integer page, @RequestParam Integer size, @RequestBody SceneRole sceneRole){
        PageHelper.startPage(page, size);
        List<SceneRole> roleGroups = sceneRoleService.listAll(sceneRole);
        return RespBean.data(new PageInfo<>(roleGroups));
    }

    @ApiOperation(value = "获取一个场景下的所有角色", notes = "已完成，根据角色组获取角色（树型结构）")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public RespBean rolesWithGroup(@ApiParam(value = "sceneId", required = true) @RequestParam String sceneId){
        return RespBean.data(sceneRoleService.rolesWithGroup(sceneId));
    }

    /**
     **
     * 新增角色
     * @param sceneRole
     * @return RespBean
     */
    @ApiOperation(value = "新增业务级角色", notes = "（已完成）传SceneRole实体类;roleNameZh角色中文名称;groupId所属角色组;sceneCode所属场景编号（以上变量必填）")
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
        sceneRole.setRoleName(SystemConstant.SCENE_NORMAL);
        sceneRole.setStatus(true);
        return RespBean.status(sceneRoleService.save(sceneRole));
    }

    @ApiOperation(value = "添加角色组", notes = "（已完成）传RoleGroup实体;roleGroupName角色组名称;sceneCode场景编号;（以上变量必填）")
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

    @ApiOperation(value = "删除角色组", notes = "（已完成）传入RoleGroup实体类数组;id:角色组id;必填")
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

    @ApiOperation(value = "修改角色组", notes = "（已完成）主要是修改角色组名称;传RoleGroup实体;id:角色组id;roleGroupName:角色组名称;以上必填")
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

    @ApiOperation(value = "角色转移",notes = "已完成,修改角色的角色组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "groupId", value = "新角色组id", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "sceneCode", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "role2Group",method = RequestMethod.PUT)
    public RespBean updateRoleInfo(@RequestBody SceneRole role){
        if (Func.hasEmpty(role.getId(), role.getGroupId(), role.getSceneCode())){
            return RespBean.paramError();
        }
        return RespBean.status(sceneRoleService.updateGroupInfo(role));
    }

    @ApiOperation(value = "添加角色成员",notes = "为特定角色添加成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true),
            @ApiImplicitParam(name = "userIds", value = "用户id数组，逗号隔开", required = true)
    })
    @RequestMapping(value = "member/add",method = RequestMethod.POST)
    public RespBean users2Role(@RequestParam Long roleId, @RequestParam String userIds){
        return RespBean.data(sceneRoleService.addUsers2Role(roleId, Func.toStrList(",", userIds)));
    }

    @ApiOperation(value = "角色检索",notes = "已完成，通过角色名称获取（模糊搜索）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleNameZh", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneCode", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/roleSearch",method = RequestMethod.GET)
    public RespBean roleSearch(@RequestBody SceneRole role){
        return RespBean.data(sceneRoleService.getRoleByName(role));
    }

    @ApiOperation(value = "删除角色成员",notes = "为特定角色批量删除成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id数组", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataTypeClass = Long.class)
    })
    @RequestMapping(value = "member/remove",method = RequestMethod.DELETE)
    public RespBean removeRoleMember(@NotEmpty @RequestParam String userIds, @NotEmpty @RequestParam Long roleId){
        return RespBean.status(sceneRelationService.removeBatch(roleId, Func.toStrList(",", userIds)));
    }

    @ApiOperation(value = "通过角色获取角色成员",notes = "（已完成）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @RequestMapping(value = "/users/{roleId}",method = RequestMethod.GET)
    public RespBean getUsersByRole(@PathVariable Long roleId){
        return RespBean.data(sceneRoleService.getUsersByRole(roleId));
    }

    @ApiOperation(value = "编辑业务角色权限",notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "resourceIds", value = "资源id的数组", required = true, dataType = "String")
    })
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @Transactional
    public RespBean grant(@RequestParam String resourceIds, @RequestParam Long roleId){
        return RespBean.status(sceneRoleService.grant(roleId, Func.toLongList(",", resourceIds)));
    }

    @ApiOperation(value = "获取场景中的角色（用于设置授权）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("authorities")
    public RespBean getMemberRole(@RequestParam String userId, @RequestParam String sceneId){
        HashMap<String, Object> resMap = new HashMap<>();
        List<SceneRole> allRoles = sceneRoleService.getRolesByScene(sceneId);
        List<SceneRole> rolesOwned = sceneRoleService.getRolesOwned(userId, sceneId);
        List<Long> roles = new ArrayList<>();
        rolesOwned.forEach(role -> roles.add(role.getId()));
        resMap.put("options", allRoles);
        resMap.put("values", roles.toArray());
        return RespBean.data(resMap);
    }

    @PostMapping("set/authority")
    @ApiOperation(value = "场景中设置用户角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleIds", value = "角色id的数组", required = true, dataTypeClass = String.class)
    })
    public RespBean setAuthority(@RequestParam String userId, @RequestParam String rids){
        return RespBean.status(sceneRoleService.setAuthority(userId, Func.toLongList(",", rids)));
    }
}
