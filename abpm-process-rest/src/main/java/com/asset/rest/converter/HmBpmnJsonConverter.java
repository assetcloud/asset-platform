package com.asset.rest.converter;

import org.flowable.bpmn.model.BaseElement;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;

import java.util.Map;

/**
 * @author lichao
 */
public class HmBpmnJsonConverter extends BpmnJsonConverter {

    /**
     * 通过继承开放convertersToJsonMap的访问
     * @return convertersToJsonMap
     */
    public static Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> getConvertersToJsonMap() {
        return convertersToJsonMap;
    }

    /**
     * 通过继承开放convertersToJsonMap的访问
     * @return convertersToBpmnMap
     */
    public static Map<String, Class<? extends BaseBpmnJsonConverter>> getConvertersToBpmnMap() {
        return convertersToBpmnMap;
    }
}
