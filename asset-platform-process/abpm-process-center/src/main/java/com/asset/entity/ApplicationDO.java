package com.asset.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationDO {

    private String id;

    private String applicationName;

    private String iconCls;

    private Integer status;

    private Integer isPublished;

    private Date createdTime;

    private Date disableTime;

    private Date removeTime;

    public ApplicationDO() {
    }
}
