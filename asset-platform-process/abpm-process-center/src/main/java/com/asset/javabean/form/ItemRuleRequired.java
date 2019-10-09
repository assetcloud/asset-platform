package com.asset.javabean.form;

import lombok.Data;

/**
 * 表示是否必填的表单项Rule
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
@Data
public class ItemRuleRequired extends ItemRuleBase {
    Boolean required;

    public ItemRuleRequired(String message) {
        super.message = message;
        required = true;
    }
}
