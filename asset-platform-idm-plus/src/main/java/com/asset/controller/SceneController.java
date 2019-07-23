package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.service.IOrganService;
import com.asset.service.ISceneRoleService;
import com.asset.service.ISceneService;
import com.asset.service.IUserSceneService;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("scene")
public class SceneController {

    @Autowired
    private IOrganService organService;

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private IUserSceneService userSceneService;

    @ApiOperation(value = "通过场景获取所有所属用户", notes = "已完成",tags = "场景", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("members")
    public RespBean getUsersUnderScene(@RequestParam("sceneId")String sceneId){
        return RespBean.data(organService.getUsersByScene(sceneId));
    }



    @ApiOperation(value = "向场景中增加用户（批量）", notes = "",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(实体)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String")
    })
    @PostMapping("addUsers")
    public RespBean addUsersToScene(@RequestBody List<UserScene> users, @RequestParam("sceneId") String sceneId){
        if (Func.isNull(sceneId)){
            return RespBean.paramError();
        }
        //获取该场景下的默认角色
        SceneRole defaultRole = sceneRoleService.getDefaultRole(sceneId);
        for (UserScene userScene : users) {
            if (Func.hasEmpty(userScene.getUserId())){
                return RespBean.paramError();
            }
            userScene.setSceneId(sceneId);
            userScene.setStatus(1);
            //设置为默认角色
            userScene.setRoleId(defaultRole.getId());
        }
        return RespBean.data(userSceneService.insertBatch(users));
    }

    @ApiOperation(value = "向场景中新增组织部门", notes = "",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organScenes", value = "场景-组织的对应数据", required = true, dataType = "List<OrganScene>")
    })
    @RequestMapping(value = "/node/add", method = RequestMethod.POST)
    public RespBean changeOrganTree(@RequestBody List<OrganScene> organSceneList){
        //TODO:编辑组织树，存在则不更新，不存在则插入
        return RespBean.ok("ok");
    }

    @ApiOperation(value = "获取用户场景", notes = "根据用户获取场景;page起始页;size每页数据量", tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/list/byUser", method = RequestMethod.GET)
    public RespBean getUserScenes(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam(defaultValue = "1") Integer page
            , @ApiParam(value = "size", defaultValue = "10", required = true) @RequestParam(defaultValue = "10") Integer size
            , @ApiParam(value = "userId", required = true) @RequestParam String userId){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getScenesByUser(userId);
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return RespBean.data(scenePageInfo);
    }

    @ApiOperation(value = "为用户装载工作场景", notes = "已完成", tags = "用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loadScene", method = RequestMethod.POST)
    public RespBean setScene(@RequestParam("userId") String userId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, sceneId)){
            return RespBean.paramError();
        }
        if (!sceneService.hasScene(userId, sceneId)){
            return RespBean.error("工作场景加载失败");
        }
        GlobalConstant.put(userId, sceneId);
        return RespBean.ok("工作场景加载成功");
    }

    @ApiOperation(value = "场景中通过组织部门获取所属用户", notes = "已完成",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "nodeId", value = "单据id", required = true, dataTypeClass = java.lang.Long.class)
    })
    @GetMapping("node/users")
    public RespBean getUsersByNode(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") Long nodeId){

        return null;
    }
}
