package com.asset.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author YBY
 * @since 2019-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_templet")
public class AsTempletDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 应用模板的名称
     */
    private String templetName;

    /**
     * 应用模板的图标
     */
    private String iconCls;

    /**
     * 应用模板下对应的应用Id
     */
    private String linkAppId;

    /**
     * 应用模板下对应的表单模型Id
     */
    private String linkFormModelId;

    /**
     * 应用模板下对应的流程模型Id
     */
    private String linkProcModelId;

    /**
     * 应用模板状态
     */
    private Integer status;

    /**
     * 发布模板的人的Id
     */
    private String committer;

    /**
     * 模板发布时间
     */
    private Date createTime;


    public AsTempletDO() {
    }

    private AsTempletDO(Builder builder) {
        setId(builder.id);
        setTempletName(builder.templetName);
        setIconCls(builder.iconCls);
        setLinkAppId(builder.linkAppId);
        setLinkFormModelId(builder.linkFormModelId);
        setLinkProcModelId(builder.linkProcModelId);
        setStatus(builder.status);
        setCommitter(builder.committer);
        setCreateTime(builder.createTime);
    }


    public static final class Builder {
        private String id;
        private String templetName;
        private String iconCls;
        private String linkAppId;
        private String linkFormModelId;
        private String linkProcModelId;
        private Integer status;
        private String committer;
        private Date createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder templetName(String val) {
            templetName = val;
            return this;
        }

        public Builder iconCls(String val) {
            iconCls = val;
            return this;
        }

        public Builder linkAppId(String val) {
            linkAppId = val;
            return this;
        }

        public Builder linkFormModelId(String val) {
            linkFormModelId = val;
            return this;
        }

        public Builder linkProcModelId(String val) {
            linkProcModelId = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public Builder committer(String val) {
            committer = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }


        public AsTempletDO build() {
            return new AsTempletDO(this);
        }
    }
}
