package cn.org.assetcloud.system.controller;

import cn.org.assetcloud.system.entity.OrganTree;
import cn.org.assetcloud.system.service.IOrganService;
import cn.org.assetcloud.system.service.ISceneRoleService;
import cn.org.assetcloud.system.service.ISceneService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", defaultValue = "0", required = true, dataType = "int")
    })
    @PostMapping(value = "/node/save")
    public R addNode(@RequestBody OrganTree organTree){
        if (Func.hasEmpty(organTree.getUnitName(), organTree.getParentId())){
            return R.fail("参数错误");
        }
        return R.data(organService.save(organTree));
    }

    @ApiOperation(value = "批量添加组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/nodes/save")
    public R addNodes(@Valid @RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.hasEmpty(organTree.getSort())){
                organTree.setSort(0);
            }
            organTree.setStatus(1);
        }

        return R.data(organService.saveBatch(organTrees));
    }

    @ApiOperation(value = "删除组织节点", notes = "删除组织节点")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @PostMapping(value = "/node/remove")
    public R deleteNode(String id){
    	return R.status(organService.removeById(id));
    }

    @ApiOperation(value = "批量删除组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "节点id数组, example:'id1,id2'", required = true, dataType = "string")
    })
    @PostMapping(value = "/nodes/remove")
    public R batchDeleteNode(String ids){
    	return R.status(organService.remove(Wrappers.<OrganTree>update().lambda()
			.in(OrganTree::getId, Func.toStrList(",", ids))));
    }

    @ApiOperation(value = "获取单个组织节点信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @GetMapping(value = "/node/detail")
    public R getNode(String id){
        return R.data(organService.getById(id));
    }

    @ApiOperation(value = "检索组织节点", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "组织名称", required = true, dataType = "string")
    })
    @GetMapping(value = "/node/search")
    public R getNodeByName(@ApiIgnore @RequestParam Map<String, Object> unitName){
		QueryWrapper<OrganTree> queryWrapper = Condition.getQueryWrapper(unitName, OrganTree.class);
        return R.data(organService.list(queryWrapper));
    }

    @ApiOperation(value = "编辑组织节点信息", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "string")
    })
    @PostMapping(value = "/node/update")
    public R editNode(@RequestBody OrganTree organTree){
        return R.data(organService.updateById(organTree));
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return R
     */
    @ApiOperation(value = "获取主组织树（用于场景的组织树编辑）", notes = "已完成")
    @ApiImplicitParam(value = "sceneId", name = "场景id", required = true)
    @GetMapping(value = "mainTree/checked")
    public R getTreeChecked(@RequestParam("sceneId") String sceneId){
        return R.data(organService.selectAllWithoutMerge(sceneId));
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return R
     */
    @ApiOperation(value = "获取主组织树", notes = "已完成")
    @RequestMapping(value = "mainTree", method = RequestMethod.GET)
    public R getOrganMainTree(){
        return R.data(organService.getMainTree());
    }

    @ApiOperation(value = "通过场景获取组织", notes = "已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping(value = "struct-by-scene")
    public R getOrgansByScene(@RequestParam("id") String id){
        return R.data(organService.getTreeByScene(id));
    }
}
