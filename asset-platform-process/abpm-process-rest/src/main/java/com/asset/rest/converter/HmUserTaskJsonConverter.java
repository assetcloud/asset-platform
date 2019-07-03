package com.asset.rest.converter;

import com.asset.rest.utils.ExtensionAttributeUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ExtensionAttribute;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.flowable.editor.language.json.converter.UserTaskJsonConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichao
 *
 * 直接扩展节点属性
 */
public class HmUserTaskJsonConverter extends UserTaskJsonConverter {

    @Override
    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        super.convertElementToJson(propertiesNode, baseElement);
    }

    @Override
    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode,Map<String, JsonNode> shapeMap) {
        FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap);
        UserTask userTask = (UserTask) flowElement;
        //设置人员参数变量
        userTask.setAssignee("${userids}");
        Map<String, List<ExtensionAttribute>> attributesMap = new HashMap<String, List<ExtensionAttribute>>();
        List<ExtensionAttribute> attributesList = new ArrayList<ExtensionAttribute>();
        //自定义扩展属性-人员
        JsonNode usernameJsonNode = elementNode.findValue("usernames");
        System.out.println(usernameJsonNode !=null);
        if(usernameJsonNode != null) {
            String usernames = elementNode.findValue("usernames").asText();
            if(StringUtils.isNotBlank(usernames)) {
                String userids = elementNode.findValue("userids").asText();
                ExtensionAttribute ea1 = ExtensionAttributeUtils.generate("usernames",usernames);
                attributesList.add(ea1);
                ExtensionAttribute ea2 = ExtensionAttributeUtils.generate("userids",userids);
                attributesList.add(ea2);
            }
        }
        //自定义扩展属性-角色
        if(elementNode.findValue("rolenames") != null) {
            String rolenames = elementNode.findValue("rolenames").asText();
            if(StringUtils.isNotBlank(rolenames)) {
                String roleids = elementNode.findValue("roleids").asText();
                ExtensionAttribute ea1 = ExtensionAttributeUtils.generate("rolenames",rolenames);
                attributesList.add(ea1);
                ExtensionAttribute ea2 = ExtensionAttributeUtils.generate("roleids",roleids);
                attributesList.add(ea2);
            }
        }

        //自定义扩展属性-组织
        if(elementNode.findValue("orgnames")!=null) {
            String orgnames = elementNode.findValue("orgnames").asText();
            if(StringUtils.isNotBlank(orgnames)) {
                String orgids = elementNode.findValue("orgids").asText();
                ExtensionAttribute ea1 = ExtensionAttributeUtils.generate("orgnames",orgnames);
                attributesList.add(ea1);
                ExtensionAttribute ea2 = ExtensionAttributeUtils.generate("orgids",orgids);
                attributesList.add(ea2);
            }
        }
        if(attributesList!=null&&attributesList.size()>0) {
            attributesMap.put("flowable-ext",attributesList);
            userTask.setAttributes(attributesMap);
        }
        return flowElement;
    }
}