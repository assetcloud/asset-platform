package com.asset.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AsTempletFormModelDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String formName;

    private String modelSheet;

    /**
     * 表单显示的vue图标
     */
    private String iconCls;

    public AsTempletFormModelDO() {
    }

    private AsTempletFormModelDO(Builder builder) {
        setId(builder.id);
        setFormName(builder.formName);
        setModelSheet(builder.modelSheet);
        setIconCls(builder.iconCls);
    }


    public static final class Builder {
        private String id;
        private String formName;
        private String modelSheet;
        private String iconCls;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder formName(String val) {
            formName = val;
            return this;
        }

        public Builder modelSheet(String val) {
            modelSheet = val;
            return this;
        }

        public Builder iconCls(String val) {
            iconCls = val;
            return this;
        }

        public AsTempletFormModelDO build() {
            return new AsTempletFormModelDO(this);
        }
    }
}
