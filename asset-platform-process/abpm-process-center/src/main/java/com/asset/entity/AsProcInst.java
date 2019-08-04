package com.asset.entity;

import java.time.LocalDateTime;
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
public class AsProcInst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.UUID)
    private String id;

    private String procInstId;

    private String procModelId;

    private String procDefId;

    private String procDeployId;

    private Date commitTime;

    private String committer;

    private String formInstAllValue;

    /**
     * 流程实例状态 0：激活 1：被挂起 2：被删除 3：已完成
     */
    private Integer status;


}
