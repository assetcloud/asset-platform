package com.asset.javabean;

import com.asset.dto.ProcNodeDTO;
import com.asset.dto.SignStrategyDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProcModelBO {
    String proc_model_id;
    List<ProcNodeDTO> act_data = new ArrayList<>();
    String candidate_user;
    String candidate_group;
    Date limit_time;
    Integer overtime_strategy;
    SignStrategyDTO sign_strategy;
    String todo_strategy;
    Integer if_joint_sign;

    public ProcModelBO() {
    }
}
