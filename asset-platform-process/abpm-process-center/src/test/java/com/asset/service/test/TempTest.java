package com.asset.service.test;

import com.alibaba.fastjson.JSONObject;
import com.asset.FlowableApplication;
import com.asset.entity.ActRuVariableDO;
import com.asset.service.impl.ActRuVariableService;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import lombok.Data;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.json.Json;

import javax.swing.text.html.parser.Entity;
import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class TempTest {

    @Autowired
    ActRuVariableService actRuVariableService;


    @Test
    public void testInComing() {
         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
         RepositoryService repositoryService = processEngine.getRepositoryService();
         RuntimeService runtimeService = processEngine.getRuntimeService();
         TaskService taskService = processEngine.getTaskService();

         String defId = "testExeKey:1:9c267343-af54-11e9-a0fd-2e15a8856301";

        BpmnModel bpmnModel = repositoryService.getBpmnModel(defId);
        Process process = bpmnModel.getProcesses().get(0);
        Collection<FlowElement> flowElements = process.getFlowElements();
        for (FlowElement flowElement : flowElements) {
            SequenceFlow sequenceFlow = (SequenceFlow) flowElement;
            sequenceFlow.setConditionExpression("");

            if (flowElement instanceof UserTask ) {
                UserTask u = (UserTask) flowElement;
                if(u.getId().equals("u2.2.1"))
                {
                    List<SequenceFlow> incomingFlows = u.getIncomingFlows();
                    for(SequenceFlow s :incomingFlows){
                        System.out.println("source:" + s.getSourceFlowElement().getId());
                    }
                }
            }

        }

    }

    @Test
    public void testJson(){

        String formValue = "{\"seq1\":\"${test==1}\",\"seq2\":\"${test==2}\"}";
        JSONObject JSON = JSONObject.parseObject(formValue);
        for(String key:JSON.keySet()){
            System.out.println(key + ":" +JSON.get(key));
        }
    }

    @Test
    public void testQuery(){
        ActRuVariableDO doo = new ActRuVariableDO.Builder()
                .procInstId("sa")
                .text("sa")
                .executionId("sa").build();

        actRuVariableService.ifContain(doo);
    }
    private static final Configuration configuration = Configuration.builder()
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .build();
    @Test
    public void testJsonPath(){
        String modelEditorJson = "{\"modelId\":\"0d9e221c-cd6c-11e9-bf9e-0215030df0c2\",\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"properties\":{\"process_id\":\"ceshiScrip\",\"name\":\"测试脚本\",\"process_potentialstarteruser\":\"\",\"dataproperties\":\"\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"process_namespace\":\"http://www.flowable.org/processdef\"},\"childShapes\":[{\"resourceId\":\"startEvent1\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"initiator\":\"\",\"executionlisteners\":\"\",\"formkeydefinition\":\"\",\"formreference\":\"\",\"formproperties\":\"\",\"documentation\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":130,\"y\":193},\"upperLeft\":{\"x\":100,\"y\":163}},\"dockers\":[]},{\"resourceId\":\"sid-92C89E16-98B3-4D39-8C2A-544391B6DEB3\",\"properties\":{\"scriptformat\":\"\",\"scripttext\":\"\",\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\"},\"stencil\":{\"id\":\"ScriptTask\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":340,\"y\":206.4910687260886},\"upperLeft\":{\"x\":240,\"y\":126.4910687260886}},\"dockers\":[]}],\"stencil\":{\"id\":\"BPMNDiagram\"},\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\",\"url\":\"../editor/stencilsets/bpmn2.0/bpmn2.0.json\"}}";
        String replace = modelEditorJson.replace("\"stencil\":{\"id\":\"ScriptTask\"}", "\"stencil\":{\"id\":\"UserTask\"}");

        //        String strRes = "StartNoneEvent";
//        while(modelEditorJson.indexOf(strRes)!=-1) {
//            int i = modelEditorJson.indexOf(strRes);
//            modelEditorJson.rep
//            str = str.substring(i+1);
//        }
        //        DocumentContext ext = JsonPath.parse(modelEditorJson);
//        JsonPath p = JsonPath.compile("$..stencil[?(@.id==\"StartNoneEvent\")]");      //根据该节点找到属于该节点的map集合
//        HashMap<String,String> tes1 = new HashMap<>();
//        tes1.put("id","UsER");
//
//        ext.set(p,tes1);    //替换集合中该子节点"remark"的值为classMap.get("remark")得到的值
//        p.isDefinite();
//        String json = ext.jsonString();
//        ext.set(p,tes1);    //替换集合中该子节点"remark"的值为classMap.get("rema
//        json = ext.jsonString();// rk")得到的值
//        ext.set(p,tes1);    //替换集合中该子节点"remark"的值为classMap.get("remark")得到的值
//        json = ext.jsonString();// rk")得到的值
//
//        String json2 = JsonPath.using(configuration).parse(modelEditorJson).set(p, "MYSESSINID").jsonString();


        //        JsonPath p = JsonPath
//        DocumentContext context = JsonPath.parse(modelEditorJson);
//        JsonPath p = JsonPath.compile("$..stencil");
//
//
//        Object id = p.read("id");
//
//
//        List<LinkedHashMap> pp = context.read("$..stencil");
//        for(LinkedHashMap linkedHashMap:pp)
//        {
//            String value= (String) linkedHashMap.get("id");
//            context.set("new",)

//            if(jsonObject.get("id").equals("UserTask"))
//            {
//                Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
//                for(Map.Entry<String, Object> map :entries)
//                {
//
//                }
//            }

//        }
    }

    @Test
    public void testArray(){
        Students students = new Students();
        ArrayList<Student> s = students.getStudents();
        s.remove(0);
        System.out.println("size+"+students.getStudents().size());

        Student s1 = s.get(0);
        s1.setI(2121);




    }




}
