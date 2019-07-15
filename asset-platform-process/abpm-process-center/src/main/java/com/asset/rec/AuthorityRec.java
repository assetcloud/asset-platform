package com.asset.rec;

import lombok.Data;

import java.util.List;

/**
 * 存储流程模型中每个节点对应的每个表单项的权限
 */
@Data
public class AuthorityRec {
    String proc_model_id;
    List<AuthorityItem> data;
    Integer print;
}
