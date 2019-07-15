package com.asset.controller;

import com.asset.dao.AsFormModelMapper;
import com.asset.entity.AsFormModel;
import com.asset.javabean.RespBean;
import com.asset.rec.*;
import com.asset.service.FormModelService;
import com.asset.utils.Constants;
import com.asset.utils.JsonUtils;
import com.asset.utils.RecUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    AsFormModelMapper asFormModelMapper;
    @Autowired
    FormModelService formModelService;

    /**
     * 表单创建、存储
     */
    @ApiOperation(value = "表单创建、存储", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/form/model/create", method = RequestMethod.POST)
    public RespBean createFormModel(@RequestBody FormModelCreateRec rec) throws JsonProcessingException {
        AsFormModel curFormModel = formModelService.createFormModel(rec);

        return RespBean.ok("",curFormModel);
    }


    /**
     * 表单模型修改
     *
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form/model/edit", method = RequestMethod.PUT)
    public RespBean editFormModel(@RequestBody FormModelEditRec rec) throws JsonProcessingException {
        AsFormModel asFormModel = new AsFormModel(rec);
        int i= asFormModelMapper.editFormModel(asFormModel);
        if(i==0)
            return RespBean.error("修改失败！");

        return RespBean.ok("");
    }


    /**
     * 绑定流程模型和表单模型
     */
    @RequestMapping(value = "/form/model/bind", method = RequestMethod.PUT)
    public RespBean bindFormModel(@RequestBody FormModelBindRec rec) throws JsonProcessingException {
        formModelService.bindFormModel(rec);

        return RespBean.ok("");
    }

    /**
     * 在应用页面或者在管理工厂页面 获取当前页面应该显示的表单模型
     * @param appId
     * @param userId
     * @param groupId
     * @param formStatus
     * @return
     */
    @RequestMapping(value = "/form/models/get",method = RequestMethod.GET)
    public RespBean getFormModels(@RequestParam(value = "app_id")String appId,
                                @RequestParam(value = "user_id")String userId,
                                @RequestParam(value = "group_id")int groupId,
                                @RequestParam(value = "form_status")int formStatus
                                ){
//        if(RecUtils.check(appId,groupId,formStatus))
//            return RespBean.error()
        List<AsFormModel> asFormModels = formModelService.getFormModels(appId,userId,groupId,formStatus);
        return RespBean.ok("", asFormModels);
    }




    //-------------注意这里设置表单分组和上面对表单模型进行修改逻辑上是有重复的，要不要合并再说
    /**
     * 设置表单属于OApp中的哪一个组
     * @param rec
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form/model/setgroup", method = RequestMethod.PUT)
    public RespBean setFormGroup(@RequestBody FormGroupRec rec) throws JsonProcessingException {
        AsFormModel info = new AsFormModel(rec.getForm_model_id(),rec.getGroup_id());
        int i =asFormModelMapper.setFormGroup(info);
        if(i== Constants.DATABASE_FAILED)
            return RespBean.error("修改失败！");
        return RespBean.ok("");
    }


    @RequestMapping(value = "/form/model/getBindProc",method = RequestMethod.GET)
    public RespBean getBindProc(@RequestParam(value = "form_model_id")String formModelID)
    {
        String procModelID = formModelService.getProcModelID(formModelID);

        return RespBean.ok("",procModelID);
    }


}
