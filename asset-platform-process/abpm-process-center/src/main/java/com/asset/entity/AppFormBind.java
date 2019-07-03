package com.asset.entity;

import lombok.Data;

@Data
public class AppFormBind {
    private String appId;
    private String formModelId;

    public AppFormBind(String appId, String formModelId) {
        this.appId = appId;
        this.formModelId = formModelId;
    }
}
