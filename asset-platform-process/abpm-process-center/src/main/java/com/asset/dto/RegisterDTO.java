package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

/**
 * @author YBY
 */
@Data
public class RegisterDTO {
    FormSheet form_inst_sheet;
//    String editor;  //注册的时候提交的撰写人
    String form_inst_value;
    String user_id;
    String scene_id;
    String candidate_user_id;  //审批阶段谁可以审批这个注册请求
}
