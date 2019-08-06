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
    //以下两个参数用于与组织架构模块交互,这两个参数由前台直接放到form_inst_value中去
    String user_id;
    String scene_id;
    String candidate_user_id;  //审批阶段谁可以审批这个注册请求
}
