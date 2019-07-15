package com.asset.rec;

import com.asset.form.FormJson;
import lombok.Data;

/**
 * @author YBY
 * @time 190604 1911
 * @version 1.0_190604 1911
 */
@Data
public class FormModelCreateRec {
    String form_name;
    String created_by;
    String icon_cls;
    String oapp_id;
    FormJson model_json;
}
