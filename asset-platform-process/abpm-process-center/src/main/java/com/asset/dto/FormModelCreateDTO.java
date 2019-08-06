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

    private FormModelCreateDTO(Builder builder) {
        setForm_name(builder.form_name);
        setCreated_by(builder.created_by);
        setIcon_cls(builder.icon_cls);
        setApp_id(builder.app_id);
        setForm_sheet(builder.form_sheet);
        setScene_id(builder.scene_id);
    }


    public static final class Builder {
        private String form_name;
        private String created_by;
        private String icon_cls;
        private String app_id;
        private FormSheet form_sheet;
        private String scene_id;

        public Builder() {
        }

        public Builder form_name(String val) {
            form_name = val;
            return this;
        }

        public Builder created_by(String val) {
            created_by = val;
            return this;
        }

        public Builder icon_cls(String val) {
            icon_cls = val;
            return this;
        }

        public Builder app_id(String val) {
            app_id = val;
            return this;
        }

        public Builder form_sheet(FormSheet val) {
            form_sheet = val;
            return this;
        }

        public Builder scene_id(String val) {
            scene_id = val;
            return this;
        }

        public FormModelCreateDTO build() {
            return new FormModelCreateDTO(this);
        }
    }
}
