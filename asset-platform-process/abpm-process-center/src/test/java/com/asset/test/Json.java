package com.asset.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.FlowableApplication;
import com.asset.converter.FormConverter;
import com.asset.entity.FormInst;
import com.asset.entity.OptionsBase;
import com.asset.form.FormItem;
import com.asset.form.FormJsonEntity;
import com.asset.form.ItemRuleRequired;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class Json {

    @Autowired
    protected ModelService modelService;

    @Test
    public void testJson(){
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        try{
            int curActType = map.get("sasa");
        }catch (NullPointerException e)
        {
            System.out.println("请检查");
        }

    }

    @Test
    public void testModel(){
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = engine.getRepositoryService();
        RuntimeService runtimeService = engine.getRuntimeService();
        TaskService taskService = engine.getTaskService();

        String procModelId = "0";
        org.flowable.ui.modeler.domain.Model modelData = modelService.getModel(procModelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(modelData);
    }

    @Test
    public void testFormConverter(){
        String formJson = "{\n" +
                "\t\"list\": [{\n" +
                "\t\t\"type\": \"input\",\n" +
                "\t\t\"name\": \"单行文本\",\n" +
                "\t\t\"icon\": \"icon-input\",\n" +
                "\t\t\"options\": {\n" +
                "\t\t\t\"width\": \"100%\",\n" +
                "\t\t\t\"defaultValue\": \"\",\n" +
                "\t\t\t\"required\": true,\n" +
                "\t\t\t\"dataType\": \"string\",\n" +
                "\t\t\t\"pattern\": \"\",\n" +
                "\t\t\t\"placeholder\": \"\",\n" +
                "\t\t\t\"disabled\": true,\n" +
                "\t\t\t\"remoteFunc\": \"func_1562899646000_93408\",\n" +
                "\t\t\t\"disabled\": true\n" +
                "\t\t},\n" +
                "\t\t\"key\": \"1562899646000_93408\",\n" +
                "\t\t\"model\": \"input_1562899646000_93408\",\n" +
                "\t\t\"rules\": []\n" +
                "\t}],\n" +
                "\t\"config\": {\n" +
                "\t\t\"labelWidth\": 100,\n" +
                "\t\t\"labelPosition\": \"right\",\n" +
                "\t\t\"size\": \"small\"\n" +
                "\t}\n" +
                "}";

//        JSONObject jsonobject = JSON.parseObject(formJson);



        FormJsonEntity entity = FormConverter.jsonToEntity(formJson);
        List<FormItem> list = entity.getList();
        FormItem item = list.get(0);

        List<JSONObject> rules = new ArrayList<>();
        ItemRuleRequired required = new ItemRuleRequired("此项必填");
        JSONObject object = (JSONObject) JSON.toJSON(required);
        rules.add(object);

        item.setRules(rules);

        String formJson2 = FormConverter.entityToJson(entity);


//        int num=0;
//        for(int i =0;i<list.size();i++)
//        {
//            List<JSONObject> list1 = list.get(i).getRules();
//            for(int j=0;j<list1.size();j++)
//            {
//                boolean isContain = list1.get(j).containsKey("required");
//                if(isContain)
//                    num++;
//            }
//        }

    }

    @Test
    public void test1(){
        JSONObject json = new JSONObject();
        json.put("aa", "11");
        json.put("bb", "22");
        json.put("cc", "33");
        String jsonStr = json.toString();
        System.out.println(json.get("aa"));

        JSON.parseObject(json.toJSONString(), FormInst.class);

        Map map = new HashMap();
        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");
        String json1=JSON.toJSONString(map);
        System.out.println(json1);

    }


    @Test
    public void test2(){



//        Map map = new HashMap();
//        map.put("inline",false);
//        map.put("disabled",true);
//        map.put("sss",true);

        String a = "{\n" +
                "\t\t\t\"inline\": false,\n" +
                "\t\t\t\"defaultValue\": \"\",\n" +
                "\t\t\t\"showLabel\": false,\n" +
                "\t\t\t\"options\": [{\n" +
                "\t\t\t\t\"value\": \"选项1\",\n" +
                "\t\t\t\t\"label\": \"选项1\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"value\": \"选项2\",\n" +
                "\t\t\t\t\"label\": \"选项2\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"value\": \"选项3\",\n" +
                "\t\t\t\t\"label\": \"选项3\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"required\": true,\n" +
                "\t\t\t\"width\": \"\",\n" +
                "\t\t\t\"remote\": false,\n" +
                "\t\t\t\"remoteOptions\": [],\n" +
                "\t\t\t\"props\": {\n" +
                "\t\t\t\t\"value\": \"value\",\n" +
                "\t\t\t\t\"label\": \"label\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"remoteFunc\": \"func_1562899347000_74608\"\n" +
                "\t\t}";

        OptionsBase optionsBase = JSON.parseObject(a, OptionsBase.class);

        optionsBase.setRequired(false);
        optionsBase.setDisabled(false);

        String sa = JSON.toJSONString(optionsBase);

    }



}
