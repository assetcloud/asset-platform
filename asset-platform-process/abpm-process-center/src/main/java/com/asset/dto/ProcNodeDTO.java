package com.asset.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProcNodeDTO {
    String act_id;
    int act_type;
    String candidate_user;
    String candidate_group;
    Date limit_time;
    Integer overtime_strategy;
    SignStrategyDTO sign_strategy;
    String todo_strategy;
    Integer if_joint_sign;

    public ProcNodeDTO() {
    }

    private ProcNodeDTO(Builder builder) {
        setAct_id(builder.act_id);
        setAct_type(builder.act_type);
        setCandidate_user(builder.candidate_user);
        setCandidate_group(builder.candidate_group);
        setLimit_time(builder.limit_time);
        setOvertime_strategy(builder.overtime_strategy);
        setSign_strategy(builder.sign_strategy);
        setTodo_strategy(builder.todo_strategy);
        setIf_joint_sign(builder.if_joint_sign);
    }


    public static final class Builder {
        private String act_id;
        private int act_type;
        private String candidate_user;
        private String candidate_group;
        private Date limit_time;
        private Integer overtime_strategy;
        private SignStrategyDTO sign_strategy;
        private String todo_strategy;
        private Integer if_joint_sign;

        public Builder() {
        }

        public Builder act_id(String val) {
            act_id = val;
            return this;
        }

        public Builder act_type(int val) {
            act_type = val;
            return this;
        }

        public Builder candidate_user(String val) {
            candidate_user = val;
            return this;
        }

        public Builder candidate_group(String val) {
            candidate_group = val;
            return this;
        }

        public Builder limit_time(Date val) {
            limit_time = val;
            return this;
        }

        public Builder overtime_strategy(Integer val) {
            overtime_strategy = val;
            return this;
        }

        public Builder sign_strategy(SignStrategyDTO val) {
            sign_strategy = val;
            return this;
        }

        public Builder todo_strategy(String val) {
            todo_strategy = val;
            return this;
        }

        public Builder if_joint_sign(Integer val) {
            if_joint_sign = val;
            return this;
        }

        public ProcNodeDTO build() {
            return new ProcNodeDTO(this);
        }
    }
}
