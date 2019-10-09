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
@TableName("as_form_inst")
public class AsFormInstDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;
    private String formModelId;
    private String procInstId;
    private String executionId;
    private String taskId;

    /**
     * 执行人
     */
    private String executor;

    private Date executeTime;

    private String formInstSheet;

    /**
     * 表单项中填的信息
     */
    private String formInstValue;

    /**
     *  0;   //表示被创建，等待被执行
 1;   //被执行了
99;  //表单实例被丢弃（即没被执行了，可能是直接被回滚了）
999;  //表单实例原来被正确执行了的，但是现在被回滚了
     */
    private Integer status;

    /**
     * 用来标示当前任务节点类型：经办1、审批2、抄送3
     */
    private Integer nodeType;

    /**
     * 1表示对当前审批节点同意，0表示不同意
     */
    private Integer approveResult;

    public AsFormInstDO() {
    }
}
