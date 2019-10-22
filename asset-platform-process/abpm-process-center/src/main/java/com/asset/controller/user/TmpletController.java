package com.asset.controller.user;

import com.asset.entity.AsTempletDO;
import com.asset.service.impl.AsTempletServiceImpl;
import com.asset.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "终端：管理应用模板资源")
@RequestMapping(value = "/templet")
@RestController
public class TmpletController {

    @Autowired
    AsTempletServiceImpl asAppTempletService;

    @ApiOperation(value = "获取模板列表", notes = "显示所有预置好的模板信息（不包含所属的模板资源）", httpMethod = "GET")
    @GetMapping(value = "/list/all")
    public R<List<AsTempletDO>> listAppTemplets() {
        List<AsTempletDO> asTempletDOS = asAppTempletService.listAll();
        return R.data(asTempletDOS);
    }

    @ApiOperation(value = "导入选中的模板", notes = "导入选中的模板资源", httpMethod = "POST")
    @PostMapping(value = "/lead_into")
    public R leadIntoTemplet(
            @ApiParam(value = "选中的应用模板的Id", required = true)
            @RequestParam(value = "templet_id") String templetId,
            @ApiParam(value = "当前登录用户Id", required = true)
            @RequestParam(value = "user_id") String userId,
            @ApiParam(value = "当前登录用户选择的工作场景Id", required = true)
            @RequestParam(value = "scene_id") String sceneId) {

        try {
            asAppTempletService.leadIntoTemplet(templetId, userId ,sceneId);
        }catch (RuntimeException e)
        {
            return R.fail(e.getMessage());
        }
        return R.success("导入成功！");
    }

    @ApiOperation(value = "发布新模板", notes = "选择创建完成的表单流程，发布为新的模板", httpMethod = "POST")
    @PostMapping(value = "/create")
    public R createTemplet(
            @ApiParam(value = "当前发布模板的用户Id", required = true)
            @RequestParam(value = "user_id") String userId,
            @ApiParam(value = "当前要发布的模板的表单模型Id", required = true)
            @RequestParam(value = "form_model_id") String formModelId,
            @ApiParam(value = "发布时设定的模板名称", required = true)
            @RequestParam(value = "templet_name") String templetName,
            @ApiParam(value = "发布时设定的模板图标", required = true)
            @RequestParam(value = "icon_cls") String iconCls) {

        try {
            asAppTempletService.createTemplet(userId,formModelId,templetName,iconCls);
        }catch (Exception e)
        {
            return R.fail(e.getMessage());
        }
        return R.success("发布成功！");
    }

}
