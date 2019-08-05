package com.asset.controller;


import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.common.model.Query;
import com.asset.service.IDictService;
import com.asset.service.IResourceService;
import com.asset.service.ISceneService;
import com.asset.utils.Condition;
import com.asset.utils.Func;
import com.asset.vo.ResourceVO;
import com.asset.wrapper.ResourceWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    IDictService dictService;

    ISceneService sceneService;

    @RequestMapping(value = "/app/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加资源", notes = "（已完成）传Application实体与sceneId(param);")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicationName", value = "应用名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "应用图标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @Transactional
    public RespBean addResource(@RequestBody Application application, @RequestParam("sceneId")String sceneId){
        if (Func.hasEmpty(application.getId(), application.getApplicationName(), application.getIconCls())){
            return RespBean.error("参数错误");
        }
        if(resourceService.appExists(application.getApplicationName(), sceneId)){
            return RespBean.error("应用名称已被占用，请更换后重试");
        }
        Resource resource = new Resource();
        resource.setCode(SystemConstant.CODE_APP);
        resource.setName(application.getApplicationName());
        resource.setPath(application.getId());
        resource.setIconCls(application.getIconCls());
        resource.setLevel(0);
        resource.setSort(0);
        resource.setParentId((long) 0);
        resource.setIsDeleted(0);
        resource.setAddTime(new Date());
        resource.setCategory(1);
        resource.setGroupId("");
        resource.setGroupName("");
        resource.setSceneId(sceneId);
        resourceService.save(resource);
        resourceService.addResource4Admin(resource);
        return RespBean.ok("添加成功");
    }

    @ApiOperation(value = "添加资源（表单类型）", notes = "（已完成）传FormModelInfo实体与sceneId(param);")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applicationId", value = "应用id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formModelId", value = "表单id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formName", value = "表单名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "iconCls", value = "表单图标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "表单分组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupName", value = "表单分组名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @PostMapping(value = "form/add")
    @Transactional
    public RespBean addResource(@RequestBody FormModelInfo formModelInfo, @RequestParam("sceneId")String sceneId) throws CloneNotSupportedException {
        if (Func.hasEmpty(formModelInfo.getApplicationId(), formModelInfo.getFormModelId(), formModelInfo.getFormName()
                , formModelInfo.getIconCls(), formModelInfo.getGroupId(), formModelInfo.getGroupName())){
            return RespBean.error("参数错误");
        }
        Resource parentResource = resourceService.getResourceByPath(formModelInfo.getApplicationId());
        if (Func.isNull(parentResource)){
            return RespBean.error("应用不存在");
        }
        if(resourceService.formExists(formModelInfo.getFormName(), sceneId, parentResource.getId())){
            return RespBean.error("表单名称已被使用，请更换后再试");
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
        // 为表单设置分组
        resource.setGroupId(formModelInfo.getGroupId());
        resource.setGroupName(formModelInfo.getGroupName());
        //为表单设置场景
        resourceService.save(resource);
        resourceService.addResource4Admin(resource);
        resourceService.addFuncResource(resource);
        return RespBean.ok(SystemConstant.ADD_SUCCESS);
    }

    @ApiOperation(value = "绑定场景与表单", notes = "（已完成）保存流程时将表单与场景相互绑定")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formModelId", value = "表单id(param)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/form/scene/bind", method = RequestMethod.PUT)
    @Transactional
    public RespBean formEnvBind(@RequestParam("formModelId") String formModelId, @RequestParam("sceneId") String sceneId){
        if(Func.hasEmpty(formModelId, sceneId)){
            return RespBean.error("参数错误");
        }
        if (!resourceService.formExists(formModelId)){
            return RespBean.error("表单资源不存在");
        }
        resourceService.updateFormInfo(formModelId, sceneId);
        return RespBean.data(resourceService.updateFuncInfo(formModelId, sceneId));
    }

    @GetMapping("list")
    public RespBean getAll(@NotEmpty @RequestParam String sceneId){
        return RespBean.data(resourceService.getResourceList(sceneId));
    }

    @ApiOperation(value = "获取资源", notes = "（已完成）通过当前用户获取所有资源（终端用户操作时使用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "formModelId", value = "表单id(param)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String"),
    })
    @GetMapping(value = "byUser/list")
    public RespBean getMenusByCurrentUser(@RequestParam("userId") String userId, @RequestParam("sceneId")String sceneId){
        if (Func.hasEmpty(userId, sceneId)){
            return RespBean.error("参数错误");
        }
        return RespBean.data(resourceService.getResourcesByCurrentUser(userId, sceneId));
    }

    @ApiOperation(value = "获取资源", notes = "（已完成）通过角色获取资源(用于终端管理员的权限管理界面)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/byRole/list", method = RequestMethod.GET)
    public RespBean getResourcesByRoleId(@RequestParam("roleId") Long roleId, @RequestParam("sceneId") String sceneId){
        return RespBean.data(resourceService.getResourcesByRole(roleId, sceneId));
    }

    @ApiOperation(value = "获取应用资源", notes = "（已完成）通过当前用户角色获取应用资源，主要用于首页内容展现")
    @RequestMapping(value = "/app/byUser", method = RequestMethod.GET)
    public RespBean getAppMenusByUser(@RequestParam("userId") String userId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, sceneId)){
            return RespBean.error("参数错误");
        }
        return RespBean.data(resourceService.getAppResourcesByUser(userId, sceneId));
    }

    @ApiOperation(value = "获取表单资源", notes = "通过点击应用，展现可访问的表单资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "appResourceId", value = "应用id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "form/byUser", method = RequestMethod.GET)
    public RespBean getFormMenusByApp(@RequestParam("userId")String userId, @RequestParam("appResourceId") Long appResourceId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, appResourceId, sceneId)){
            return RespBean.error("参数错误");
        }
        return RespBean.data(resourceService.getFormResourcesByApp(userId, appResourceId, sceneId));
    }

    @GetMapping("func/byForm")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "formResourceId", value = "表单资源id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @ApiOperation(value = "获取操作型资源", notes = "（已完成）通过点击应用，展现可访问的操作型资源")
    public RespBean getFuncByForm(@RequestParam("userId")String userId, @RequestParam("formModelId") Long formResourceId, @RequestParam("sceneId") String sceneId){
        if (Func.hasEmpty(userId, formResourceId, sceneId)){
            return RespBean.error("参数错误");
        }
        return RespBean.data(resourceService.getFuncResourcesByForm(userId, formResourceId, sceneId));
    }

    @ApiOperation(value = "获取所有应用资源", notes = "已完成")
    @GetMapping("sys/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", defaultValue = "1", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "单页数据量", defaultValue = "10", required = true, dataType = "int"),
            @ApiImplicitParam(name = "category", value = "资源类型", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "资源名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sceneId", value = "场景id", paramType = "query", dataType = "string")
    })
    public R list(Query query, @ApiIgnore @RequestParam Map<String, Object> resource){
        PageHelper.startPage(query.getPage(), query.getSize());
        List<Resource> list = resourceService.list(Condition.getQueryWrapper(resource, Resource.class).lambda()
                .eq(Resource::getIsDeleted, 0)
                .orderByAsc(Resource::getSort));
        ResourceWrapper resourceWrapper = new ResourceWrapper(resourceService, dictService, sceneService);
        return R.data(new PageInfo<>(resourceWrapper.listNodeVO(list)));
    }

    @GetMapping("detail")
    @ApiOperation(value = "资源详情", notes = "已完成")
    @ApiImplicitParam(name = "id", value = "资源id", defaultValue = "1", required = true, dataType = "int")
    public R<ResourceVO> detail(Resource resource){
        Resource record = resourceService.getOne(Condition.getQueryWrapper(resource));
        ResourceWrapper resourceWrapper = new ResourceWrapper(resourceService, dictService, sceneService);
        return R.data(resourceWrapper.entityVO(record));
    }

    @PostMapping("submit")
    @ApiOperation(value = "新增或修改", notes = "（未完成）传入resource实体")
    public R submit(@Valid @RequestBody Resource resource){
        return R.status(resourceService.saveOrUpdate(resource));
    }

    @PostMapping("remove")
    @ApiOperation(value = "删除", notes = "（未完成）")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        Collection<Resource> resources = resourceService.listByIds(Func.toIntList(ids));
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
}
