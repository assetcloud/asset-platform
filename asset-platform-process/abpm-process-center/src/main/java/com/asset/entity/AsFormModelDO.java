package com.asset.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
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
public class AsFormModelDO implements Serializable {

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
    private Integer groupId;
    private String iconCls;
    private String procModelId;
    private String modelSheet;
    private String sceneId;
    private String appId;
    private Integer isBinded;  //表单模型是否绑定流程模型，0否，1是
    private Integer isBindAuthority;   //表单项权限数据是否添加，0否，1是
    private Integer isAddNodeInfo;   //是否正确增加节点信息，0否，1是
    private Integer isAddSeqCondition;   //是否增加seqCondition，0否，1是
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;

    public AsFormModelDO() {
    }
}
