package com.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2019-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ACT_RU_VARIABLE")
public class ActRuVariableDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_",type = IdType.UUID)
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("TYPE_")
    private String type;

    @TableField("NAME_")
    private String name;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("PROC_INST_ID_")
    private String procInstId;

    @TableField("TASK_ID_")
    private String taskId;

    @TableField("SCOPE_ID_")
    private String scopeId;

    @TableField("SUB_SCOPE_ID_")
    private String subScopeId;

    @TableField("SCOPE_TYPE_")
    private String scopeType;

    @TableField("BYTEARRAY_ID_")
    private String bytearrayId;

    @TableField("DOUBLE_")
    private Double doubleLocal;

    @TableField("LONG_")
    private Long longLocal;

    @TableField("TEXT_")
    private String text;

    @TableField("TEXT2_")
    private String text2;

    public ActRuVariableDO() {
    }

    private ActRuVariableDO(Builder builder) {
        setId(builder.id);
        setRev(builder.rev);
        setType(builder.type);
        setName(builder.name);
        setExecutionId(builder.executionId);
        setProcInstId(builder.procInstId);
        setTaskId(builder.taskId);
        setScopeId(builder.scopeId);
        setSubScopeId(builder.subScopeId);
        setScopeType(builder.scopeType);
        setBytearrayId(builder.bytearrayId);
        setDoubleLocal(builder.doubleLocal);
        setLongLocal(builder.longLocal);
        setText(builder.text);
        setText2(builder.text2);
    }


    public static final class Builder {
        private String id;
        private Integer rev;
        private String type;
        private String name;
        private String executionId;
        private String procInstId;
        private String taskId;
        private String scopeId;
        private String subScopeId;
        private String scopeType;
        private String bytearrayId;
        private Double doubleLocal;
        private Long longLocal;
        private String text;
        private String text2;

        public Builder() {
        }


        public Builder rev(Integer val) {
            rev = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder scopeId(String val) {
            scopeId = val;
            return this;
        }

        public Builder subScopeId(String val) {
            subScopeId = val;
            return this;
        }

        public Builder scopeType(String val) {
            scopeType = val;
            return this;
        }

        public Builder bytearrayId(String val) {
            bytearrayId = val;
            return this;
        }

        public Builder doubleLocal(Double val) {
            doubleLocal = val;
            return this;
        }

        public Builder longLocal(Long val) {
            longLocal = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Builder text2(String val) {
            text2 = val;
            return this;
        }

        public ActRuVariableDO build() {
            return new ActRuVariableDO(this);
        }
    }
}
