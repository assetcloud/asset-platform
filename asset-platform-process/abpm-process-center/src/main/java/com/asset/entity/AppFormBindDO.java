package com.asset.entity;

import lombok.Data;

@Data
public class AppFormBindDO {
    private String appId;
    private String formModelId;

    public AppFormBindDO(String appId, String formModelId) {
        this.appId = appId;
        this.formModelId = formModelId;
    }
}
