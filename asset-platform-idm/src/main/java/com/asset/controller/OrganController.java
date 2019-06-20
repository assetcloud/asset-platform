package com.asset.controller;

import com.asset.bean.OrganTree;
import com.asset.bean.RespBean;
import com.asset.bean.Role;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.OrganService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/organ")
public class OrganController {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrganController.class);

    @Autowired
    OrganService organService;

    @ApiOperation(value = "添加组织节点", notes = "添加组织节点",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为0", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public RespBean addNode(@RequestBody OrganTree organTree){
        int flag = organService.addNode(organTree);
        if (flag == SystemConstant.NODE_ALREADY_EXISTS){
            return RespBean.error(SystemConstant.ADD_FAILURE,"NODE_ALREADY_EXISTS");
        } else if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE,"SYSTEM_ERROR");
        }
        return RespBean.ok(SystemConstant.ADD_SUCCESS);
    }

    @ApiOperation(value = "删除组织节点", notes = "删除组织节点",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.DELETE)
    public RespBean deleteNode(@RequestBody OrganTree organTree){
        int flag = organService.deleteNode(organTree);
        if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error(SystemConstant.DELETE_FAILURE,"SYSTEM_ERROR");
        }
        return RespBean.ok(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "获取单个组织节点", notes = "获取单个组织节点",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public RespBean getNode(@PathVariable String id){
        OrganTree node = organService.getNode(id);
        if (node == null){
            return RespBean.error(SystemConstant.GET_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, node);
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return RespBean
     */
    @ApiOperation(value = "获取主组织树", notes = "获取主组织树（仅限管理员访问）",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/mainTree", method = RequestMethod.GET)
    public RespBean getOrganMainTree(){
        List<Role> roles = UserUtils.getCurrentUser().getRoles();
        for (Role role : roles) {
            LOGGER.info(role.toString());
            if (role.getId().equals(SystemConstant.ADMIN_ROLE_ID)) {
                //TODO:后续可以交由spring security处理
                // an administrator has access to main trees
                List<OrganTree> mainTrees = organService.getMainTree();
                return RespBean.ok(SystemConstant.GET_SUCCESS, mainTrees);
            }
        }
        return RespBean.error(SystemConstant.GET_FAILURE, new ArrayList<>());
    }
}
