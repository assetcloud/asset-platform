package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.FormModelInfo;
import com.asset.rec.*;
import com.asset.service.impl.FormModelServiceImpl;
import com.asset.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 表单模型的创建、修改、获取、以及权限的存取
 * 表单模型与流程部署绑定
 *
 * @author yby
 * @version 1.1_190603 0908
 * @time 190522 1056
 */
@RestController
@Api(value = "表单模型接口")
public class FormModelController {
    final static Logger LOGGER = LoggerFactory.getLogger(FormModelController.class);

    @Autowired
    FormModelServiceImpl formModelService;

    /**
     * 表单创建、存储
     */
    @ApiOperation(value = "表单创建、存储", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/form/model/create", method = RequestMethod.POST)
    public String createFormModel(@RequestBody FormModelCreateRec rec) throws JsonProcessingException {
        FormModelInfo curFormModel = formModelService.createFormModel(rec);
        int code = 0;

        if (curFormModel.getForm_model_id().equals(""))
            code = 1;

        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", code);
        map.put("data", curFormModel);

        Object jsonOut = JSONObject.toJSON(map);

        return jsonOut.toString();
    }


    /**
     * 表单模型修改
     *
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form/model/edit", method = RequestMethod.PUT)
    public String editFormModel(@RequestBody FormModelEditRec rec) throws JsonProcessingException {
        int code = formModelService.editFormModel(rec);

        return JsonUtils.getCodeJson(code);
    }


    /**
     * 绑定流程模型和表单模型
     */
    @RequestMapping(value = "/form/model/bind", method = RequestMethod.PUT)
    public String bindFormModel(@RequestBody FormModelBindRec rec) throws JsonProcessingException {
        int code = formModelService.bindFormModel(rec);

        return JsonUtils.getCodeJson(code);
    }



    /**
     * 根据传入的OAppID获取该应用下所有表单模型
     */
    @RequestMapping(value = "/form/models/get", method = RequestMethod.GET)
    public String getFormModels(@RequestParam(value = "oapp_id") String oappID) throws JsonProcessingException {
        ArrayList<FormModelInfo> formModelInfos = (ArrayList<FormModelInfo>) formModelService.getFormModels(oappID);
        int code = 0;
        if (formModelInfos.size() != 0)
            code = 1;

        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("code", code);
        map.put("list", formModelInfos);

        Object json = JSONObject.toJSON(map);
        return json.toString();
    }

    /**
     * 在流程设计页面，对表单项权限信息进行存储
     */
    @RequestMapping(value = "/form/authority/save", method = RequestMethod.POST)
    public String saveFormModelAuthority(@RequestBody FormAuthorityRec authorityRec) throws JsonProcessingException {
        int code = formModelService.saveFormModelAuthority(authorityRec);

        return JsonUtils.getCodeJson(code);
    }


    /**
     * 后台自动完成：
     * 表单模型与流程部署绑定，用于在流程实例运行时能找到对应的表单模型，然后生成表单实例
     */
    @ApiOperation(value = "用于表单模型与流程部署绑定", notes = "表单模型与流程部署绑定", httpMethod = "POST")
    @RequestMapping(value = "/form/deploy/bind", method = RequestMethod.POST)
    public String formDeployBind(@RequestBody FormDeployBindRec formDeployBindRec) throws JsonProcessingException {
        int code = formModelService.formBindDeploy(formDeployBindRec);

        return JsonUtils.getCodeJson(code);
    }

    /**
     * 设置表单属于OApp中的哪一个组
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form/model/setgroup", method = RequestMethod.PUT)
    public String setFormGroup(@RequestBody FormGroupRec rec) throws JsonProcessingException {
        int code = formModelService.setFormGroup(rec);

        return JsonUtils.getCodeJson(code);
    }


}
