package cn.org.assetcloud.system.controller;

import cn.org.assetcloud.system.common.SystemConstant;
import cn.org.assetcloud.system.entity.*;
import cn.org.assetcloud.system.service.*;
import cn.org.assetcloud.system.user.feign.IUserClient;
import cn.org.assetcloud.system.user.service.IUserService;
import cn.org.assetcloud.system.vo.SceneVO;
import cn.org.assetcloud.system.vo.UserVO;
import cn.org.assetcloud.system.wrapper.SceneWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("scene")
@Api(value = "场景管理", tags = "场景管理")
public class SceneController {

	IUserClient userClient;

    IOrganService organService;

    ISceneService sceneService;

    IOrganSceneService organSceneService;

    ISceneRoleService sceneRoleService;

    IUserSceneService userSceneService;

    ISceneRelationService sceneRelationService;

    IUserService userService;

    IRoleService roleService;

    IDictClient dictClient;

    IResourceService resourceService;

    IResourceGroupService resourceGroupService;

    @ApiOperation(value = "获取所有场景信息", notes = "已完成")
    @GetMapping(value = "list")
    public R<List<SceneVO>> getAllScenes(){
        List<Scene> allScene = sceneService.getAllScene();
        SceneWrapper sceneWrapper = new SceneWrapper(sceneService);
        return R.data(sceneWrapper.listNodeVO(allScene));
    }

//    @PostMapping("remove")
//    @ApiOperation(value = "删除场景", notes = "已完成")
//    public R removeScene(@ApiParam(value = "sceneId", required = true) @RequestParam String sceneId){
//    	// TODO 必要的处理
//        return R.status(sceneService.removeScene(sceneId));
//    }

    @ApiOperation(value = "获取用户尚未拥有的场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "current", name = "起始页", required = true, defaultValue = "1", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "size", name = "每页数据量", required = true, defaultValue = "10", dataTypeClass = Integer.class),
            @ApiImplicitParam(value = "userId", name = "用户id", required = true,  dataTypeClass = String.class),
            @ApiImplicitParam(value = "sceneName", name = "场景名称", required = true, dataTypeClass = String.class)
    })
    @GetMapping("list-invert")
    public R<PageInfo<Scene>> getUserScenesInvert(Query query, @RequestParam("userId") String userId, @RequestParam("sceneName") String sceneName){
		PageHelper.startPage(query.getCurrent(), query.getSize());
		List<Scene> sceneInvert = sceneService.getSceneInvert(userId, sceneName);
		return R.data(new PageInfo<>(sceneInvert));
    }

    @ApiOperation(value = "通过场景获取所有所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "string")
    })
    @GetMapping("member/list")
    public R getUsersUnderScene(@RequestParam("sceneId") String sceneId){
		List<UserScene> list = userSceneService.list(Wrappers.<UserScene>query().lambda().select(UserScene::getUserId).eq(UserScene::getSceneId, sceneId));
		List<String> userIds = list.stream().map(UserScene::getUserId).collect(Collectors.toList());
		return R.data(userClient.usersByScene(userIds));
    }

    @ApiOperation(value = "向场景中增加用户（批量）", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userIds", value = "用户id的集合", required = true, dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "string")
    })
    @PostMapping("members")
    public R addMembers(@RequestParam String userIds, @RequestParam("sceneId") String sceneId){
        List<String> ids = Func.toStrList(",", userIds);
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
        return R.data(organSceneService.batchRemoveMembers(Func.toStrList(",", userIds), sceneId));
    }

    @ApiOperation(value = "场景中设置用户主部门", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class)
    })
    @PostMapping("main/node/set")
    public R setMainNode(@RequestParam String userId, @RequestParam String sceneId, @RequestParam String nodeId){
        if (Func.hasEmpty(userId, sceneId, nodeId)){
            return R.fail("参数错误");
        }
        return R.status(userSceneService.updateNodeIdByUserId(userId, sceneId, nodeId));
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

	@ApiImplicitParams({
		@ApiImplicitParam(name = "current", value = "起始页", defaultValue = "1", required = true),
		@ApiImplicitParam(name = "size", value = "数据量", defaultValue = "10", required = true),
		@ApiImplicitParam(name = "userId", value = "用户id", required = true)
	})
    @ApiOperation(value = "获取用户场景", notes = "根据用户获取场景;page起始页;size每页数据量")
    @GetMapping(value = "list-by-user")
    public R<PageInfo<Scene>> getUserScenes(Query query, @RequestParam String userId){
		PageHelper.startPage(query.getCurrent(), query.getSize(), false);
		List<Scene> scenes = sceneService.getScenesByUser(userId);
        return R.data(new PageInfo<>(scenes));
    }

    @ApiOperation(value = "为用户装载工作场景", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @PostMapping(value = "load-scene")
    public R setScene(@RequestParam("userId") String userId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, sceneId)){
            return R.fail("参数错误");
        }
        if (!sceneService.hasScene(userId, sceneId)){
            return R.fail("工作场景加载失败");
        }
        // TODO 改为Redis
//        GlobalConstant.put(userId, sceneId);
        return R.success("工作场景加载成功");
    }

    @ApiOperation(value = "场景中通过组织部门获取所属用户", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "起始页", defaultValue = "1", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10", required = true, dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "nodeId", value = "部门id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "memberName", value = "用户姓名", required = true, dataTypeClass = String.class)
    })
    @GetMapping("node/members/list")
    public R<PageInfo<UserVO>> getUsersByNode(Query query, @RequestParam("sceneId") String sceneId,
											@RequestParam("nodeId") String nodeId,
											@RequestParam("memberName") String memberName){
        PageHelper.startPage(query.getCurrent(), query.getSize());
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
    @GetMapping(value = "node/search")
    public R searchSceneNode(@RequestParam("keyword") String keyword, @RequestParam("sceneId") String sceneId){
        return R.data(sceneService.getNodesByNameAlike(keyword, sceneId));
    }

//    @ApiOperation(value = "通过邮件邀请用户接入场景", notes = "未完成")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class)
//    })
//    @PostMapping("invite/member")
//    public R inviteMember(@RequestParam String userId) {
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
    @GetMapping("member/roles-owned")
    public R getMemberRole(@RequestParam String userId, @RequestParam String sceneId){
        return R.data(userSceneService.rolesOwned(userId, sceneId));
    }

    @ApiOperation(value = "获取场景中当前成员未拥有的角色", notes = "已完成，未拥有的角色其checked值为0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "sceneId", value = "场景", required = true, dataTypeClass = String.class)
    })
    @GetMapping("member/roles-invert")
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
    @GetMapping("user-specify")
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
            List<String> users = sceneRoleService.getUsersByRoles(cn.org.assetcloud.system.utils.Func.toLongList(",", roleIds));
            userList.addAll(users);
        }
        userList.addAll(Func.toStrList(",", userIds));
        return R.data(userList);
    }

	@ApiOperation(value = "获取单个场景的基本信息", notes = "已完成")
	@GetMapping(value = "detail")
	public R getOneScene(@RequestParam("sceneId") String sceneId){
		return R.data(sceneService.getById(sceneId));
	}

	@ApiOperation(value = "新增场景", notes = "（已完成）添加场景信息;传入Scene实体;sceneName必填")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "sceneName", required = true, name = "场景名称")
	})
	@Transactional
	@PostMapping(value = "save")
	public R addScene(@Valid @RequestBody Scene scene){
		if (sceneService.getSceneByName(scene.getSceneName()).size() > 0){
			//记录已存在
			return R.fail("场景名称已被使用");
		}
		sceneService.addSceneNormal(scene);
		return R.data(sceneRoleService.addRoles4Scene(scene.getId()));
	}

	@ApiOperation(value = "删除场景", notes = "（未完成）删除场景信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
	})
	@PostMapping(value = "remove")
	public R deleteScene(@RequestParam("sceneId") String sceneId){
    	return R.status(sceneService.clearScene(sceneId));
	}

	@ApiOperation(value = "修改场景信息", notes = "已完成")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sceneName", value = "新场景名称", required = true, dataType = "String"),
	})
	@PostMapping(value = "update")
	public R modifyScene(@Valid @RequestBody Scene scene){
		//根据场景名称的变化，判断是否需要对名称进行限制（防止重复）
		if (!scene.getSceneName().trim().equals(sceneService.getById(scene.getId()).getSceneName())){
			if (sceneService.getSceneByName(scene.getSceneName()).size() > 0){
				//记录已存在
				return R.fail("场景名称已被使用，请更换后重试");
			}
		}
		return R.data(sceneService.updateById(scene));
	}

	@ApiOperation(value = "获取所有场景信息（兼模糊查询）", notes = "（已完成）场景信息的模糊检索;page当前页数;size当前页数据量;sceneName在非模糊查询时置空即可")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "current", defaultValue = "1", value = "起始页", required = true, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "size", defaultValue = "20", value = "每页数据量", required = true, dataTypeClass = Integer.class),
		@ApiImplicitParam(name = "sceneName", value = "场景名称", dataTypeClass = String.class)
	})
	@GetMapping(value = "list-with-page")
	public R<PageInfo<Scene>> findScene(@ApiIgnore @RequestParam Map<String, Object> scene, Query query){
		PageHelper.startPage(query.getCurrent(), query.getSize());
		QueryWrapper<Scene> queryWrapper = Condition.getQueryWrapper(scene, Scene.class);
		List<Scene> list = sceneService.list(queryWrapper);
		if (Func.isNull(list)){
			return R.success(SystemConstant.SCENE_NOT_FOUND);
		}
		return R.data(new PageInfo<>(list));
	}

	@GetMapping("signify")
	@ApiOperation(value = "判断用户所属部门", notes = "已完成")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "userId", name = "用户id", required = true),
		@ApiImplicitParam(value = "sceneId", name = "场景id", required = true)
	})
	public R userSignify(@RequestParam String userId, @RequestParam String sceneId){
		UserScene record = userSceneService.getOne(Wrappers.<UserScene>lambdaQuery()
			.eq(UserScene::getUserId, userId).eq(UserScene::getSceneId, sceneId));
		return R.data(record.getNodeId());
	}
}
