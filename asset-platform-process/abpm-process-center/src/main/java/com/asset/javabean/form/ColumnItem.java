package com.asset.javabean.form;

import lombok.Data;

import java.util.List;

/**
 * 用来表示在栅格表单项 columns中的内容
 *   {        "span": 12,
 *           "list": []
 *    }
 */
@Data
public class ColumnItem {
    private List<FormItem> list;
    Object span;
}
