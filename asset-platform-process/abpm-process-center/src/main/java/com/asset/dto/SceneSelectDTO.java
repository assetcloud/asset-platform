package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

@Data
public class SceneSelectDTO {
    FormSheet form_inst_sheet;
    String form_inst_value;
    String user_id;
    String scene_id;  //这两个参数还需要吗？
    String candidate_user_id;  //审批阶段谁可以审批这个工作场景申请的请求
}



