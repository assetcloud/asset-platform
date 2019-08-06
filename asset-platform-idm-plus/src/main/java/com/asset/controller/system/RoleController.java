
package com.asset.controller.system;

import com.asset.bean.RespBean;
import com.asset.bean.Role;
import com.asset.service.IRoleService;
import com.asset.utils.Func;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hjhu on 2019/5/28.
 */
@RestController
@RequestMapping(value = "sys/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("list")
    @ApiOperation(value = "平台角色列表", notes = "已完成", tags = "平台角色")
    public RespBean roleList(){
        return RespBean.data(roleService.getAll());
    }

    @GetMapping("detail")
    @ApiOperation(value = "获取平台角色详细信息", notes = "（已完成）传入Role实体;id必填", tags = "平台角色")
    public RespBean getRoleDetail(@RequestBody Role role){
        return RespBean.data(roleService.getOne(new QueryWrapper<>(role)));
    }

    @PostMapping("submit")
    @ApiOperation(value = "新增角色信息", notes = "（已完成）传入Role实体;id必填", tags = "平台角色")
    public RespBean addRole(@RequestBody Role role){
        if (roleService.roleExists(role)){
            return RespBean.error("角色名称已存在，请更换后重试");
        }
        return RespBean.status(roleService.save(role));
    }

    @PutMapping("edit")
    @ApiOperation(value = "修改角色信息", notes = "传入Role实体;id必填", tags = "平台角色")
    public RespBean UpdateRole(@RequestBody Role role){
        //TODO:更新前判断名称是否重复
        return RespBean.status(roleService.updateById(role));
    }

    @PostMapping("remove")
    @ApiOperation(value = "删除", notes = "（已完成）传入ids")
    public RespBean remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return RespBean.status(roleService.batchDelete(Func.toLongList(",", ids)));
    }

    @PostMapping("grant")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "menuIds", value = "菜单id的数组", required = true, dataType = "String")
    })
    @ApiOperation(value = "编辑平台角色权限", notes = "已完成", tags = "平台角色")
    public RespBean grant(@RequestParam Long roleId, @RequestParam String menuIds){
        return RespBean.status(roleService.grant(roleId, Func.toLongList(",", menuIds)));
    }
}
