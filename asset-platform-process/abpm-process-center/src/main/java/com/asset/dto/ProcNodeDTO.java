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
}
