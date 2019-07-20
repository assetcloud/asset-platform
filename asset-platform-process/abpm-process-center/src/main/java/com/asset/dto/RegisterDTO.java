package com.asset.dto;

import com.asset.form.FormJsonEntity;
import lombok.Data;

@Data
public class RegisterDTO {
    FormJsonEntity model_json;
    String editor;  //注册的时候提交的撰写人
    String resiter_value;
}
