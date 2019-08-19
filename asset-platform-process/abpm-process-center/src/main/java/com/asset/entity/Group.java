package com.asset.entity;

import lombok.Data;

@Data
public class Group {
    protected Integer id;

    protected String appId;

    protected String groupName;

    protected Integer status;

    public Group() {
    }
}
