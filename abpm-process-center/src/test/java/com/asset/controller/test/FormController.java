package com.asset.controller.test;

import com.asset.FlowableApplication;
import com.asset.dao.FormMapper;
import com.asset.entity.JsonForm;
import com.asset.service.FormRepositoryService;
import com.asset.utils.ProcInstUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于测试创建流程实例，然后创建表单与之相绑定的操作
 * @author yby
 * @time 190522 1408
 * @version 1.0_190522 1408
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class FormController {

    Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    FormMapper formMapper;
    @Autowired
    FormRepositoryService formRepositoryService;


    /**
     * 测试创建表单并执行
     */
    @Test
    public void testFormExecute(){
        //消息传递，直接用的是这个
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("days", "5");
        Task task = ProcInstUtils.getCurTask("b35ea73c-7c55-11e9-bf85-2e15a8856301");
        ProcInstUtils.complete("b35ea73c-7c55-11e9-bf85-2e15a8856301",map);
    }


    /**
     * 保存表单信息到数据库
     */
    @Test
    public void testSaveForm(){
        String formJson = "abc";
        String formName = "testForm";
        String modelID = "b220b2cf-7b9d-11e9-a762-2e15a8856301";
        JsonForm jsonForm = new JsonForm(formJson,modelID,formName);
        int ret = formMapper.saveForm(jsonForm);
        logger.info("ret : {}",ret);
    }


    /**
     * 测试对象转json
     */
    @Test
    public void getForm() throws JsonProcessingException {
        JsonForm jsonForm = new JsonForm("json","modelID","FORMnAME");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jsonForm);
        logger.debug("json: {}",json);
    }

    /**
     * 测试获取表单实例
     * @throws JsonProcessingException
     */
    @Test
    public void getFormInst() throws JsonProcessingException {
        String taskID = "modelid2";
        JsonForm jsonForm = formRepositoryService.getFormInst(taskID);

        logger.info("jsonForm is {}",jsonForm.toString());
    }

}
