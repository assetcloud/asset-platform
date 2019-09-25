package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.GlobalConstant;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.*;
import com.asset.utils.Func;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "organ")
@Api(value = "组织结构管理", tags = "组织结构管理")
public class OrganController {

    IOrganService organService;

    ISceneService sceneService;

    ISceneRoleService sceneRoleService;

    @ApiOperation(value = "添加组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", defaultValue = "0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public R addNode(@RequestBody OrganTree organTree){
        if (Func.hasEmpty(organTree.getUnitName(), organTree.getParentId())){
            return R.fail("参数错误");
        }
        if (organService.nodeExists(organTree.getUnitName())){
            return R.fail(String.format("组织部门: %s 已存在", organTree.getUnitName()));
        }
        if (!organService.hasParent(organTree.getParentId())){
            return R.fail("目标父节点不存在");
        }
        return R.data(organService.addNode(organTree));
    }

    @ApiOperation(value = "批量添加组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/nodes", method = RequestMethod.POST)
    public R addNodes(@Valid @RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.isNull(organTree.getSort())){
                organTree.setSort(0);
            }
            if (organService.nodeExists(organTree.getUnitName())){
                return R.fail(String.format("组织部门: %s 已存在", organTree.getUnitName()));
            }
            if (!organService.hasParent(organTree.getParentId())){
                return R.fail("目标父节点不存在");
            }
            organTree.setStatus(1);
            organTree.setIsDeleted(0);
            organTree.setEnableTime(new Date());
        }

        return R.data(organService.batchAddNodes(organTrees));
    }

    @ApiOperation(value = "删除组织节点", notes = "删除组织节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.DELETE)
    public R deleteNode(@RequestBody OrganTree organTree){
        int flag = organService.deleteNode(organTree);
        if (flag == SystemConstant.SYSTEM_ERROR){
            return R.fail(SystemConstant.SYSTEM_FAILURE);
        }
        return R.success(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "批量删除组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id的List", required = true, dataTypeClass = java.util.List.class)
    })
    @RequestMapping(value = "/nodes", method = RequestMethod.DELETE)
    public R batchDeleteNode(@RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.isNull(organTree.getId())){
                return R.fail("参数错误");
            }
        }
        return R.data(organService.batchDeleteNode(organTrees));
    }

    @ApiOperation(value = "获取单个组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public R getNode(@PathVariable String id){
        if (Func.isNull(id)){
            return R.fail("参数错误");
        }
        return R.data(organService.getById(id));
    }

    @ApiOperation(value = "检索组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "组织名称", required = true, dataTypeClass = java.lang.String.class)
    })
    @RequestMapping(value = "/node/search", method = RequestMethod.GET)
    public R getNodeByName(@RequestParam("unitName") String unitName){
        List<OrganTree> organTrees = organService.searchNode(unitName);
        if (organTrees.size() == 0){
            return R.fail(SystemConstant.NODE_NOT_FOUND);
        }
        return R.data(organTrees);
    }

    @ApiOperation(value = "编辑组织节点信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.PUT)
    public R editNode(@RequestBody OrganTree organTree){
        if (Func.isNull(organTree.getId())){
            return R.fail("参数错误");
        }
        return R.data(organService.updateById(organTree));
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return R
     */
    @ApiOperation(value = "获取主组织树（用于场景的组织树编辑）", notes = "已完成")
    @ApiImplicitParam(value = "sceneId", name = "场景id", required = true)
    @RequestMapping(value = "/mainTree/checked", method = RequestMethod.GET)
    public R getTreeChecked(@RequestParam("sceneId") String sceneId){
        return R.data(organService.selectAllWithoutMerge(sceneId));
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

    @ApiOperation(value = "获取单个场景的基本信息", notes = "已完成")
    @RequestMapping(value = "/scene", method = RequestMethod.GET)
    public R getOneScene(@RequestParam("sceneId") String sceneId){
        return R.data(sceneService.getById(sceneId));
    }

    @ApiOperation(value = "新增场景", notes = "（已完成）添加场景信息;传入Scene实体;sceneName必填")
    @RequestMapping(value = "/scene", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "sceneName", required = true, name = "场景名称")
    })
    @Transactional
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
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.DELETE)
    public R deleteScene(@RequestBody Scene scene){
        int flag = sceneService.deleteScene(scene.getId());
        if (flag < 0){
            return R.fail(SystemConstant.SYSTEM_FAILURE);
        }
        return R.success(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "修改场景信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneName", value = "新场景名称", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/scene", method = RequestMethod.PUT)
    public R modifyScene(@Valid @RequestBody Scene scene){
        if (Func.isNull(scene.getId())){
            return R.fail("参数错误");
        }
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
            @ApiImplicitParam(name = "page", defaultValue = "1", value = "起始页", required = true, dataTypeClass = java.lang.Integer.class),
            @ApiImplicitParam(name = "size", defaultValue = "20", value = "每页数据量", required = true, dataTypeClass = java.lang.Integer.class),
            @ApiImplicitParam(name = "sceneName", value = "场景名称", dataTypeClass = java.lang.String.class)
    })
    @RequestMapping(value = "/scenes", method = RequestMethod.GET)
    public R findScene(@RequestParam(value = "sceneName") String sceneName
            , @RequestParam("page") Integer page
            , @RequestParam("size") Integer size){
        if (Func.hasEmpty(sceneName)){
            sceneName = "";
        }
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.findSceneByNameAlike(sceneName);
        if (Func.isNull(scenes)){
            return R.success(SystemConstant.SCENE_NOT_FOUND);
        }
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return R.data(scenePageInfo);
    }

    @ApiOperation(value = "通过场景获取组织", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "scene/{id}", method = RequestMethod.GET)
    public R getOrgansByScene(@PathVariable String id){
        if (Func.isNull(id)){
            return R.fail("参数错误");
        }
        return R.data(organService.getTreeByScene2(id));
    }
}
