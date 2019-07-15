package com.asset.entity;

import lombok.Data;

@Data
public class ActAuthority {
    private String procModelId;

    private String actId;

    private String formItemKey;

    private Integer authority;

    public ActAuthority() {
    }

    public ActAuthority(String procModelId, String actId, String formItemKey, Integer authority) {
        this.procModelId = procModelId;
        this.actId = actId;
        this.formItemKey = formItemKey;
        this.authority = authority;
    }
}
