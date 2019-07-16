package com.asset.controller;

import com.asset.bean.RespBean;
import com.asset.bean.Scene;
import com.asset.common.SystemConstant;
import com.asset.service.SceneService;
import com.asset.utils.Func;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    SceneService sceneService;

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
}
