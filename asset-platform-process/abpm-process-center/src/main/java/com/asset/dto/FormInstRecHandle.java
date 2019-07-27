package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

/**
 * 经办节点
 */
@Data
public class FormInstRecHandle extends FormInstRecBase {
    //待办节点的表单实例ID
    String form_inst_id;
    String task_id;
    String proc_inst_id;
    FormSheet form_inst_sheet;
    String form_inst_value; //填写的表单数据信息
}
