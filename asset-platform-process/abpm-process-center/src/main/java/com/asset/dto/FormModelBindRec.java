package com.asset.dto;

import lombok.Data;

/**
 * 用于接收表单模型和流程模型绑定
 */
@Data
public class FormModelBindRec {
    String form_model_id;
    String proc_model_id;
}
