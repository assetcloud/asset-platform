package com.asset.controller;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsFormModel;
import com.asset.rec.*;
import com.asset.service.impl.FormModelServiceImpl;
import com.asset.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 表单模型的创建、修改、获取、以及权限的存取
 * 表单模型与流程部署绑定
 *
 * @author yby
 * @version 1.2_190617 1608
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
        AsFormModel curFormModel = formModelService.createFormModel(rec);
        int code = 0;

        if (curFormModel.getId().equals(""))
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
    @RequestMapping(value = "/form/model/bind", method = RequestMethod.POST)
    public String bindFormModel(@RequestBody FormModelBindRec rec) throws JsonProcessingException {
        int code = formModelService.bindFormModel(rec);

        return JsonUtils.getCodeJson(code);
    }



    //    -------------注意这里设置表单分组和上面对表单模型进行修改逻辑上是有重复的，要不要合并再说
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


// ----------------------------------------------表单项权限设置，暂时先不搞-----------------------------------
    //    /**
//     * 在流程设计页面，对表单项权限信息进行存储
//     */
//    @RequestMapping(value = "/form/authority/save", method = RequestMethod.POST)
//    public String saveFormModelAuthority(@RequestBody FormAuthorityRec authorityRec) throws JsonProcessingException {
//        int code = formModelService.saveFormModelAuthority(authorityRec);
//
//        return JsonUtils.getCodeJson(code);
//    }
//    /**
//     * 保存表单模型权限信息
//     * @param formModelAuthority
//     * @return
//     */
//    @Override
//    public int saveFormModelAuthority(FormAuthorityRec formModelAuthority) {
//        FormAuthorityInfo authorityInfo = new FormAuthorityInfo(formModelAuthority);
//        System.out.println(formModelAuthority.toString());
//        return asFormModelMapper.saveFormModelAuthority(authorityInfo);
//    }
    //    /**
//     * 在流程模型表中绑定对应的表单模型
//     * @param bindInfo
//     * @return
//     */
//    int bindFormModel(AsFormModel bindInfo);
//  <!--&lt;!&ndash; 在流程模型表中绑定对应的表单模型-->
//       <!--@param FormBindInfo bindInfo &ndash;&gt;-->
//  <!--<update id="bindFormModel" parameterType="com.asset.entity.AsFormModel">-->
//        <!--UPDATE-->
//        <!--act_de_model-->
//        <!--SET-->
//        <!--form_id = #{formModelID,jdbcType = VARCHAR}-->
//	    <!--WHERE-->
//	    <!--id = #{procModelID,jdbcType = VARCHAR};-->

//    <!--</update>-->


}
