package com.asset.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2019-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActDeModel implements Serializable {

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


}