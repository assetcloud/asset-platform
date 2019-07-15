package com.asset.entity;

import com.asset.form.ItemRuleBase;
import lombok.Data;

/**
 * 表示是否表单项中填写内容格式的Rule
 * @author YBY
 * @time 190712
 * @version 1.0_190712
 */
@Data
public class ItemRuleType extends ItemRuleBase {
    String type;
}
