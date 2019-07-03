package com.asset.entity;

import lombok.Data;

@Data
public class AsGroupCreate extends AsGroup {

    public AsGroupCreate(String appId,String groupName, Integer status) {
        this.appId = appId;
        this.groupName = groupName;
        this.status = status;
    }
}
