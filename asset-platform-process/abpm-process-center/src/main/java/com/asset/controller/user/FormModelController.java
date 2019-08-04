package com.asset.controller.user;

import com.asset.exception.DatabaseException;
import com.asset.javabean.FormModelBO;
import com.asset.javabean.RespBean;
import com.asset.dto.*;
import com.asset.service.FormModelService;
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
    FormModelService formModelService;

    /**
     * 表单创建、存储
     */
    @ApiOperation(value = "表单创建、存储", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/form_model/save", method = RequestMethod.POST)
    public RespBean createFormModel(@RequestBody FormModelCreateDTO dto)  {
        FormModelBO formModelBO = null;
        try {
            formModelBO = formModelService.createFormModel(dto);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return RespBean.error(databaseException.getMessage());
        }

        return RespBean.ok("", formModelBO);
    }


    /**
     * 表单模型修改
     * @param dto
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form_model/update", method = RequestMethod.PATCH)
    public RespBean updateFormModel(@RequestBody FormModelEditDTO dto) {
        try {
            formModelService.updateFormModel(dto);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return RespBean.error(databaseException.getMessage());
        }

        return RespBean.ok("");
    }


    /**
     * 绑定流程模型和表单模型
     */
    @RequestMapping(value = "/form_model/bind", method = RequestMethod.PATCH)
    public RespBean bindFormModel(@RequestParam(value = "form_model_id") String formModelId,
                                  @RequestParam(value = "proc_model_id") String procModelId)  {
        try {
            formModelService.bindFormAndProcModel(formModelId,procModelId);
        } catch (DatabaseException databaseException) {
            databaseException.printStackTrace();
            return RespBean.error(databaseException.getMessage());
        }

        return RespBean.ok("");
    }

    /**
     * 在应用页面或者在管理工厂页面 获取当前页面应该显示的表单模型
     * @param appId
     * @param groupId 传入的值为-1时表示不对分组进行限制，某一个具体值表示只筛选这个分组的表单模型
     * @param formStatus -1:全部 0:还没和流程模型绑定  1:和流程模型绑定  2:已删除
     * @return
     */
    @RequestMapping(value = "/form_model/models",method = RequestMethod.GET)
    public RespBean getFormModels(@RequestParam(value = "app_id")String appId,
                                @RequestParam(value = "group_id")int groupId,
                                @RequestParam(value = "form_status")int formStatus
                                ){
        List<FormModelBO> formModelDOS = formModelService.getFormModels(appId,groupId,formStatus);
        return RespBean.ok("", formModelDOS);
    }


    /**
     * 获取绑定的流程模型id
     * @param formModelID
     * @return
     */
    @RequestMapping(value = "/form_model/proc_model_id",method = RequestMethod.GET)
    public RespBean getBindProc(@RequestParam(value = "form_model_id")String formModelID)
    {
        String procModelID = formModelService.getProcModelID(formModelID);
        if(procModelID.equals("null"))
            return RespBean.error("表单模型未绑定流程模型！");
        return RespBean.ok("",procModelID);
    }
}
