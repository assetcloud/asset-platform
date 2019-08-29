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
public class AsTempletFormAuthorityDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String templetProcModelId;

    private String actId;

    private String formItemKey;

    /**
     * 1不可见，2可见+不可编辑，3可见+可编辑，4必填
     */
    private Integer authority;

    public AsTempletFormAuthorityDO() {
    }

    private AsTempletFormAuthorityDO(Builder builder) {
        setId(builder.id);
        setTempletProcModelId(builder.templetProcModelId);
        setActId(builder.actId);
        setFormItemKey(builder.formItemKey);
        setAuthority(builder.authority);
    }


    public static final class Builder {
        private String id;
        private String templetProcModelId;
        private String actId;
        private String formItemKey;
        private Integer authority;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder templetProcModelId(String val) {
            templetProcModelId = val;
            return this;
        }

        public Builder actId(String val) {
            actId = val;
            return this;
        }

        public Builder formItemKey(String val) {
            formItemKey = val;
            return this;
        }

        public Builder authority(Integer val) {
            authority = val;
            return this;
        }

        public AsTempletFormAuthorityDO build() {
            return new AsTempletFormAuthorityDO(this);
        }
    }
}
