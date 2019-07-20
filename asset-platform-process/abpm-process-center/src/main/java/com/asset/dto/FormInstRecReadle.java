package com.asset.dto;

import lombok.Data;

/**
 * 待阅节点前台传来的信息
 */
@Data
public class FormInstRecReadle extends FormInstRecBase{
    String form_inst_id;
    String proc_inst_id;
    String task_id;
}
