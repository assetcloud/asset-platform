package com.asset.rec;

import lombok.Data;

import java.util.List;

/**
 * @author YBY
 * @time 190604 1911
 * @version 1.0_190604 1911
 */
@Data
public class FormModelCreateRec {
    String form_model_id;
    String form_name;
    List<Object> model_json = null;
    String created_by;
    String icon_cls;
    String oapp_id;
}
