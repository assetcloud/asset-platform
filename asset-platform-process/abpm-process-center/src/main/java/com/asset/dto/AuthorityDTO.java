package com.asset.dto;

import lombok.Data;

import java.util.List;

/**
 * 存储流程模型中每个节点对应的每个表单项的权限
 */
@Data
public class AuthorityDTO {
    String proc_model_id;
    List<AuthorityItemDTO> data;
    Integer print;
}
