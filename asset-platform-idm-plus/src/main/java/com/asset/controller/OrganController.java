package com.asset.controller;

import com.asset.bean.*;
import com.asset.common.SystemConstant;
import com.asset.common.UserUtils;
import com.asset.service.IOrganService;
import com.asset.service.ISceneRoleService;
import com.asset.service.ISceneService;
import com.asset.service.IUserSceneService;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "organ")
public class OrganController {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrganController.class);

    @Autowired
    private IOrganService organService;

    @Autowired
    private ISceneService sceneService;

    @Autowired
    private ISceneRoleService sceneRoleService;

    @Autowired
    private IUserSceneService userSceneService;

    @ApiOperation(value = "添加组织节点", notes = "已完成",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public RespBean addNode(@RequestBody OrganTree organTree){
        if (Func.hasEmpty(organTree.getUnitName(), organTree.getParentId())){
            return RespBean.paramError();
        }
        if (Func.isNull(organTree.getSort())){
            organTree.setSort(0);
        }
        if (organService.nodeExists(organTree.getUnitName())){
            return RespBean.error(String.format("组织部门: %s 已存在", organTree.getUnitName()));
        }
        if (!organService.hasParent(organTree.getParentId())){
            return RespBean.error("目标父节点不存在");
        }
        organTree.setStatus(1);
        organTree.setIsDeleted(0);
        organTree.setEnableTime(new Date());
        return RespBean.data(organService.insert(organTree));
    }

    @ApiOperation(value = "批量添加组织节点", notes = "已完成",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "单位名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "parentId", value = "父节点id，若无则为\"\"", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序编号，默认为0", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/nodes", method = RequestMethod.POST)
    public RespBean addNodes(@Valid @RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.isNull(organTree.getSort())){
                organTree.setSort(0);
            }
            if (organService.nodeExists(organTree.getUnitName())){
                return RespBean.error(String.format("组织部门: %s 已存在", organTree.getUnitName()));
            }
            if (!organService.hasParent(organTree.getParentId())){
                return RespBean.error("目标父节点不存在");
            }
            organTree.setStatus(1);
            organTree.setIsDeleted(0);
            organTree.setEnableTime(new Date());
        }

        return RespBean.data(organService.batchAddNodes(organTrees));
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

    @ApiOperation(value = "批量删除组织节点", notes = "已完成",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id的List", required = true, dataTypeClass = java.util.List.class)
    })
    @RequestMapping(value = "/nodes", method = RequestMethod.DELETE)
    public RespBean batchDeleteNode(@RequestBody List<OrganTree> organTrees){
        for (OrganTree organTree : organTrees) {
            if (Func.isNull(organTree.getId())){
                return RespBean.paramError();
            }
            organTree.setDisableTime(new Date());
            organTree.setIsDeleted(1);
            organTree.setStatus(0);
        }
        return RespBean.data(organService.batchDeleteNode(organTrees));
    }

    @ApiOperation(value = "获取单个组织节点", notes = "已完成",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node/{id}", method = RequestMethod.GET)
    public RespBean getNode(@PathVariable String id){
        if (Func.isNull(id)){
            return RespBean.paramError();
        }
        return RespBean.data(organService.selectById(id));
    }

    @ApiOperation(value = "检索组织节点", notes = "已完成",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "unitName", value = "组织名称", required = true, dataTypeClass = java.lang.String.class)
    })
    @RequestMapping(value = "/node/search", method = RequestMethod.GET)
    public RespBean getNodeByName(@RequestParam("unitName") String unitName){
        List<OrganTree> organTrees = organService.searchNode(unitName);
        if (organTrees.size() == 0){
            return RespBean.ok(SystemConstant.NODE_NOT_FOUND);
        }
        return RespBean.ok(SystemConstant.GET_SUCCESS, organTrees);
    }

    @ApiOperation(value = "编辑组织节点信息", notes = "已完成",tags = "组织", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "节点id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/node", method = RequestMethod.PUT)
    public RespBean editNode(@RequestBody OrganTree organTree){
        if (Func.isNull(organTree.getId())){
            return RespBean.error("参数错误");
        }
        return RespBean.data(organService.updateById(organTree));
    }

    /**
     * 获取主组织树（需为管理员角色）
     * @return RespBean
     */
    @ApiOperation(value = "获取主组织树", notes = "已完成",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/mainTree", method = RequestMethod.GET)
    public RespBean getOrganMainTree(){
        List<Role> roles = UserUtils.getCurrentUser().getRoles();
        for (Role role : roles) {
            if (role.getId().equals(SystemConstant.ADMIN_ROLE_ID)) {
                //TODO:后续可以交由spring security处理
                // an administrator has access to main trees
                OrganTree organTree = organService.getMainTree();
                return RespBean.ok(SystemConstant.GET_SUCCESS, organTree);
            }
        }
        return RespBean.error(SystemConstant.GET_FAILURE, "");
    }

    @ApiOperation(value = "获取单个场景的基本信息", notes = "已完成",tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/scene", method = RequestMethod.GET)
    public RespBean getOneScene(@RequestParam("sceneId") String sceneId){
        return RespBean.data(sceneService.selectById(sceneId));
    }

//    @ApiOperation(value = "获取所有场景信息", notes = "",tags = "组织", httpMethod = "GET")
//    @RequestMapping(value = "/rest/scenes", method = RequestMethod.GET)
//    public RespBean getAllScenes(){
//        List<Scene> allScene = sceneService.getAllScene();
//        if (allScene == null){
//            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
//        }
//        return RespBean.ok(SystemConstant.GET_SUCCESS, allScene);
//    }

    @ApiOperation(value = "新增场景", notes = "（已完成）添加场景信息;传入Scene实体;sceneName必填",tags = "组织", httpMethod = "POST")
    @RequestMapping(value = "/scene", method = RequestMethod.POST)
    public RespBean addScene(@Valid @RequestBody Scene scene){
        if (sceneService.getSceneByName(scene.getSceneName()).size() > 0){
            //记录已存在
            return RespBean.error("场景名称已被使用");
        }
        return RespBean.data(sceneService.addSceneNormal(scene));
    }

    @ApiOperation(value = "删除场景", notes = "删除场景信息",tags = "组织", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/rest/scene", method = RequestMethod.DELETE)
    public RespBean deleteScene(@RequestBody Scene scene){
        int flag = sceneService.deleteScene(scene.getId());
        if (flag < 0){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        return RespBean.ok(SystemConstant.DELETE_SUCCESS);
    }

    @ApiOperation(value = "修改场景信息", notes = "已完成",tags = "组织", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneName", value = "新场景名称", required = true, dataType = "String"),
    })
    @RequestMapping(value = "/scene", method = RequestMethod.PUT)
    public RespBean modifyScene(@Valid @RequestBody Scene scene){
        if (Func.isNull(scene.getId())){
            return RespBean.paramError();
        }
        //根据场景名称的变化，判断是否需要对名称进行限制（防止重复）
        if (!scene.getSceneName().trim().equals(sceneService.selectById(scene.getId()).getSceneName())){
            if (sceneService.getSceneByName(scene.getSceneName()).size() > 0){
                //记录已存在
                return RespBean.error("场景名称已被使用，请更换后重试");
            }
        }
        return RespBean.data(sceneService.updateById(scene));
    }

    @ApiOperation(value = "获取所有场景信息（兼模糊查询）", notes = "（已完成）场景信息的模糊检索;page当前页数;size当前页数据量;sceneName在非模糊查询时置空即可",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",defaultValue = "1", value = "起始页", required = true, dataTypeClass = java.lang.Integer.class),
            @ApiImplicitParam(name = "size",defaultValue = "20", value = "每页数据量", required = true, dataTypeClass = java.lang.Integer.class),
            @ApiImplicitParam(name = "sceneName",defaultValue = "", value = "场景名称", dataTypeClass = java.lang.String.class)
    })
    @RequestMapping(value = "/scenes", method = RequestMethod.GET)
    public RespBean findScene(@RequestParam(value = "sceneName") String sceneName
            , @RequestParam("page") Integer page
            , @RequestParam("size") Integer size){
        if (Func.hasEmpty(sceneName)){
            sceneName = "";
        }
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.findSceneByNameAlike(sceneName);
        if (Func.isNull(scenes)){
            return RespBean.ok(SystemConstant.SCENE_NOT_FOUND);
        }
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return RespBean.data(scenePageInfo);
    }

    @ApiOperation(value = "通过场景获取组织", notes = "已完成",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "场景id", required = true, dataType = "String")
    })
    @RequestMapping(value = "scene/{id}", method = RequestMethod.GET)
    public RespBean getOrgansByScene(@PathVariable String id){
        if (Func.isNull(id)){
            return RespBean.error("参数错误");
        }
        return RespBean.data(organService.getTreeByScene(id));
    }

    @ApiOperation(value = "通过场景获取所属用户（不依赖组织树显示）", notes = "已完成",tags = "组织", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sceneId", value = "场景id", required = true, dataType = "String")
    })
    @GetMapping("scene/users")
    public RespBean getUsersUnderScene(@RequestParam("sceneId")String sceneId){
        //TODO:获取场景下用户，依赖组织树显示，另起一个接口
        return RespBean.data(organService.getUsersByScene(sceneId));
    }

    @ApiOperation(value = "向场景中增加用户（批量）", notes = "",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id(实体)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sceneId", value = "场景id(param)", required = true, dataType = "String")
    })
    @PostMapping("scene/addUsers")
    public RespBean addUsersToScene(@RequestBody List<UserScene> users, @RequestParam("sceneId") String sceneId){
        if (Func.isNull(sceneId)){
            return RespBean.paramError();
        }
        //获取该场景下的默认角色
        SceneRole defaultRole = sceneRoleService.getDefaultRole(sceneId);
        for (UserScene userScene : users) {
            if (Func.hasEmpty(userScene.getUserId())){
                return RespBean.paramError();
            }
            userScene.setSceneId(sceneId);
            userScene.setStatus(1);
            //设置为默认角色
            userScene.setRoleId(defaultRole.getId());
        }
        //TODO:明天改为真批量增
        return RespBean.data(userSceneService.insertBatch(users));
    }

    @ApiOperation(value = "向场景中新增组织部门", notes = "",tags = "组织", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organScenes", value = "场景-组织的对应数据", required = true, dataType = "List<OrganScene>")
    })
    @RequestMapping(value = "/scene/addNode", method = RequestMethod.PUT)
    public RespBean changeOrganTree(@RequestBody List<OrganScene> organSceneList){
        //TODO:编辑组织树，存在则不更新，不存在则插入
        for (OrganScene organScene : organSceneList) {
            LOGGER.info(organScene.toString());
        }
        return RespBean.ok("ok");
    }

    @ApiOperation(value = "获取用户场景", notes = "根据用户获取场景;page起始页;size每页数据量", tags = "组织", httpMethod = "GET")
    @RequestMapping(value = "/scenes/user", method = RequestMethod.GET)
    public RespBean getUserScenes(@ApiParam(value = "page", defaultValue = "1", required = true) @RequestParam(defaultValue = "1") Integer page
            , @ApiParam(value = "size", defaultValue = "10", required = true) @RequestParam(defaultValue = "10") Integer size
            , @ApiParam(value = "userId", required = true) @RequestParam String userId){
        PageHelper.startPage(page, size);
        List<Scene> scenes = sceneService.getScenesByUser(userId);
        if (Func.isNull(scenes)){
            return RespBean.error(SystemConstant.SYSTEM_FAILURE);
        }
        PageInfo<Scene> scenePageInfo = new PageInfo<>(scenes);
        return RespBean.ok(SystemConstant.GET_SUCCESS, scenePageInfo);
    }

    @ApiOperation(value = "场景信息判空", notes = "场景信息判空;sceneId场景id",tags = "组织",httpMethod = "GET")
    @GetMapping("scene/empty")
    public RespBean isEmptyScene(@RequestParam(value = "sceneId") String sceneId){
        if (sceneService.isSceneEmpty(sceneId)){
            return RespBean.ok("", true);
        }
        return RespBean.ok("", false);
    }
}
