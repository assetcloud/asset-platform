package com.asset.controller.test;

import com.asset.FlowableApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用于测试创建流程实例，然后创建表单与之相绑定的操作
 * @author yby
 * @time 190531 1219
 * @version 1.0_190531 1219
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class FormAuthority {

    @Autowired
    protected ModelService modelService;

    @Autowired
    protected ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(FormAuthority.class);


    @Test
    public void testActRuVariable(){

    }




    /**
     * 测试怎么获取流程模型中的一个节点（不是运行的时候）
     */
    @Test
    public void getModelNode(){
        String modelId = "5e407653-8361-11e9-a356-2e15a8856301";
        Model model = modelService.getModel(modelId);
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put("modelId", model.getId());
        modelNode.put("name", model.getName());
        modelNode.put("key", model.getKey());
        modelNode.put("description", model.getDescription());
        modelNode.putPOJO("lastUpdated", model.getLastUpdated());
        modelNode.put("lastUpdatedBy", model.getLastUpdatedBy());
        if (StringUtils.isNotEmpty(model.getModelEditorJson())) {
            try {
                ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
                editorJsonNode.put("modelType", "model");
                modelNode.set("model", editorJsonNode);
            } catch (Exception e) {
                logger.error("Error reading editor json {}", modelId, e);
                throw new InternalServerErrorException("Error reading editor json " + modelId);
            }

        }
        //如果editorjson为空
        else {
            ObjectNode editorJsonNode = objectMapper.createObjectNode();
            editorJsonNode.put("id", "canvas");
            editorJsonNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorJsonNode.put("modelType", "model");
            modelNode.set("model", editorJsonNode);
        }

        System.out.println(modelNode.toString());
    }
}
