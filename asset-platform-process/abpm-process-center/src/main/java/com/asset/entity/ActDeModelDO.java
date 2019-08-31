package com.asset.entity;

import java.sql.Blob;
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
 * @since 2019-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ACT_DE_MODEL")
public class ActDeModelDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String name;

    private String modelKey;

    private String description;

    private String modelComment;

    private Date created;

    private String createdBy;

    private Date lastUpdated;

    private String lastUpdatedBy;

    private Integer version;

    private String modelEditorJson;

    private Blob thumbnail;

    private Integer modelType;

    private String tenantId;

    public ActDeModelDO() {
    }

    private ActDeModelDO(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setModelKey(builder.modelKey);
        setDescription(builder.description);
        setModelComment(builder.modelComment);
        setCreated(builder.created);
        setCreatedBy(builder.createdBy);
        setLastUpdated(builder.lastUpdated);
        setLastUpdatedBy(builder.lastUpdatedBy);
        setVersion(builder.version);
        setModelEditorJson(builder.modelEditorJson);
        setThumbnail(builder.thumbnail);
        setModelType(builder.modelType);
        setTenantId(builder.tenantId);
    }


    public static final class Builder {
        private String id;
        private String name;
        private String modelKey;
        private String description;
        private String modelComment;
        private Date created;
        private String createdBy;
        private Date lastUpdated;
        private String lastUpdatedBy;
        private Integer version;
        private String modelEditorJson;
        private Blob thumbnail;
        private Integer modelType;
        private String tenantId;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder modelKey(String val) {
            modelKey = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder modelComment(String val) {
            modelComment = val;
            return this;
        }

        public Builder created(Date val) {
            created = val;
            return this;
        }

        public Builder createdBy(String val) {
            createdBy = val;
            return this;
        }

        public Builder lastUpdated(Date val) {
            lastUpdated = val;
            return this;
        }

        public Builder lastUpdatedBy(String val) {
            lastUpdatedBy = val;
            return this;
        }

        public Builder version(Integer val) {
            version = val;
            return this;
        }

        public Builder modelEditorJson(String val) {
            modelEditorJson = val;
            return this;
        }

        public Builder thumbnail(Blob val) {
            thumbnail = val;
            return this;
        }

        public Builder modelType(Integer val) {
            modelType = val;
            return this;
        }

        public Builder tenantId(String val) {
            tenantId = val;
            return this;
        }

        public ActDeModelDO build() {
            return new ActDeModelDO(this);
        }
    }
}
