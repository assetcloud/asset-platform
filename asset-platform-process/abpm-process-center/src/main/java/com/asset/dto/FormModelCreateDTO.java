package com.asset.dto;

import com.asset.javabean.form.FormSheet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author YBY
 * @time 190604 1911
 * @version 1.0_190604 1911
 */
@Data
@ApiModel
public class FormModelCreateDTO {
    @NotNull(message = "表单名称不能为空")
    @ApiModelProperty(value = "表单名称",required = true)
    String form_name;
    @NotNull(message = "创建人Id不能为空")
    @ApiModelProperty(value = "创建人",required = true)
    String created_by;
    @NotNull(message = "表单模型图标不能为空")
    @ApiModelProperty(value = "表单模型图标",required = true)
    String icon_cls;
    @NotNull(message = "应用Id不能为空")
    @ApiModelProperty(value = "所属应用Id（必须真实存在）",required = true)
    String app_id;
    @NotNull(message = "表单样式不能为空")
    @ApiModelProperty(value = "表单样式",required = true)
    FormSheet form_sheet;
    @NotNull(message = "工作场景Id不能为空")
    @ApiModelProperty(value = "当前用户登录时选择的工作场景Id",required = true)
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
