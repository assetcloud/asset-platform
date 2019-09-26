package cn.org.assetcloud.system.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "Application对象", description = "应用对象")
public class Application {

    private String id;

    private String applicationName;

    private String iconCls;

    private Integer status;

    private Integer isPublished;

    private Date createdTime;

    private Date disableTime;

    private Date removeTime;
}
