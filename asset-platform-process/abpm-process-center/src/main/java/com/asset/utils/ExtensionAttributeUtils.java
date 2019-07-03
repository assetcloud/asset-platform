package com.asset.utils;

import org.flowable.bpmn.model.ExtensionAttribute;

/**
 * @author lichao
 */
public class ExtensionAttributeUtils{

    public static ExtensionAttribute generate(String key,String val) {
        ExtensionAttribute ea = new ExtensionAttribute();
        ea.setName(key);
        ea.setValue(val);
        ea.setNamespace(Constants.NAMESPACE);
        ea.setNamespacePrefix(Constants.NAMESPACE_PREFIX);
        return ea;
    }
}
