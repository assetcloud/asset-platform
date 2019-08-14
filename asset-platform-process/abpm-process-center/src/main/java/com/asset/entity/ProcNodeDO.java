package com.asset.entity;

import com.alibaba.fastjson.JSON;
import com.asset.dto.ProcNodeDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ProcNodeDO {
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
}
