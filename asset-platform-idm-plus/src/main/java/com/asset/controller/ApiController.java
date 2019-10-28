package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.config.WebLog;
import com.asset.service.*;
import com.asset.utils.Func;
import com.asset.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
@Api(value = "开发API", tags = "开放API")
public class ApiController {

    ISceneService sceneService;

    IResourceService resourceService;

    IOrganService organService;

    IUserService userService;

    ISceneRoleService sceneRoleService;

    IUserRoleService userRoleService;

    IUserSceneService userSceneService;

    IRoleService roleService;

    IDictService dictService;

    /*@ApiOperation(value = "获取所有场景信息", notes = "获取所有场景信息",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "rest/scenes", method = RequestMethod.GET)
    public R getAllScene(@ApiParam(value = "page", defaultValue = "1",required = true) @RequestParam(defaultValue = "1") Integer page
            , @ApiParam(value = "size", defaultValue = "1",required = true) @RequestParam(defaultValue = "20") Integer size){
        List<Scene> scenes = sceneService.getAllScenesByPage(page, size);
        if (Func.isNull(scenes)){
            return R.error(SystemConstant.SYSTEM_FAILURE);
        }
        return R.ok(SystemConstant.GET_SUCCESS, scenes);
    }*/

    /**
     * 分页获取场景信息
     * @param page
     * @param size
     * @return R
     */
    @GetMapping("rest/scenes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10", paramType = "query")
    })
    @ApiOperation(value = "获取所有场景信息", notes = "获取所有场景信息;page为起始页;size为每页显示数量")
    @WebLog(description = "获取所有场景信息")
    public R getAllSceneByPage(Integer page, Integer size){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getAllScene();
        if (Func.isNull(scenes)){
            return R.fail(SystemConstant.SYSTEM_FAILURE);
        }
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return R.data(scenePageInfo);
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return R
     */
    @ApiOperation(value = "获取主组织树", notes = "已完成")
    @RequestMapping(value = "/mainTree", method = RequestMethod.GET)
    public R getOrganMainTree(){
        return R.data(organService.getMainTree());
    }

    @ApiOperation(value = "添加菜单（表单类型）", notes = "传FormModelInfo实体与sceneId(param);")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationId", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formModelId", value = "表单id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formName", value = "表单名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "表单图标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "表单分组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupName", value = "表单分组名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "场景id", required = true, dataType = "String")
    })

    @PostMapping(value = "form/add")
    @Transactional
    public R addResource(@RequestBody FormModelInfo formModelInfo, @RequestParam("sceneId") String sceneId
            , @RequestParam("groupId")Long groupId) throws CloneNotSupportedException {
        if (Func.hasEmpty(formModelInfo.getApplicationId(), formModelInfo.getFormModelId(), formModelInfo.getFormName()
                , formModelInfo.getIconCls(), formModelInfo.getGroupId(), formModelInfo.getGroupName(), sceneId)){
            return R.fail("参数错误");
        }
        Resource parentResource = resourceService.getResourceByPath(formModelInfo.getApplicationId());
        if (Func.isNull(parentResource)){
            return R.fail("应用不存在");
        }
        if(resourceService.formExists(formModelInfo.getFormName(), sceneId, parentResource.getId())){
            return R.fail("表单名称已被使用，请更换后再试");
        }
        Resource resource = new Resource();
        //1-应用；2-表单；3-表单操作
        resource.setCategory(2);
        resource.setParentId(parentResource.getId());
        resource.setAddTime(new Date());
        resource.setIsDeleted(0);
        resource.setSort(0);
        resource.setLevel(0);
        resource.setPath(formModelInfo.getFormModelId());
        resource.setName(formModelInfo.getFormName());
        resource.setIconCls(formModelInfo.getIconCls());
        resource.setCode(SystemConstant.CODE_FORM);
        resource.setGroupId(groupId);
        resource.setSceneId(sceneId);
        resourceService.save(resource);
        resourceService.addResource4Admin(resource);
        resourceService.addFuncResource(resource);
        return R.success(SystemConstant.ADD_SUCCESS);
    }

    @ApiOperation(value = "注册用户激活", notes = "（已完成）sceneId场景ID;userId用户ID;")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "sceneId", required = true, name = "场景id", dataTypeClass = String.class),
            @ApiImplicitParam(value = "userId", required = true, name = "用户id", dataTypeClass = String.class)
    })
    @PostMapping(value = "active")
    @Transactional
    public R userActivate(@RequestParam String sceneId, @RequestParam String userId) {
        if (userService.getById(userId).getAdmin() == 1){
            //TODO:
            return R.success("注册为平台管理员的接口还没做");
        }
        userService.enableUser(userId);
        //设置平台级权限
        UserRole userRole = new UserRole();
        userRole.setCreatedTime(new Date());
        userRole.setUid(userId);
        userRole.setStatus(1);
        userRole.setRoleId(SystemConstant.DEFAULT_ROLE_ID);
        userRoleService.save(userRole);
        sceneService.enableScene(userId, sceneId);
        return R.success("用户审核通过");
    }

    @GetMapping("hdu/organization")
    public R getHduOrgan(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId){
        // 杭电的工作场景 e65edc60-96ee-11e9-ac96-005056c00001
        // 杭电级别的部门集合 743ccc5fb94314d08490c4662b16753a
        return R.data(organService.getNodeByScene(sceneId, nodeId));
    }

    @GetMapping("hdu/node/user/list")
    public R getUsersByNode(@RequestParam("sceneId") String sceneId, @RequestParam("nodeId") String nodeId){
        List<UserVO> nodeUsers = userSceneService.getNodeUsers(sceneId, nodeId, "");
//        UserWrapper userWrapper = new UserWrapper(userService, dictService, roleService);
        return R.data(nodeUsers);
    }

    @ApiOperation(value = "批量添加组织树节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "单位id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @PostMapping("hdu/organ/nodes/add")
    public R addNode(@RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.isNull(organTree.getSort())){
                organTree.setSort(0);
            }
            if (organService.nodeExists(organTree.getUnitName())){
                return R.fail(String.format("组织部门: %s 已存在", organTree.getUnitName()));
            }
//            if (!organService.hasParent(organTree.getParentId())){
//                return R.fail("目标父节点不存在");
//            }
            organTree.setStatus(1);
            organTree.setIsDeleted(0);
            organTree.setEnableTime(new Date());
        }
        return R.data(organService.batchAddNodes(organTrees));
    }
}
