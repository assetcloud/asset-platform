package com.asset.javabean;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用于控制台显示应用、分组、表单模型以及对应的流程模型资源、
 * 采用三层架构进行展示
 */
@Data
public class AdminAppInfoVO {
    private String id;
    private String applicationName;
    private String iconCls;
    private Integer status;
    private Integer isPublished;
    private Date createdTime;
    private Date disableTime;
    private Date removeTime;

    private List<AdminFormModelVO> adminFormModelVOs;

    public AdminAppInfoVO() {
    }
}
