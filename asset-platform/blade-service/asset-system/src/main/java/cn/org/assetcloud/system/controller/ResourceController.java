package cn.org.assetcloud.system.controller;


import cn.org.assetcloud.system.common.SystemConstant;
import cn.org.assetcloud.system.entity.*;
import cn.org.assetcloud.system.service.*;
import cn.org.assetcloud.system.vo.ResourceGroupVO;
import cn.org.assetcloud.system.vo.ResourceVO;
import cn.org.assetcloud.system.wrapper.ResourceGroupWrapper;
import cn.org.assetcloud.system.wrapper.ResourceWrapper;
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
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  业务级资源控制器
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("resource")
@Api(value = "业务资源管理", tags = "业务资源管理")
public class ResourceController {

    IResourceService resourceService;

    IDictClient dictClient;

    ISceneService sceneService;

    IResourceGroupService resourceGroupService;

    IResourceRoleService resourceRoleService;

    ISceneRelationService sceneRelationService;

    ISceneRoleService sceneRoleService;

    @ApiOperation(value = "添加应用资源", notes = "（已完成）传Application实体与sceneId(param);")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicationName", value = "应用名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @Transactional
	@PostMapping(value = "/app/save")
	public R addResource(@RequestBody Application application, @RequestParam("sceneId")String sceneId){
        if (Func.hasEmpty(application.getId(), application.getApplicationName(), application.getIconCls())){
            return R.fail("参数错误");
        }
        if(resourceService.appExists(application.getApplicationName(), sceneId)){
            return R.fail("应用名称已被占用，请更换后重试");
        }
        Resource resource = new Resource(application, sceneId);
		resourceService.save(resource);
        resourceService.addResource4Admin(resource);
        return R.success("添加成功");
    }


	@ApiOperation(value = "添加表单资源", notes = "（已完成）传FormModelInfo实体与sceneId(param);")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "表单资源id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "applicationId", value = "应用id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "formModelId", value = "表单模型id", required = true, dataType = "string"),
            @ApiImplicitParam(name = "formName", value = "表单名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "iconCls", value = "表单图标", required = true, dataType = "string"),
            @ApiImplicitParam(name = "groupId", value = "资源分组id", required = true, dataType = "int")
    })
    @PostMapping(value = "form/save")
    @Transactional
    public R addResource(@RequestBody FormModelInfo formModelInfo, @RequestParam("groupId")Long groupId
		, @RequestParam("sceneId") String sceneId) throws CloneNotSupportedException {
        if (Func.hasEmpty(formModelInfo.getApplicationId(), formModelInfo.getFormModelId(), formModelInfo.getFormName())){
            return R.fail("参数错误");
        }
        Resource parentResource = resourceService.getResourceByPath(formModelInfo.getApplicationId());
        if (Func.isNull(parentResource)){
            return R.fail("应用不存在");
        }
        if(formModelInfo.getId() == null && resourceService.formExists(formModelInfo.getFormName(), parentResource.getSceneId(), parentResource.getId())){
            return R.fail("表单名称已被使用，请更换后再试");
        }
		Resource resource = new Resource(formModelInfo, groupId, parentResource, sceneId);
        resourceService.saveOrUpdate(resource);
        if (formModelInfo.getId() == null){
            resourceService.addResource4Admin(resource);
            resourceService.addFuncResource(resource);
        }
        return R.data(resource.getId());
    }



	@ApiOperation(value = "绑定场景与表单", notes = "（已完成）保存流程时将表单与场景相互绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formModelId", value = "表单id(param)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String"),
    })
    @PostMapping(value = "form/scene/bind")
    @Transactional
    public R formEnvBind(@RequestParam("formModelId") String formModelId, @RequestParam("sceneId") String sceneId){
        if(Func.hasEmpty(formModelId, sceneId)){
            return R.fail("参数错误");
        }
        if (!resourceService.formExists(formModelId)){
            return R.fail("表单资源不存在");
        }
        resourceService.updateFormInfo(formModelId, sceneId);
        return R.data(resourceService.updateFuncInfo(formModelId, sceneId));
    }

    @ApiOperation(value = "根据场景id获取资源列表", notes = "已完成")
	@ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "string")
    @GetMapping("list-by-scene")
    public R<List<ResourceVO>> getAll(@NotEmpty @RequestParam String sceneId){
        List<Resource> resourceList = resourceService.list(Wrappers.<Resource>query().lambda()
                .eq(Resource::getSceneId, sceneId));
        ResourceWrapper resourceWrapper = new ResourceWrapper(resourceService, dictClient, sceneService, resourceGroupService);
        return R.data(resourceWrapper.listNodeVO(resourceList));
    }

    @ApiOperation(value = "获取资源", notes = "（已完成）通过当前用户获取所有资源（终端用户操作时使用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(param)", required = true, dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "string"),
    })
    @GetMapping(value = "list-by-user")
    public R getMenusByCurrentUser(@RequestParam("userId") String userId, @RequestParam("sceneId")String sceneId){
        return R.data(resourceService.getResourcesByCurrentUser(userId, sceneId));
    }

    @ApiOperation(value = "通过角色获取资源keys", notes = "（已完成）通过角色获取资源(用于终端管理员的权限管理界面)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Long")
    })
    @GetMapping("/role-res-keys")
    public R getResourcesByRoleId(@RequestParam("roleId") Long roleId){
        return R.data(resourceService.getResourcesByRole(roleId));
    }

    @ApiOperation(value = "获取应用资源", notes = "（已完成）通过当前用户角色获取应用资源，主要用于首页内容展现")
    @RequestMapping(value = "/app/byUser", method = RequestMethod.GET)
    public R getAppMenusByUser(@RequestParam("userId") String userId, @RequestParam("sceneId") String sceneId){
        return R.data(resourceService.getAppResourcesByUser(userId, sceneId));
    }

    @ApiOperation(value = "获取表单资源", notes = "通过点击应用，展现可访问的表单资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appResourceId", value = "应用id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "form/byUser", method = RequestMethod.GET)
    public R getFormMenusByApp(@RequestParam("userId")String userId
            , @RequestParam("appResourceId") Long appResourceId
            , @RequestParam("sceneId") String sceneId){
        return R.data(resourceService.getFormResourcesByApp(userId, appResourceId, sceneId));
    }


    @ApiOperation(value = "根据应用获取表单资源", notes = "通过点击应用，展现可访问的表单资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appId", value = "应用id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping(value = "form/list")
    public R getFormResourceByApp(@RequestParam("userId")String userId
            , @RequestParam("appId") Long appId, @RequestParam("sceneId") String sceneId){
        List<SceneRole> rolesOwned = sceneRoleService.getRolesOwned(userId, sceneId);
        List<Long> roleIds = rolesOwned.stream().map(SceneRole::getId).collect(Collectors.toList());
        List<Resource> formList = resourceService.getFormList(appId, roleIds);
        ResourceWrapper resourceWrapper = new ResourceWrapper(resourceService, dictClient, sceneService, resourceGroupService);
        return R.data(resourceWrapper.listNodeVOWithGroup(appId, formList));
    }

    @GetMapping("func-by-form")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formResourceId", value = "表单资源id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取操作型资源", notes = "（已完成）通过点击应用，展现可访问的操作型资源")
    public R getFuncByForm(@RequestParam("userId")String userId, @RequestParam("formResourceId") Long formResourceId
            , @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, formResourceId, sceneId)){
            return R.fail("参数错误");
        }
        return R.data(resourceService.getFuncResourcesByForm(userId, formResourceId, sceneId));
    }

    @SuppressWarnings("unchecked")
	@ApiOperation(value = "获取应用资源", notes = "已完成")
    @GetMapping("sys-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", defaultValue = "1", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "单页数据量", defaultValue = "10", required = true, dataType = "int"),
            @ApiImplicitParam(name = "category", value = "资源类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "资源名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", paramType = "query", dataType = "string")
    })
    public R list(Query query, @ApiIgnore @RequestParam Map<String, Object> resource){
		PageHelper.startPage(query.getCurrent(), query.getSize());
		QueryWrapper queryWrapper = Condition.getQueryWrapper(resource, Resource.class);
		return R.data(new PageInfo<>(resourceService.list(queryWrapper)));
    }

    @GetMapping("detail")
    @ApiOperation(value = "资源详情", notes = "已完成")
    @ApiImplicitParam(name = "id", value = "资源id", defaultValue = "1", required = true, dataType = "int")
    public R<ResourceVO> detail(Resource resource){
        Resource record = resourceService.getOne(Condition.getQueryWrapper(resource));
        ResourceWrapper resourceWrapper = new ResourceWrapper(resourceService, dictClient, sceneService, resourceGroupService);
        return R.data(resourceWrapper.entityVO(record));
    }

//    @PostMapping("submit")
//    @ApiOperation(value = "新增或修改", notes = "（已完成）传入resource实体")
//    public R submit(@Valid @RequestBody Resource resource){
//        switch (resource.getCategory()){
//            case 1:
//                resource.setCode(SystemConstant.CODE_APP);
//                resource.setLevel(SystemConstant.DEFAULT_RES_LEVEL);
//            case 2:
//                resource.setCode(SystemConstant.CODE_FORM);
//                resource.setLevel(SystemConstant.DEFAULT_RES_LEVEL);
//            case 3:
//                resource.setCode(SystemConstant.CODE_FUNC);
//        }
//        return R.status(resourceService.saveOrUpdate(resource));
//    }

    @PostMapping("remove")
    @ApiOperation(value = "删除", notes = "（已完成）")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        Collection<Resource> resources = resourceService.listByIds(Func.toStrList(",", ids));
        resources.forEach(resource ->{
            resource.setIsDeleted(1);
            resource.setRemoveTime(new Date());
        });
        return R.status(resourceService.updateBatchById(resources));
    }

    @GetMapping("tree")
    @ApiOperation(value = "树形结构", notes = "（已完成）树形结构")
    @ApiImplicitParam(name = "sceneId", value = "场景id", defaultValue = "1", required = true, dataType = "string")
    public R<List<ResourceVO>> tree(String sceneId) {
        if (Func.hasEmpty(sceneId)){
            sceneId = "";
        }
        List<ResourceVO> tree = resourceService.tree(sceneId);
        return R.data(tree);
    }

    @GetMapping("group/list")
    @ApiOperation(value = "获取资源分组", notes = "（已完成）")
    public R<List<ResourceGroupVO>> groups(@ApiParam(value = "sceneId", required = true, name = "场景ID") @RequestParam String sceneId
    , @ApiParam(value = "appId", required = true, name = "应用ID") @RequestParam Long appId){
        List<ResourceGroup> list = resourceGroupService.list(Wrappers.<ResourceGroup>query().lambda()
                .eq(ResourceGroup::getSceneId, sceneId).eq(ResourceGroup::getAppId, appId));
        ResourceGroupWrapper resourceGroupWrapper = new ResourceGroupWrapper(resourceGroupService, sceneService);
        return R.data(resourceGroupWrapper.listNodeVO(list));
    }

    @PostMapping("group/submit")
    @ApiOperation(value = "资源分组新增或修改", notes = "（已完成）传入resourceGroup实体")
    public R submit(@Valid @RequestBody ResourceGroup resourceGroup){
        if (resourceGroupService.groupExists(resourceGroup)){
            return R.fail("该分组已存在,请更换名称后重试");
        }
        return R.status(resourceGroupService.saveOrUpdate(resourceGroup));
    }

    @PostMapping("group/remove")
    @ApiOperation(value = "资源分组删除", notes = "（已完成）传入resourceGroup实体")
    public R removeGroups(String ids){
        List<Long> groupIds = cn.org.assetcloud.system.utils.Func.toLongList(",", ids);
        int count = resourceService.count(Wrappers.<Resource>query().lambda()
                .in(Resource::getGroupId, groupIds));
        if (count > 0){
            return R.fail("目标分组下存在表单资源，请删除后重试");
        }
        return R.status(resourceGroupService.remove(Wrappers.<ResourceGroup>query().lambda()
                .in(ResourceGroup::getId, groupIds)));
    }
}
