package com.asset.rec;

import com.asset.form.FormJson;
import lombok.Data;

@Data
public class RegisterRec {
    FormJson model_json;
    //注册的时候提交的撰写人
    String editor;
    String resiter_value;
}
