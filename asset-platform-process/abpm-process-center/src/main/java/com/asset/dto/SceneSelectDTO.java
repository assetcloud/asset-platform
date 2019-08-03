package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

@Data
public class SceneSelectDTO {
    FormSheet form_inst_sheet;
    String editor;  //注册的时候提交的撰写人
    String form_inst_value;
}



