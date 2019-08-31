package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.common.SystemConstant;
import com.asset.service.*;
import com.asset.utils.CommonUtils;
import com.asset.utils.Email;
import com.asset.utils.Func;
import com.asset.wrapper.SceneWrapper;
import com.asset.wrapper.UserWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("scene")
@Api(value = "场景管理", tags = "场景管理")
public class SceneController {

    IOrganService organService;

    ISceneService sceneService;

    IOrganSceneService organSceneService;

    ISceneRoleService sceneRoleService;

    IUserSceneService userSceneService;

    IMailService mailService;

    ISceneRelationService sceneRelationService;

    IUserService userService;

    IRoleService roleService;

    IDictService dictService;

    @ApiOperation(value = "获取所有场景信息", notes = "已完成")
    @RequestMapping(value = "/sceneList", method = RequestMethod.GET)
    public RespBean getAllScenes(){
        List<Scene> allScene = sceneService.getAllScene();
        SceneWrapper sceneWrapper = new SceneWrapper(sceneService);
        return RespBean.ok(SystemConstant.GET_SUCCESS, sceneWrapper.listNodeVO(allScene));
    }

    @PostMapping("remove")
    @ApiOperation(value = "删除场景", notes = "已完成")
    public R removeScene(@ApiParam(value = "sceneId", required = true) @RequestParam String sceneId){
        return R.status(sceneService.removeScene(sceneId));
    }

    @ApiOperation(value = "获取用户尚未拥有的场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "page", name = "起始页", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "size", name = "每页数据量", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "userId", name = "用户id", required = true,  dataTypeClass = String.class),
            @ApiImplicitParam(value = "sceneName", name = "场景名称", required = true, dataTypeClass = String.class)
    })
    @GetMapping("list/invert")
    public RespBean getUserScenesInvert(@RequestParam Integer page, @RequestParam Integer size
            , @RequestParam String userId, @RequestParam String sceneName){
        PageHelper.startPage(page, size);
        List<Scene> sceneInvert = sceneService.getSceneInvert(userId, sceneName);
        return RespBean.data(new PageInfo<>(sceneInvert));
    }

    @ApiOperation(value = "通过场景获取所有所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("members")
    public RespBean getUsersUnderScene(@RequestParam("sceneId")String sceneId){
        return RespBean.data(organService.getUsersByScene(sceneId));
    }

    @ApiOperation(value = "向场景中增加用户（批量）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String")
    })
    @PostMapping("members")
    public RespBean addMembers(@RequestParam String userIds, @RequestParam("sceneId") String sceneId){
        if (Func.isNull(sceneId)){
            return RespBean.paramError();
        }
        List<String> ids = Func.toStrList(",", userIds);
        //获取该场景下的默认角色
        SceneRole defaultRole = sceneRoleService.getDefaultRole(sceneId);
        sceneRelationService.saveBatch(defaultRole.getId(), ids);
        return RespBean.status(sceneService.addSceneMembers(ids, sceneId));
    }

    @ApiOperation(value = "场景中批量删除用户", notes = "已完成")
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

    @ApiOperation(value = "场景中设置用户主部门", notes = "已完成")
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

    @ApiOperation(value = "场景中设置部门负责人", notes = "已完成")
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

    @ApiOperation(value = "向场景中新增组织部门", notes = "已完成")
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

    @ApiOperation(value = "批量删除场景中的组织部门", notes = "（完成对叶子节点的删除）")
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

    @ApiOperation(value = "获取用户场景", notes = "根据用户获取场景;page起始页;size每页数据量")
    @RequestMapping(value = "/list/byUser", method = RequestMethod.GET)
    public RespBean getUserScenes(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam Integer page
            , @ApiParam(value = "size", defaultValue = "10", required = true) @RequestParam Integer size
            , @ApiParam(value = "userId", required = true) @RequestParam String userId){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getScenesByUser(userId);
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return RespBean.data(scenePageInfo);
    }

    @ApiOperation(value = "为用户装载工作场景", notes = "已完成")
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

    @ApiOperation(value = "场景中通过组织部门获取所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "memberName", value = "用户姓名", required = true, dataTypeClass = String.class)
    })
    @GetMapping("node/members/list")
    public R getUsersByNode(@RequestParam("page") Integer page, @RequestParam("size") Integer size
            , @RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId
            , @RequestParam("memberName") String memberName){
        PageHelper.startPage(page, size);
        List<User> nodeUsers = userSceneService.getNodeUsers(sceneId, nodeId, memberName);
        UserWrapper userWrapper = new UserWrapper(userService, dictService, roleService);
        return R.data(new PageInfo<>(userWrapper.listNodeVO(nodeUsers)));
    }

    @ApiOperation(value = "场景中通过组织部门获取所属用户（无分页）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @GetMapping("node/members")
    public RespBean getUsersByNode2(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId){
        return RespBean.data(userSceneService.getNodeUsers(sceneId, nodeId, ""));
    }

    @ApiOperation(value = "检索场景中的组织部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @RequestMapping(value = "/node/search", method = RequestMethod.GET)
    public RespBean searchSceneNode(@RequestParam("keyword") String keyword, @RequestParam("sceneId") String sceneId){
        return RespBean.data(sceneService.getNodesByNameAlike(keyword, sceneId));
    }

    @ApiOperation(value = "通过邮件邀请用户接入场景", notes = "未完成")
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

    @ApiOperation(value = "获取场景中成员的已有角色", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/owned")
    public RespBean getMemberRole(@RequestParam String userId, @RequestParam String sceneId){
        return RespBean.data(userSceneService.rolesOwned(userId, sceneId));
    }

    @ApiOperation(value = "获取场景中当前成员未拥有的角色", notes = "已完成，未拥有的角色其checked值为0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/invert")
    public RespBean getMemberRoleInvert(@RequestParam String userId, @RequestParam String sceneId){
        return RespBean.data(userSceneService.rolesChecked(userId, sceneId));
    }

    @ApiOperation(value = "用户请求绑定其它场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneIds", value = "场景id数组", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("bind/another")
    @Transactional
    public RespBean bindScene(@RequestParam(value = "sceneId") String sceneIds
            , @RequestParam(value = "userId") String userId){
        Map<String, String> jsonMap = new HashMap<>();
        if (Func.hasEmpty(sceneIds)) {
            return RespBean.paramError();
        }
        //绑定场景与用户
        sceneService.userSceneBind(Func.toStrList(",", sceneIds), userId);
        jsonMap.put("userId", userId);
        jsonMap.put("sceneIds", sceneIds);
        return RespBean.data(jsonMap);
    }

    @ApiOperation(value = "解析用户集合", notes = "/流程绑定经办人时,将组织和角色解析至用户级别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeIds", value = "部门id集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "userIds", value = "用户id集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)

    })
    @GetMapping("user/specify")
    public R<List<String>> userSpecify(@RequestParam String nodeIds, @RequestParam String roleIds
            , @RequestParam String userIds, @RequestParam String sceneId){
        List<String> userList = new ArrayList<>(new LinkedHashSet<>());
        if (Func.hasEmpty(userIds, sceneId)){
            return R.fail("");
        }
        if (!Func.hasEmpty(nodeIds)){
            List<String> users = userSceneService.getNodeUsers(sceneId, Func.toStrList(",", nodeIds));
            userList.addAll(users);
        }
        if (!Func.hasEmpty(roleIds)){
            List<String> users = sceneRoleService.getUsersByRoles(Func.toLongList(",", roleIds));
            userList.addAll(users);
        }
        userList.addAll(Func.toStrList(",", userIds));
        return R.data(userList);
    }
}
