package com.asset.rec;

import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
public class FormInstCommitRec extends BaseFormInstRec{

    public FormInstCommitRec() {
    }

    public FormInstCommitRec( String editor) {
        super.editor = editor;
    }
}
