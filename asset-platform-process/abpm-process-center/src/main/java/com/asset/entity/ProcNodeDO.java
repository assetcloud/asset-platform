package com.asset.entity;

import com.alibaba.fastjson.JSON;
import com.asset.dto.ProcNodeDTO;
import com.asset.javabean.IDGenerator;
import com.asset.javabean.UuidIdGenerator;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_proc_node")
public class ProcNodeDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    @TableField(value = "proc_model_id")
    private String procModelId;

    @TableField(value = "node_id")
    private String nodeId;

    @TableField(value = "node_type")
    private Integer nodeType;

    @TableField(value = "candidate_user")
    private String candidateUser;

    @TableField(value = "candidate_group")
    private String candidateGroup;

    @TableField(value = "limit_time")
    private Date limitTime;

    @TableField(value = "overtime_strategy")
    private Integer overtimeStrategy;

    @TableField(value = "sign_strategy")
    private String signStrategy;

    @TableField(value = "todo_strategy")
    private String todoStrategy;

    @TableField(value = "if_joint_sign")
    private Integer ifJointSign;

    public ProcNodeDO() {

    }

    public ProcNodeDO(String procModelId, String nodeId, Integer nodeType) {
        this.procModelId = procModelId;
        this.nodeId = nodeId;
        this.nodeType = nodeType;
    }


    public ProcNodeDO(ProcNodeDTO nodeDTO,String procModelId) {
        this.procModelId = procModelId;
        this.nodeId = nodeDTO.getAct_id();
        this.nodeType = nodeDTO.getAct_type();
        this.candidateUser = nodeDTO.getCandidate_user();
        this.candidateGroup = nodeDTO.getCandidate_group();
        this.limitTime = nodeDTO.getLimit_time();
        this.overtimeStrategy = nodeDTO.getOvertime_strategy();
        this.signStrategy = JSON.toJSONString(nodeDTO.getSign_strategy());
        this.todoStrategy = nodeDTO.getTodo_strategy();
        this.ifJointSign = nodeDTO.getIf_joint_sign();
    }

    private ProcNodeDO(Builder builder) {
        setId(builder.id);
        setProcModelId(builder.procModelId);
        setNodeId(builder.nodeId);
        setNodeType(builder.nodeType);
        setCandidateUser(builder.candidateUser);
        setCandidateGroup(builder.candidateGroup);
        setLimitTime(builder.limitTime);
        setOvertimeStrategy(builder.overtimeStrategy);
        setSignStrategy(builder.signStrategy);
        setTodoStrategy(builder.todoStrategy);
        setIfJointSign(builder.ifJointSign);
    }


    public static final class Builder {
        private String id;
        private String procModelId;
        private String nodeId;
        private Integer nodeType;
        private String candidateUser;
        private String candidateGroup;
        private Date limitTime;
        private Integer overtimeStrategy;
        private String signStrategy;
        private String todoStrategy;
        private Integer ifJointSign;

        public Builder() {
        }

        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder nodeId(String val) {
            nodeId = val;
            return this;
        }

        public Builder nodeType(Integer val) {
            nodeType = val;
            return this;
        }

        public Builder candidateUser(String val) {
            candidateUser = val;
            return this;
        }

        public Builder candidateGroup(String val) {
            candidateGroup = val;
            return this;
        }

        public Builder limitTime(Date val) {
            limitTime = val;
            return this;
        }

        public Builder overtimeStrategy(Integer val) {
            overtimeStrategy = val;
            return this;
        }

        public Builder signStrategy(String val) {
            signStrategy = val;
            return this;
        }

        public Builder todoStrategy(String val) {
            todoStrategy = val;
            return this;
        }

        public Builder ifJointSign(Integer val) {
            ifJointSign = val;
            return this;
        }

        public ProcNodeDO build() {
            IDGenerator generator = new UuidIdGenerator();
            id = generator.generateID();
            return new ProcNodeDO(this);
        }
    }
}
