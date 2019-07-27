package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.common.SystemConstant;
import com.asset.service.*;
import com.asset.utils.CommonUtils;
import com.asset.utils.Email;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("scene")
public class SceneController {

    @Autowired
    private IOrganService organService;

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private IOrganSceneService organSceneService;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private IUserSceneService userSceneService;

    @Autowired
    private IMailService mailService;

    @ApiOperation(value = "获取所有场景信息", notes = "场景",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/sceneList", method = RequestMethod.GET)
    public RespBean getAllScenes(){
        List<Scene> allScene = sceneService.getAllScene();
        if (allScene == null){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, allScene);
    }

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
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String")
    })
    @PostMapping("members")
    public RespBean addMembers(@RequestParam String userIds, @RequestParam("sceneId") String sceneId){
        if (Func.isNull(sceneId)){
            return RespBean.paramError();
        }
        //获取该场景下的默认角色
        SceneRole defaultRole = sceneRoleService.getDefaultRole(sceneId);
        return RespBean.status(sceneService.addSceneMembers(Func.toStrList(",", userIds)
                , sceneId, defaultRole.getId()));
    }

    @ApiOperation(value = "场景中批量删除用户", notes = "/",tags = "场景", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @DeleteMapping("members")
    public RespBean removeMembers(@RequestParam String userIds, @RequestParam String sceneId){
        if (Func.hasEmpty(userIds, sceneId)){
            return RespBean.paramError();
        }
        return RespBean.data(organSceneService.batchRemoveMembers(Func.toStrList(",", userIds), sceneId));
    }

    @ApiOperation(value = "场景中设置用户主部门", notes = "已完成",tags = "场景", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("node/set")
    public RespBean setMainNode(@RequestParam String userId, @RequestParam String sceneId, @RequestParam String nodeId){
        if (Func.hasEmpty(userId, sceneId, nodeId)){
            return RespBean.paramError();
        }
        return RespBean.status(userSceneService.updateNodeIdByUserId(userId, sceneId, nodeId));
    }

    @ApiOperation(value = "场景中设置部门负责人", notes = "已完成",tags = "场景", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PutMapping("principal/set")
    @Transactional
    public RespBean setPrincipal(@RequestParam String userId, @RequestParam String sceneId, @RequestParam String nodeId){
        if (Func.hasEmpty(userId, sceneId, nodeId)){
            return RespBean.paramError();
        }
        return RespBean.status(userSceneService.updatePrincipalByUserId(userId, sceneId, nodeId));
    }

    @ApiOperation(value = "向场景中新增组织部门", notes = "已完成",tags = "场景", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "treeIds", value = "部门节点的集合", required = true, dataTypeClass = List.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/node/add", method = RequestMethod.POST)
    public RespBean changeOrganTree(@RequestParam String treeIds, @RequestParam("sceneId") String sceneId){
        List<OrganTree> treeList = (List<OrganTree>) organService.listByIds(Func.toStrList(",", treeIds));
        ArrayList<OrganScene> organScenes = new ArrayList<>();
        for (OrganTree treeNode : treeList) {
            OrganScene node = CommonUtils.NodeTransformer(treeNode);
            node.setSceneId(sceneId);
            organScenes.add(node);
        }
        return RespBean.data(organSceneService.saveBatch(organScenes));
    }

    @ApiOperation(value = "批量删除场景中的组织部门", notes = "（完成对叶子节点的删除）",tags = "场景", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeIds", value = "部门id的集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/node/remove", method = RequestMethod.DELETE)
    public RespBean removeNodes(@RequestParam String nodeIds, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(nodeIds)){
            return RespBean.paramError();
        }
        return RespBean.data(organSceneService.batchRemove(Func.toStrList(",", nodeIds), sceneId));
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
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @GetMapping("node/users")
    public RespBean getUsersByNode(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId){
        return RespBean.data(userSceneService.getNodeUsers(sceneId, nodeId));
    }

    @ApiOperation(value = "检索场景中的组织部门", notes = "已完成",tags = "场景", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/node/search", method = RequestMethod.GET)
    public RespBean searchSceneNode(@RequestParam("keyword") String keyword, @RequestParam("sceneId") String sceneId){
        return RespBean.data(sceneService.getNodesByNameAlike(keyword, sceneId));
    }

    @ApiOperation(value = "通过邮件邀请用户接入场景", notes = "/",tags = "场景", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("invite/member")
    public RespBean inviteMember(@RequestParam String userId) {
        //TODO:邮件单独做成一个服务，同时需要整合redis和mq
        Email email = new Email();
        email.setEmail(Func.toStrArray(",", "1036514689@qq.com"));
        email.setContent("资产云开发协同中心");
        email.setSubject("尊敬的用户您好");
        mailService.send(email);
        return RespBean.data("发送成功");
    }

    @ApiOperation(value = "获取场景中成员的已有角色", notes = "已完成",tags = "场景", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/owned")
    public RespBean getMemberRole(@RequestParam String userId, @RequestParam String sceneId){
        return RespBean.data(userSceneService.rolesOwned(userId, sceneId));
    }

    @ApiOperation(value = "获取场景中当前成员未拥有的角色", notes = "已完成，未拥有的角色其checked值为0",tags = "场景", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/invert")
    public RespBean getMemberRoleInvert(@RequestParam String userId, @RequestParam String sceneId){
        return RespBean.data(userSceneService.rolesChecked(userId, sceneId));
    }
}
