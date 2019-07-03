package com.asset.entity;

import lombok.Data;

@Data
public class AsGroupRename extends AsGroup {

    public AsGroupRename(Integer groupId, String appId, String groupName) {
        this.groupId = groupId;
        this.appId = appId;
        this.groupName = groupName;
    }

}
