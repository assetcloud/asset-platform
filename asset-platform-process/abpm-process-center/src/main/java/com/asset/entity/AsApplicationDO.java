package com.asset.entity;

import java.time.LocalDateTime;
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
@TableName("as_application")
public class AsApplicationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 模块名称
     */
    private String applicationName;

    /**
     * 模块图标
     */
    private String iconCls;

    /**
     * 状态：1、可用；0、不可用
     */
    private Integer status;

    /**
     * 是否发布：1——发布，0——没发布
     */
    private Integer isPublished;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 失效时间
     */
    private Date disableTime;

    /**
     * 删除时间
     */
    private Date removeTime;

    public AsApplicationDO() {
    }
}
