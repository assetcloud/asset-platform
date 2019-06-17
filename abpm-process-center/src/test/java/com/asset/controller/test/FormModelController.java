//package com.asset.controller.test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.asset.FlowableApplication;
//import com.asset.dao.FormModelMapper;
//import com.asset.entity.FormModelInfo;
//import com.asset.service.FormModelService;
//import com.asset.utils.FormIDGenerator;
//import com.asset.utils.IDGenerator;
//import com.asset.utils.ProcInstUtils;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.flowable.task.api.Task;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 用于测试创建流程实例，然后创建表单与之相绑定的操作
// * @author yby
// * @time 190522 1408
// * @version 1.0_190522 1408
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FlowableApplication.class)
//public class FormModelController {
//
//    Logger logger = LoggerFactory.getLogger(FormModelController.class);
//
//    @Autowired
//    FormModelMapper formModelMapper;
//    @Autowired
//    FormModelService formService;
//
//
//    @Test
//    public void testID(){
//        IDGenerator generator = new FormIDGenerator();
//        System.out.println(generator.generateID());
//    }
//
//
//
//    @Test
//    public void getFormModel(){
//        String modelJson = "form1559530852151";
//
//        FormModelInfo ret = formModelMapper.getFormModel(modelJson);
//
//        System.out.println("ret is "+ret.toString());
//    }
//
//    @Test
//    public void bindForm(){
//        String formModelID = "1";
//        String procModelID = "05ba9630-7794-11e9-a7cf-2e15a8856301";
//
//        int code = formService.bindFormModel(formModelID,procModelID);
//        System.out.println("code is "+code);
//    }
//
////    @Test
////    public void saveAuthority(){
////        FormAuthorityRec formModelAuthority = new FormAuthorityRec("form5215108fd151",
////                "866ba8856301-866b-11e9-9089-2e15a8856301",
////                "student_leave",
////                "Student Wang",
////        "{\"list\":[{\"type\":\"input\",\"name\":\"单行文本\",\"icon\":\"icon-input\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"dataType\":\"string\",\"pattern\":\"\",？？？\"placeholder\":\"\",提示\"remoteFunc\":\"func_1559093913000_64187\"},\"key\":\"1559093913000_64187\",\"model\":\"input_1559093913000_64187\",\"rules\":[{\"type\":\"string\",\"message\":\"单行文本格式不正确\"}]},{\"type\":\"textarea\",\"name\":\"多行文本\",\"icon\":\"icon-diy-com-textarea\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"disabled\":false,\"pattern\":\"\",\"placeholder\":\"\",\"remoteFunc\":\"func_1559093915000_54488\"},\"key\":\"1559093915000_54488\",\"model\":\"textarea_1559093915000_54488\",\"rules\":[]},{\"type\":\"number\",\"name\":\"计数器\",\"icon\":\"icon-number\",\"options\":{\"width\":\"\",\"required\":false,\"defaultValue\":0,\"min\":0,\"max\":0,\"step\":1,\"disabled\":false,\"controlsPosition\":\"\",\"remoteFunc\":\"func_1559093917000_59875\"},\"key\":\"1559093917000_59875\",\"model\":\"number_1559093917000_59875\",\"rules\":[]},{\"type\":\"radio\",\"name\":\"单选框组\",\"icon\":\"icon-radio-active\",\"options\":{\"inline\":false,\"defaultValue\":\"\",\"showLabel\":false,\"options\":[{\"value\":\"选项1\",\"label\":\"选项1\"},{\"value\":\"选项2\",\"label\":\"选项2\"},{\"value\":\"选项3\",\"label\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093919000_99333\"},\"key\":\"1559093919000_99333\",\"model\":\"radio_1559093919000_99333\",\"rules\":[]},{\"type\":\"checkbox\",\"name\":\"多选框组\",\"icon\":\"icon-check-box\",\"options\":{\"inline\":false,\"defaultValue\":[],\"showLabel\":false,\"options\":[{\"value\":\"选项1\"},{\"value\":\"选项2\"},{\"value\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093939000_1748\"},\"key\":\"1559093939000_1748\",\"model\":\"checkbox_1559093939000_1748\",\"rules\":[]},{\"type\":\"time\",\"name\":\"时间选择器\",\"icon\":\"icon-time\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"isRange\":false,\"arrowControl\":true,\"format\":\"HH:mm:ss\",\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093942000_49623\"},\"key\":\"1559093942000_49623\",\"model\":\"time_1559093942000_49623\",\"rules\":[]},{\"type\":\"date\",\"name\":\"日期选择器\",\"icon\":\"icon-date\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"type\":\"date\",\"format\":\"yyyy-MM-dd\",\"timestamp\":false,\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093945000_4839\"},\"key\":\"1559093945000_4839\",\"model\":\"date_1559093945000_4839\",\"rules\":[]},{\"type\":\"select\",\"name\":\"下拉选择框\",\"icon\":\"icon-select\",\"options\":{\"defaultValue\":\"\",\"multiple\":false,\"disabled\":false,\"clearable\":false,\"placeholder\":\"\",\"required\":false,\"showLabel\":false,\"width\":\"\",\"options\":[{\"value\":\"下拉框1\"},{\"value\":\"下拉框2\"},{\"value\":\"下拉框3\"}],\"remote\":false,\"filterable\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093951000_24131\"},\"key\":\"1559093951000_24131\",\"model\":\"select_1559093951000_24131\",\"rules\":[]},{\"type\":\"rate\",\"name\":\"评分\",\"icon\":\"icon-icon-test\",\"options\":{\"defaultValue\":0,\"max\":5,\"disabled\":false,\"allowHalf\":false,\"required\":false,\"remoteFunc\":\"func_1559093947000_88214\"},\"key\":\"1559093947000_88214\",\"model\":\"rate_1559093947000_88214\",\"rules\":[]},{\"type\":\"switch\",\"name\":\"开关\",\"icon\":\"icon-switch\",\"options\":{\"defaultValue\":false,\"required\":false,\"disabled\":false,\"remoteFunc\":\"func_1559093953000_73568\"},\"key\":\"1559093953000_73568\",\"model\":\"switch_1559093953000_73568\",\"rules\":[]},{\"type\":\"slider\",\"name\":\"滑块\",\"icon\":\"icon-slider\",\"options\":{\"defaultValue\":0,\"disabled\":false,\"required\":false,\"min\":0,\"max\":100,\"step\":1,\"showInput\":false,\"range\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093955000_23561\"},\"key\":\"1559093955000_23561\",\"model\":\"slider_1559093955000_23561\",\"rules\":[]},{\"type\":\"imgupload\",\"name\":\"图片\",\"icon\":\"icon-tupian\",\"options\":{\"defaultValue\":[],\"size\":{\"width\":100,\"height\":100},\"width\":\"\",\"tokenFunc\":\"funcGetToken\",\"token\":\"\",\"domain\":\"http://pfp81ptt6.bkt.clouddn.com/\",\"disabled\":false,\"length\":8,\"multiple\":false,\"isQiniu\":false,\"isDelete\":false,\"min\":0,\"isEdit\":false,\"action\":\"https://jsonplaceholder.typicode.com/photos/\",\"remoteFunc\":\"func_1559093958000_41914\"},\"key\":\"1559093958000_41914\",\"model\":\"imgupload_1559093958000_41914\",\"rules\":[]}],\"config\":{\"labelWidth\":100,\"labelPosition\":\"top\",\"size\":\"small\"}}";
////        int code = 0;
////        for(int i=0;i<1;i++)
////        {
////            code = formService.saveFormModelAuthority(formModelAuthority);
////        }
////        Object json = JSONObject.toJSON(code);
////        System.out.println("code is "+json.toString());
////    }
//
//
//    @Test
//    public void getFormModels(){
//        ArrayList<FormModelInfo> formModelInfos = (ArrayList<FormModelInfo>) formService.getFormModels();
//        int code = 0;
//        if (formModelInfos.size()!=0)
//            code = 1;
//
//        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
//        map.put("code",code);
//        map.put("list",formModelInfos);
//
//        Object json = JSONObject.toJSON(map);
//
//        System.out.println("json is "+json.toString());
//    }
//
//
//    /**
//     * 测试创建表单并执行
//     */
//    @Test
//    public void testFormExecute(){
//        //消息传递，直接用的是这个
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("days", "5");
//        Task task = ProcInstUtils.getCurTask("b35ea73c-7c55-11e9-bf85-2e15a8856301");
//        ProcInstUtils.complete("b35ea73c-7c55-11e9-bf85-2e15a8856301",map);
//    }
//
//
//
//
//    //    }
////        logger.info("formModelInfo is {}", formModelInfo.toString());
////
////        FormModelInfo formModelInfo = formService.getFormInst(taskID);
////        String taskID = "modelid2";
////    public void getFormInst() throws JsonProcessingException {
////    @Test
////     */
////     * @throws JsonProcessingException
////     * 测试获取表单实例
////    /**
////    /**
////     * 保存表单信息到数据库
////     */
////    @Test
////    public void testSaveForm(){
////        String formJson = "abc";
////        String formName = "testForm";
////        String modelID = "b220b2cf-7b9d-11e9-a762-2e15a8856301";
////        FormModelInfo formModelInfo = new FormModelInfo(formJson,modelID,formName);
////        int ret = formModelMapper.createFormModel(formModelInfo);
////        logger.info("ret : {}",ret);
////    }
//
//    @Test
//    public void insertModel(){
//        String modelJson = "{\"list\":[{\"type\":\"input\",\"name\":\"单行文本\",\"icon\":\"icon-input\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"dataType\":\"string\",\"pattern\":\"\",？？？\"placeholder\":\"\",提示\"remoteFunc\":\"func_1559093913000_64187\"},\"key\":\"1559093913000_64187\",\"model\":\"input_1559093913000_64187\",\"rules\":[{\"type\":\"string\",\"message\":\"单行文本格式不正确\"}]},{\"type\":\"textarea\",\"name\":\"多行文本\",\"icon\":\"icon-diy-com-textarea\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"disabled\":false,\"pattern\":\"\",\"placeholder\":\"\",\"remoteFunc\":\"func_1559093915000_54488\"},\"key\":\"1559093915000_54488\",\"model\":\"textarea_1559093915000_54488\",\"rules\":[]},{\"type\":\"number\",\"name\":\"计数器\",\"icon\":\"icon-number\",\"options\":{\"width\":\"\",\"required\":false,\"defaultValue\":0,\"min\":0,\"max\":0,\"step\":1,\"disabled\":false,\"controlsPosition\":\"\",\"remoteFunc\":\"func_1559093917000_59875\"},\"key\":\"1559093917000_59875\",\"model\":\"number_1559093917000_59875\",\"rules\":[]},{\"type\":\"radio\",\"name\":\"单选框组\",\"icon\":\"icon-radio-active\",\"options\":{\"inline\":false,\"defaultValue\":\"\",\"showLabel\":false,\"options\":[{\"value\":\"选项1\",\"label\":\"选项1\"},{\"value\":\"选项2\",\"label\":\"选项2\"},{\"value\":\"选项3\",\"label\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093919000_99333\"},\"key\":\"1559093919000_99333\",\"model\":\"radio_1559093919000_99333\",\"rules\":[]},{\"type\":\"checkbox\",\"name\":\"多选框组\",\"icon\":\"icon-check-box\",\"options\":{\"inline\":false,\"defaultValue\":[],\"showLabel\":false,\"options\":[{\"value\":\"选项1\"},{\"value\":\"选项2\"},{\"value\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093939000_1748\"},\"key\":\"1559093939000_1748\",\"model\":\"checkbox_1559093939000_1748\",\"rules\":[]},{\"type\":\"time\",\"name\":\"时间选择器\",\"icon\":\"icon-time\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"isRange\":false,\"arrowControl\":true,\"format\":\"HH:mm:ss\",\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093942000_49623\"},\"key\":\"1559093942000_49623\",\"model\":\"time_1559093942000_49623\",\"rules\":[]},{\"type\":\"date\",\"name\":\"日期选择器\",\"icon\":\"icon-date\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"type\":\"date\",\"format\":\"yyyy-MM-dd\",\"timestamp\":false,\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093945000_4839\"},\"key\":\"1559093945000_4839\",\"model\":\"date_1559093945000_4839\",\"rules\":[]},{\"type\":\"select\",\"name\":\"下拉选择框\",\"icon\":\"icon-select\",\"options\":{\"defaultValue\":\"\",\"multiple\":false,\"disabled\":false,\"clearable\":false,\"placeholder\":\"\",\"required\":false,\"showLabel\":false,\"width\":\"\",\"options\":[{\"value\":\"下拉框1\"},{\"value\":\"下拉框2\"},{\"value\":\"下拉框3\"}],\"remote\":false,\"filterable\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093951000_24131\"},\"key\":\"1559093951000_24131\",\"model\":\"select_1559093951000_24131\",\"rules\":[]},{\"type\":\"rate\",\"name\":\"评分\",\"icon\":\"icon-icon-test\",\"options\":{\"defaultValue\":0,\"max\":5,\"disabled\":false,\"allowHalf\":false,\"required\":false,\"remoteFunc\":\"func_1559093947000_88214\"},\"key\":\"1559093947000_88214\",\"model\":\"rate_1559093947000_88214\",\"rules\":[]},{\"type\":\"switch\",\"name\":\"开关\",\"icon\":\"icon-switch\",\"options\":{\"defaultValue\":false,\"required\":false,\"disabled\":false,\"remoteFunc\":\"func_1559093953000_73568\"},\"key\":\"1559093953000_73568\",\"model\":\"switch_1559093953000_73568\",\"rules\":[]},{\"type\":\"slider\",\"name\":\"滑块\",\"icon\":\"icon-slider\",\"options\":{\"defaultValue\":0,\"disabled\":false,\"required\":false,\"min\":0,\"max\":100,\"step\":1,\"showInput\":false,\"range\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093955000_23561\"},\"key\":\"1559093955000_23561\",\"model\":\"slider_1559093955000_23561\",\"rules\":[]},{\"type\":\"imgupload\",\"name\":\"图片\",\"icon\":\"icon-tupian\",\"options\":{\"defaultValue\":[],\"size\":{\"width\":100,\"height\":100},\"width\":\"\",\"tokenFunc\":\"funcGetToken\",\"token\":\"\",\"domain\":\"http://pfp81ptt6.bkt.clouddn.com/\",\"disabled\":false,\"length\":8,\"multiple\":false,\"isQiniu\":false,\"isDelete\":false,\"min\":0,\"isEdit\":false,\"action\":\"https://jsonplaceholder.typicode.com/photos/\",\"remoteFunc\":\"func_1559093958000_41914\"},\"key\":\"1559093958000_41914\",\"model\":\"imgupload_1559093958000_41914\",\"rules\":[]}],\"config\":{\"labelWidth\":100,\"labelPosition\":\"top\",\"size\":\"small\"}}";
//        String[] formName = {"form_test","form_test1","form_test2","form_test3","form_test4"};
//        String[] createdBy = {"Student Wang","Teacher Li","Teacher Wang","Teacher Wang","Teacher Wang"};
//        for(int i = 0;i<5;i++) {
//            FormModelInfo ret = formService.createFormModel(modelJson,createdBy[i],formName[i]);
//        }
//    }
//
//    /**
//     * 测试jsonObject
//     */
//    @Test
//    public void testJson(){
//        String modelJson = "{sasasa}";
//        String formName = "2";
//        String createBy ="3sasasas";
//
//        FormModelInfo curFormModel = formService.createFormModel(modelJson,createBy,formName);
//        int code = 0;
//
//        if(curFormModel.getForm_model_id().equals(""))
//            code = 1;
//
//        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
//        map.put("node",1);
//        map.put("data",curFormModel);
//
//        Object json = JSONObject.toJSON(map);
//
//        System.out.println("json is "+json.toString());
//    }
//
//
//    /**
//     * 测试对象转json
//     */
//    @Test
//    public void getForm() throws JsonProcessingException {
//        FormModelInfo formModelInfo = new FormModelInfo("json","modelID","FORMnAME");
//
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(formModelInfo);
//        logger.debug("json: {}",json);
//    }
//
//
//}
