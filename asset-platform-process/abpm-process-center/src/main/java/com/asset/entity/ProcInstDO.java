package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProcInstDO {
    private String procInstId;

    private String procModelId;

    private String procDefId;

    private String procDeployId;

    private Date commitTime;

    private String committer;

    private String formInstAllValue;

    private Integer status;

    public ProcInstDO() {
    }

    public ProcInstDO(String procInstId,
                      String procModelId,
                      String procDefId,
                      String procDeployId,
                      String committer,
                      String formInstAllValue
    ) {
        this.procInstId = procInstId;
        this.procModelId = procModelId;
        this.procDefId = procDefId;
        this.procDeployId = procDeployId;
        this.committer = committer;
        this.formInstAllValue = formInstAllValue;
    }
}
