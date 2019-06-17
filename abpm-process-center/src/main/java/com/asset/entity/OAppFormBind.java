package com.asset.entity;

import lombok.Data;

@Data
public class OAppFormBind {
    private String oappId;
    private String formModelId;

    public OAppFormBind(String oappId, String formModelId) {
        this.oappId = oappId;
        this.formModelId = formModelId;
    }
}
