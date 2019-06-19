package com.asset.controller;

import com.asset.bean.OrganTree;
import com.asset.bean.RespBean;
import com.asset.common.SystemConstant;
import com.asset.service.OrganService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/organ")
public class OrganController {

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
            return RespBean.error("添加失败","NODE_ALREADY_EXISTS");
        } else if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error("系统错误","SYSTEM_ERROR");
        }
        return RespBean.ok("添加成功");
    }

    @ApiOperation(value = "删除组织节点", notes = "删除组织节点",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.DELETE)
    public RespBean deleteNode(@RequestBody OrganTree organTree){
        int flag = organService.deleteNode(organTree);
        if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error("删除失败","SYSTEM_ERROR");
        }
        return RespBean.ok("删除成功");
    }

    @ApiOperation(value = "获取单个组织节点", notes = "获取单个组织节点",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public OrganTree getNode(@PathVariable String id){
        return organService.getNode(id);
    }
}
