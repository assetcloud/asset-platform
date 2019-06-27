package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.OrganService;
import com.asset.service.SceneService;
import io.swagger.annotations.*;
import io.undertow.util.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/organ")
public class OrganController {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrganController.class);

    @Autowired
    OrganService organService;

    @Autowired
    SceneService sceneService;

    @ApiOperation(value = "添加组织节点", notes = "添加组织节点",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为0", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public RespBean addNode(@RequestBody OrganTree organTree){
        int flag = organService.addNode(organTree);
        if (flag == SystemConstant.RECORD_ALREADY_EXISTS){
            return RespBean.error(SystemConstant.ADD_FAILURE,"NODE_ALREADY_EXISTS");
        } else if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE,"SYSTEM_ERROR");
        }
        return RespBean.ok(SystemConstant.ADD_SUCCESS);
    }

    @ApiOperation(value = "删除组织节点", notes = "删除组织节点",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.DELETE)
    public RespBean deleteNode(@RequestBody OrganTree organTree){
        int flag = organService.deleteNode(organTree);
        if (flag == SystemConstant.SYSTEM_ERROR){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "获取单个组织节点", notes = "获取单个组织节点",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public RespBean getNode(@PathVariable String id){
        OrganTree node = organService.getNode(id);
        if (node == null){
            return RespBean.error(SystemConstant.GET_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, node);
    }

    @ApiOperation(value = "编辑组织节点信息", notes = "编辑组织节点信息",tags = "组织", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.PUT)
    public RespBean editNode(@RequestBody OrganTree organTree){
        int flag = organService.updateNode(organTree);
        if (flag < 0){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.UPDATE_SUCCESS);
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return RespBean
     */
    @ApiOperation(value = "获取主组织树", notes = "获取主组织树（仅限管理员访问）",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/mainTree", method = RequestMethod.GET)
    public RespBean getOrganMainTree(){
        List<Role> roles = UserUtils.getCurrentUser().getRoles();
        for (Role role : roles) {
            LOGGER.info(role.toString());
            if (role.getId().equals(SystemConstant.ADMIN_ROLE_ID)) {
                //TODO:后续可以交由spring security处理
                // an administrator has access to main trees
                List<OrganTree> mainTrees = organService.getMainTree();
                return RespBean.ok(SystemConstant.GET_SUCCESS, mainTrees);
            }
        }
        return RespBean.error(SystemConstant.GET_FAILURE, new ArrayList<>());
    }

    @ApiOperation(value = "获取场景信息", notes = "获取场景信息",tags = "组织", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200,message = "获取成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scenes", method = RequestMethod.GET)
    public RespBean getAllScenes(){
        List<Scene> allScene = sceneService.getAllScene();
        if (allScene == null){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, allScene);
    }

    @ApiOperation(value = "新增场景", notes = "添加场景信息",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneName", value = "场景名称", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "添加成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.POST)
    public RespBean addScene(@RequestBody Scene scene){
        int flag = sceneService.addScene(scene);
        if (flag == SystemConstant.RECORD_ALREADY_EXISTS){
            return RespBean.error(SystemConstant.SCENE_ALREADY_EXISTS);
        } else if (flag < 0) {
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.ADD_SUCCESS);
    }

    @ApiOperation(value = "删除场景", notes = "删除场景信息",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "删除成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.DELETE)
    public RespBean deleteScene(@RequestBody Scene scene){
        int flag = sceneService.deleteScene(scene.getId());
        if (flag < 0){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "修改场景名称", notes = "修改场景名称",tags = "组织", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneName", value = "新场景名称", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "修改成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.PUT)
    public RespBean modifyScene(@RequestBody Scene scene){
        int flag = sceneService.updateSceneInfo(scene);
        if (flag < 0){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.UPDATE_SUCCESS);
    }

    @ApiOperation(value = "检索场景", notes = "场景信息的模糊检索",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneName", value = "场景名称", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "检索成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.GET)
    public RespBean findScene(@RequestBody Scene scene){
        List<Scene> scenes = sceneService.findSceneByNameAlike(scene.getSceneName());
        if (scenes == null){
            return RespBean.ok(SystemConstant.SCENE_NOT_FOUND);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, scenes);
    }

    @ApiOperation(value = "通过场景获取组织", notes = "通过场景获取对应的组织树",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "获取成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/scene/{id}", method = RequestMethod.GET)
    public RespBean getOrgansByScene(@PathVariable String id) throws BadRequestException {
        if (id == null){
            throw new BadRequestException("场景id为空");
        }
        List<OrganTree> organTree = organService.getTreeByScene(id);
        if (organTree == null){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, organTree);
    }

    @ApiOperation(value = "修改场景组织", notes = "编辑指定场景下的组织树",tags = "组织", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organScenes", value = "场景-组织的对应数据", required = true, dataType = "List<OrganScene>")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "保存成功",response = RespBean.class),
            @ApiResponse(code = 500,message = "系统错误",response = RespBean.class)
    })
    @RequestMapping(value = "/rest/edit", method = RequestMethod.PUT)
    public RespBean changeOrganTree(@RequestBody List<OrganScene> organSceneList){
        //TODO:编辑组织树，存在则不更新，不存在则插入
        for (OrganScene organScene : organSceneList) {
            LOGGER.info(organScene.toString());
        }
        return RespBean.ok("ok");
    }
}
