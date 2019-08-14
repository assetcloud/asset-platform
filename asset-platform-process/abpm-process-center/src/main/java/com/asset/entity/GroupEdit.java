package com.asset.entity;

import com.asset.utils.Constants;
import lombok.Data;

@Data
public class GroupEdit extends Group {

    public GroupEdit(Integer groupId, String appId, String groupName) {
        this.groupId = groupId;
        this.appId = appId;
        this.groupName = groupName;
        this.status = Constants.GROUP_ENABLED;
    }

    public GroupEdit() {
    }
}
