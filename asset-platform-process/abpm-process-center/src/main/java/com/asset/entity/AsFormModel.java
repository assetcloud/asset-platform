package com.asset.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_form_model")
public class AsFormModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.UUID)
    private String id;

    private String formName;

    private Date createdTime;

    private String createdBy;

    private Date lastUpdatedTime;

    private String lastUpdatedBy;

    private Integer version;

    private String modelSheet;

    /**
     * 该表单所属分组（负值代表不属于任一分组）
     */
    private Integer groupId;

    /**
     * 表单显示的vue图标
     */
    private String iconCls;

    /**
     * 表单当前状态（0未绑定流程；1已绑定流程可被发起；2已被删除）
     */
    private Integer status;

    /**
     * 绑定的流程模型ID
     */
    private String procModelId;

    /**
     * 当前表单流程模型所属的工作场景
     */
    private String sceneId;

    private String appId;

    public AsFormModel() {
    }
}
