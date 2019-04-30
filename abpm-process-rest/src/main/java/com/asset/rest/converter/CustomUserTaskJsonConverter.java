package com.asset.rest.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.CustomProperty;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.flowable.editor.language.json.converter.UserTaskJsonConverter;

import java.util.Map;

/**
 * @author lichao
 *
 * 利用UserTask自带的扩展属性
 */
public class CustomUserTaskJsonConverter extends UserTaskJsonConverter {


	@Override
	protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode,Map<String, JsonNode> shapeMap) {
		FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap);
		UserTask userTask = (UserTask) flowElement;
		// 将自己的属性添加到activiti自带的自定义属性中
		CustomProperty customProperty = new CustomProperty();
		customProperty.setName("rolenames");
		customProperty.setSimpleValue(this.getPropertyValueAsString("rolenames", elementNode));
		userTask.getCustomProperties().add(customProperty);
		return flowElement;
	}

	@Override
	protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
		super.convertElementToJson(propertiesNode, baseElement);
	}
}