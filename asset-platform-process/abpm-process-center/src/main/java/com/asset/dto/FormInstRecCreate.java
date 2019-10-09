package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建表单实例时，前台传来的RequestBody
 */
@Data
@ApiModel
public class FormInstRecCreate extends FormInstRecBase {
    @ApiModelProperty(value = "流程中第一个节点要填写的表单内容的样式",required = true)
    FormSheet form_inst_sheet;
    @ApiModelProperty(value = "流程中第一个节点填写的表单内容",required = true)
    String form_inst_value; //填写的表单数据信息

    public FormInstRecCreate() {
    }

    private FormInstRecCreate(Builder builder) {
        setEditor(builder.editor);
        setForm_model_id(builder.form_model_id);
        setForm_inst_sheet(builder.form_inst_sheet);
        setForm_inst_value(builder.form_inst_value);
    }

    public static final class Builder {
        private String editor;
        private String form_model_id;
        private FormSheet form_inst_sheet;
        private String form_inst_value;

        public Builder() {
        }

        public Builder editor(String val) {
            editor = val;
            return this;
        }

        public Builder form_model_id(String val) {
            form_model_id = val;
            return this;
        }

        public Builder form_inst_sheet(FormSheet val) {
            form_inst_sheet = val;
            return this;
        }

        public Builder form_inst_value(String val) {
            form_inst_value = val;
            return this;
        }

        public FormInstRecCreate build() {
            return new FormInstRecCreate(this);
        }
    }
}
