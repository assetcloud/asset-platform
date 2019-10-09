package com.asset.javabean.form;

import lombok.Data;

/**
 * 在FormItemBase中的rules对象中出现的
 * 用来指定表单项的属性，如：填写类型、是否必填等
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
@Data
public class ItemRuleBase {
    String message; //当前规则不满足输出的信息
}
