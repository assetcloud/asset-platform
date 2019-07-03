package com.asset.controller;

import com.asset.entity.AsFormInst;
import com.asset.rec.FormInstApproveRec;
import com.asset.rec.FormInstCommitRec;
import com.asset.service.impl.FormInstServiceImpl;
import com.asset.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 表单实例的创建、修改，表单项值在流程引擎中的使用
 * @author YBY
 * @time 190610_1040
 * @
 */
@RestController
@Api(value = "表单实例创建")
public class FormInstController {

    @Autowired
    FormInstServiceImpl formInstService;

    /**
     * 用户登录应用界面，填写表单，发起一个新的表单流程
     * @param rec
     * @return
     */
    @ApiOperation(value = "在用户的应用界面，选择该应用下一个表单，点新增之后，填写完表单，接着点击 提交 发送的请求" ,
            notes = "" , httpMethod = "POST")
    @RequestMapping(value = "/form/inst/commit", method = RequestMethod.POST)
    public String commitFormInst(@RequestBody FormInstCommitRec rec){
        int code = formInstService.commitFormInst(rec);

        return JsonUtils.getCodeJson(code);
    }

    /**
     * 用户登录系统之后，根据传进来的任务类型不同，前台显示待办（包含审批、经办）/待阅/全部的表单信息
     * 这里的全部节点信息还包含了历史的处理信息，这里先不考虑
     * @param userID
     * @param taskType
     * @return
     */
    @RequestMapping(value = "/form/inst/show",method = RequestMethod.GET)
    public String getFormInsts(@RequestParam(value = "user_id") String userID,
                              @RequestParam(value = "task_type") Integer taskType)
    {
        ArrayList<AsFormInst> asFormInsts = (ArrayList<AsFormInst>) formInstService.getFormInsts(userID,taskType);
        return JsonUtils.procListToString(asFormInsts);
    }


    /**
     * 用户登录系统，查看自己的待审批任务，点击 同意 或 拒绝
     */
    @RequestMapping(value = "/form/inst/approve",method = RequestMethod.POST)
    public String approveFormInst(@RequestBody FormInstApproveRec rec)
    {
        int code = formInstService.approveFormInst(rec);

        return JsonUtils.getCodeJson(code);
    }
}
