package com.asset.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author YBY
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActHiActinst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("ACT_ID_")
    private String actId;

    @TableField("TASK_ID_")
    private String taskId;

    @TableField("CALL_PROC_INST_ID_")
    private String callProcInstId;

    @TableField("ACT_NAME_")
    private String actName;

    @TableField("ACT_TYPE_")
    private String actType;

    @TableField("ASSIGNEE_")
    private String assignee;

    @TableField("START_TIME_")
    private LocalDateTime startTime;

    @TableField("END_TIME_")
    private LocalDateTime endTime;

    @TableField("DURATION_")
    private Long duration;

    @TableField("DELETE_REASON_")
    private String deleteReason;

    @TableField("TENANT_ID_")
    private String tenantId;

    // 这个标签是用于流程驳回功能的，
    // 这个标签用来表示当前取出的任务节点没有被添加进某一个execution中
    private Boolean sign = false;

}
