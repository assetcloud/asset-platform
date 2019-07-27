package com.asset.entity;

import lombok.Data;

@Data
public class GroupCreate extends Group {

    public GroupCreate(String appId, String groupName, Integer status) {
        this.appId = appId;
        this.groupName = groupName;
        this.status = status;
    }
}
