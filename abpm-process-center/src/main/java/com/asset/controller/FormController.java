package com.asset.controller;

import com.asset.entity.Code;
import com.asset.entity.JsonForm;
import com.asset.service.impl.FormRepositoryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 把pqq创建的第三方表单存入数据库、取出数据库的操作
 * @author yby
 * @time 190522 1056
 * @version 1.0_190522 1056
 */
@RestController
public class FormController {

    @Autowired
    FormRepositoryServiceImpl formService;

    /**
     * 把第三方表单以json格式存入数据库，与对应流程模型绑定
     */
    @RequestMapping(value = "/form/model/save", method = RequestMethod.POST)
    public String saveForm(@RequestParam(value = "formjson") String formjson,
                         @RequestParam(value = "formname") String formName,
                         @RequestParam(value = "modelid") String modelID) throws JsonProcessingException {
        int temp = formService.saveForm(formjson,modelID,formName);

        Code code = new Code(temp);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(code);
    }


    /**
     * 得到流程模型对应的第三方表单信息
     */
    @RequestMapping(value = "/form/model/get/{modelID}", method = RequestMethod.GET)
    public String getForm(@PathVariable String modelID) throws JsonProcessingException {
        JsonForm jsonForm = formService.getForm(modelID);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonForm);
    }


    /**
     * 执行流程实例的时候,填写完表单，点击“完成当前任务”时 执行该提交表单方法
     */
    @RequestMapping(value = "/form/inst/save", method = RequestMethod.POST)
    public String saveFormInst(@RequestParam(value = "formname") String formname,
                               @RequestParam(value = "taskid") String taskid,
                               @RequestParam(value = "formjson") String formjson,
                               @RequestParam(value = "procinstid") String procinstid,
                               @RequestParam(value = "procdefid") String procdefid,
                               @RequestParam(value = "modelid") String modelid) throws JsonProcessingException {
        int temp = formService.saveFormInst(formname,procinstid,formjson,procdefid,modelid,taskid);

        Code code = new Code(temp);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(code);
    }

    /**
     * 得到流程实例中上一个执行的任务节点时填写的表单实例信息
     * @param taskID 上一个执行的任务节点的taskID
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/form/inst/get/{taskID}", method = RequestMethod.GET)
    public String getFormInst(@PathVariable String taskID) throws JsonProcessingException {
        JsonForm jsonForm = formService.getFormInst(taskID);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonForm);
    }






}
