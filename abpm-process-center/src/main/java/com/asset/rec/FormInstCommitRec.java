package com.asset.rec;

import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
public class FormInstCommitRec {
    String task_id;
    String created_by;
    String form_model_id;

    public FormInstCommitRec() {
    }

    public FormInstCommitRec(String task_id, String created_by) {
        this.task_id = task_id;
        this.created_by = created_by;
    }
}
