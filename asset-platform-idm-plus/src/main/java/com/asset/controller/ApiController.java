package com.asset.controller;

import com.asset.bean.FormModelInfo;
import com.asset.bean.Resource;
import com.asset.bean.RespBean;
import com.asset.bean.Scene;
import com.asset.common.SystemConstant;
import com.asset.service.IResourceService;
import com.asset.service.ISceneService;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private IResourceService resourceService;

    /*@ApiOperation(value = "获取所有场景信息", notes = "获取所有场景信息",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "rest/scenes", method = RequestMethod.GET)
    public RespBean getAllScene(@ApiParam(value = "page", defaultValue = "1",required = true) @RequestParam(defaultValue = "1") Integer page
            , @ApiParam(value = "size", defaultValue = "1",required = true) @RequestParam(defaultValue = "20") Integer size){
        List<Scene> scenes = sceneService.getAllScenesByPage(page, size);
        if (Func.isNull(scenes)){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, scenes);
    }*/

    /**
     * 分页获取场景信息
     * @param page
     * @param size
     * @return RespBean
     */
    @ApiOperation(value = "获取所有场景信息", notes = "获取所有场景信息;page为起始页;size为每页显示数量"
            , tags = "开放api", httpMethod = "GET")
    @RequestMapping(value = "rest/scenes", method = RequestMethod.GET)
    public RespBean getAllSceneByPage(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam(defaultValue = "1") Integer page
            , @ApiParam(value = "size", defaultValue = "1", required = true) @RequestParam(defaultValue = "10") Integer size){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getAllScene();
        if (Func.isNull(scenes)){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return RespBean.ok(SystemConstant.GET_SUCCESS, scenePageInfo);
    }

    @ApiOperation(value = "添加菜单（表单类型）", notes = "传FormModelInfo实体与sceneId(param);",tags = "菜单", httpMethod = "POST")
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
    public RespBean addResource(@RequestBody FormModelInfo formModelInfo, @RequestParam("sceneId") String sceneId) throws CloneNotSupportedException {
        if (Func.hasEmpty(formModelInfo.getApplicationId(), formModelInfo.getFormModelId(), formModelInfo.getFormName()
                , formModelInfo.getIconCls(), formModelInfo.getGroupId(), formModelInfo.getGroupName(), sceneId)){
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
        resource.setSceneId(sceneId);
        resourceService.save(resource);
        resourceService.addResource4Admin(resource);
        resourceService.addFuncResource(resource);
        return RespBean.ok(SystemConstant.ADD_SUCCESS);
    }
}
