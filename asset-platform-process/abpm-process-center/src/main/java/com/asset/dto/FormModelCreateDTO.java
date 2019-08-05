package com.asset.dto;

import com.asset.form.FormSheet;
import lombok.Data;

/**
 * @author YBY
 * @time 190604 1911
 * @version 1.0_190604 1911
 */
@Data
public class FormModelCreateDTO {
    String form_name;
    String created_by;
    String icon_cls;
    String app_id;
    FormSheet form_sheet;
    String scene_id;

    public FormModelCreateDTO() {
    }

    public FormModelCreateDTO(String form_name,
                              String created_by,
                              String icon_cls,
                              String app_id,
                              FormSheet form_sheet) {
        this.form_name = form_name;
        this.created_by = created_by;
        this.icon_cls = icon_cls;
        this.app_id = app_id;
        this.form_sheet = form_sheet;
    }
}
