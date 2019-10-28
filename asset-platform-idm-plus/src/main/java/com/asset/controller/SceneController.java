package com.asset.controller;

import com.asset.bean.OrganScene;
import com.asset.bean.Scene;
import com.asset.bean.SceneRole;
import com.asset.bean.UserScene;
import com.asset.common.GlobalConstant;
import com.asset.service.*;
import com.asset.utils.Func;
import com.asset.vo.UserVO;
import com.asset.wrapper.SceneWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
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
    public R getAllScenes(){
        List<Scene> allScene = sceneService.getAllScene();
        SceneWrapper sceneWrapper = new SceneWrapper(sceneService);
        return R.data(sceneWrapper.listNodeVO(allScene));
    }

    @PostMapping("remove")
    @ApiOperation(value = "删除场景", notes = "已完成")
    public R removeScene(@ApiParam(value = "sceneId", required = true) @RequestParam String sceneId){
        return R.status(sceneService.removeById(sceneId));
    }

    @PostMapping("edit")
    @ApiOperation(value = "编辑场景名称", notes = "已完成")
    public R edit(@ApiParam(value = "sceneId", required = true, name = "场景id") @RequestBody Scene scene){
        return R.status(sceneService.updateById(scene));
    }

    @ApiOperation(value = "获取用户尚未拥有的场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "page", name = "起始页", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "size", name = "每页数据量", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "userId", name = "用户id", required = true,  dataTypeClass = String.class),
            @ApiImplicitParam(value = "sceneName", name = "场景名称", required = true, dataTypeClass = String.class)
    })
    @GetMapping("list/invert")
    public R getUserScenesInvert(@RequestParam Integer page, @RequestParam Integer size
            , @RequestParam String userId, @RequestParam String sceneName){
        PageHelper.startPage(page, size);
        List<Scene> sceneInvert = sceneService.getSceneInvert(userId, sceneName);
        return R.data(new PageInfo<>(sceneInvert));
    }

    @ApiOperation(value = "通过场景获取所有所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("members")
    public R getUsersUnderScene(@RequestParam("sceneId")String sceneId){
        return R.data(organService.getUsersByScene(sceneId));
    }

    @ApiOperation(value = "向场景中增加用户（批量）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String")
    })
    @PostMapping("members")
    public R addMembers(@RequestParam String userIds, @RequestParam("sceneId") String sceneId){
        if (Func.isNull(sceneId)){
            return R.fail("参数错误");
        }
        List<String> ids = Func.toStrList(",", userIds);
        userSceneService.remove(Wrappers.<UserScene>lambdaUpdate().eq(UserScene::getSceneId, sceneId));
        //获取该场景下的默认角色
        SceneRole defaultRole = sceneRoleService.getDefaultRole(sceneId);
        sceneRelationService.saveBatch(defaultRole.getId(), ids);
        return R.status(sceneService.addSceneMembers(ids, sceneId));
    }

    @ApiOperation(value = "场景中批量删除用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("members/remove")
    public R removeMembers(@RequestParam String userIds, @RequestParam String sceneId){
        if (Func.hasEmpty(userIds, sceneId)){
            return R.fail("参数错误");
        }
        return R.data(organSceneService.batchRemoveMembers(Func.toStrList(",", userIds), sceneId));
    }

    @ApiOperation(value = "场景中设置用户主部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("node/set")
    public R setMainNode(@RequestParam String userId, @RequestParam String sceneId, @RequestParam String nodeId){
        if (Func.hasEmpty(userId, sceneId, nodeId)){
            return R.fail("参数错误");
        }
        return R.status(userSceneService.updateNodeIdByUserId(userId, sceneId, nodeId));
    }

    @ApiOperation(value = "场景中批量设置用户主部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id数组", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("node/batch-set")
    public R batchSetMainNode(@RequestParam String userIds, @RequestParam String sceneId, @RequestParam String nodeId){
//        List<UserScene> list = userSceneService.list(Wrappers.<UserScene>lambdaQuery().eq(UserScene::getSceneId, sceneId)
//                .in(UserScene::getUserId, Func.toStrList(",", userIds)));
//        list.forEach(map -> {
//            map.setNodeId(nodeId);
//            map.setNodePrincipal(0);
//        });
//        return R.status(userSceneService.updateBatchById(list));
        return R.status(userSceneService.update(Wrappers.<UserScene>lambdaUpdate().set(UserScene::getNodeId, nodeId).set(UserScene::getNodePrincipal, 0)
                .eq(UserScene::getSceneId, sceneId).in(UserScene::getUserId, Func.toStrList(",", userIds))));
    }

    @ApiOperation(value = "场景中设置部门负责人", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("/principal/set")
    @Transactional
    public R setPrincipal(@RequestParam String userId, @RequestParam String sceneId, @RequestParam String nodeId){
        if (Func.hasEmpty(userId, sceneId, nodeId)){
            return R.fail("参数错误");
        }
        return R.status(userSceneService.updatePrincipalByUserId(userId, sceneId, nodeId));
    }

    @ApiOperation(value = "向场景中新增组织部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeIds", value = "部门节点的集合", required = true, dataTypeClass = List.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/node/add")
    public R addNodes(@RequestParam String nodeIds, @RequestParam("sceneId") String sceneId){
        return R.data(organSceneService.addNodes(nodeIds, sceneId));
    }

    @ApiOperation(value = "批量删除场景中的组织部门", notes = "（完成对叶子节点的删除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeIds", value = "部门id的集合", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/node/remove")
    public R removeNodes(@RequestParam String nodeIds, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(nodeIds)){
            return R.fail("参数错误");
        }
        List<OrganScene> count = organSceneService.list(Wrappers.<OrganScene>lambdaQuery().in(OrganScene::getParentId, nodeIds)
                .eq(OrganScene::getSceneId, sceneId));
        if (count.size() > 0){
            return R.fail("请先清除目标节点的叶子节点");
        } else {
            return R.data(organSceneService.batchRemove(Func.toStrList(",", nodeIds), sceneId));
        }
    }

    @ApiOperation(value = "获取用户场景", notes = "根据用户获取场景;page起始页;size每页数据量")
    @GetMapping(value = "/list/byUser")
    public R getUserScenes(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam Integer page
            , @ApiParam(value = "size", defaultValue = "10", required = true) @RequestParam Integer size
            , @ApiParam(value = "userId", required = true) @RequestParam String userId){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getScenesByUser(userId);
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return R.data(scenePageInfo);
    }

    @ApiOperation(value = "为用户装载工作场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/loadScene", method = RequestMethod.POST)
    public R setScene(@RequestParam("userId") String userId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, sceneId)){
            return R.fail("参数错误");
        }
        if (!sceneService.hasScene(userId, sceneId)){
            return R.fail("工作场景加载失败");
        }
        GlobalConstant.put(userId, sceneId);
        return R.success("工作场景加载成功");
    }

    @ApiOperation(value = "场景中通过组织部门获取所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "memberName", value = "用户姓名", required = true, dataTypeClass = String.class)
    })
    @GetMapping("/node/members/list")
    public R getUsersByNode(@RequestParam("page") Integer page, @RequestParam("size") Integer size
            , @RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId
            , @RequestParam("memberName") String memberName){
        PageHelper.startPage(page, size);
        List<UserVO> nodeUsers = userSceneService.getNodeUsers(sceneId, nodeId, memberName);
        return R.data(new PageInfo<>(nodeUsers));
    }

//    @ApiOperation(value = "场景中通过组织部门获取所属用户（无分页）", notes = "已完成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
//            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
//    })
//    @GetMapping("node/members")
//    public R getUsersByNode2(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId){
//        return R.data(userSceneService.getNodeUsers(sceneId, nodeId, ""));
//    }

    @ApiOperation(value = "检索场景中的组织部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping(value = "/node/search")
    public R searchSceneNode(@RequestParam("keyword") String keyword, @RequestParam("sceneId") String sceneId){
        return R.data(sceneService.getNodesByNameAlike(keyword, sceneId));
    }

//    @ApiOperation(value = "通过邮件邀请用户接入场景", notes = "未完成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class)
//    })
//    @PostMapping("invite/member")
//    public R inviteMember(@RequestParam String userId) {
//        //TODO:邮件单独做成一个服务，同时需要整合redis和mq
//        Email email = new Email();
//        email.setEmail(Func.toStrArray(",", "1036514689@qq.com"));
//        email.setContent("资产云开发协同中心");
//        email.setSubject("尊敬的用户您好");
//        mailService.send(email);
//        return R.data("发送成功");
//    }

    @ApiOperation(value = "获取场景中成员的已有角色", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/owned")
    public R getMemberRole(@RequestParam String userId, @RequestParam String sceneId){
        return R.data(userSceneService.rolesOwned(userId, sceneId));
    }

    @ApiOperation(value = "获取场景中当前成员未拥有的角色", notes = "已完成，未拥有的角色其checked值为0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles/invert")
    public R getMemberRoleInvert(@RequestParam String userId, @RequestParam String sceneId){
        return R.data(userSceneService.rolesChecked(userId, sceneId));
    }

    @ApiOperation(value = "用户请求绑定其它场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneIds", value = "场景id数组", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("bind/another")
    @Transactional
    public R bindScene(@RequestParam(value = "sceneId") String sceneIds
            , @RequestParam(value = "userId") String userId){
        Map<String, String> jsonMap = new HashMap<>();
        if (Func.hasEmpty(sceneIds)) {
            return R.fail("参数错误");
        }
        //绑定场景与用户
        sceneService.userSceneBind(Func.toStrList(",", sceneIds), userId);
        jsonMap.put("userId", userId);
        jsonMap.put("sceneIds", sceneIds);
        return R.data(jsonMap);
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
