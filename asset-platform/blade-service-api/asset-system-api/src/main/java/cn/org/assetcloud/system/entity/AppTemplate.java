package cn.org.assetcloud.system.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "AppTemplate对象", description = "应用模板对象")
public class AppTemplate {

    private String id;

    private String applicationId;

    private Boolean status;

    private Date publishTime;

    private Date disableTime;

    private String publishAccount;
}
