package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsFormInst;
import com.asset.rec.FormInstCommitRec;
import com.asset.service.impl.FormInstServiceImpl;
import com.asset.utils.Constants;
import com.asset.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

    @ApiOperation(value = "在用户的应用界面，选择该应用下一个表单，点新增之后，填写完表单，接着点击 提交 发送的请求" ,
            notes = "" , httpMethod = "POST")
    @RequestMapping(value = "/form/inst/commit", method = RequestMethod.POST)
    public String commitFormInst(@RequestBody FormInstCommitRec rec){
        int code = formInstService.commitFormInst(rec);

        return JsonUtils.getCodeJson(code);
    }

    /**
     * 用户登录系统之后，点击主页面的待办窗口中任一一项，返回对应的表单实例信息
     * @param userID
     * @param form_status
     * @return
     */
    @RequestMapping(value = "/form/inst/get",method = RequestMethod.GET)
    public String getFormInsts(@RequestParam(value = "user_id") String userID,
                               //当前要请求的表单的状态，这里只做一个接收，但先不处理
                              @RequestParam(value = "form_status") Integer form_status)
    {
        ArrayList<AsFormInst> asFormInsts = (ArrayList<AsFormInst>) formInstService.getFormInsts(userID,form_status);

        int code = Constants.CODE_SUCCESS;
        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", code);
        map.put("list", asFormInsts);
        Object json = JSONObject.toJSON(map);
        return json.toString();
    }
}
