package com.asset.entity;

import lombok.Data;

/**
 * @author YBY
 * @time 190715
 * @version 1.0_190715
 */
@Data
public class ActType {
    private String procModelId;

    private String actId;

    private Integer actType;

    public ActType() {
    }

    public ActType(String procModelId, String actId, Integer actType) {
        this.procModelId = procModelId;
        this.actId = actId;
        this.actType = actType;
    }
}
