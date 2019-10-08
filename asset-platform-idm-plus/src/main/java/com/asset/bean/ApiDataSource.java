package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "ApiDataSource实体", description = "ApiDataSource实体类")
@TableName("as_api_data_source")
public class ApiDataSource {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.UUID)
    @TableField("id")
    private String id;

    /**
     * api名称
     */
    @ApiModelProperty(value = "api名称")
    private String apiName;

    /**
     * 场景id
     */
    @ApiModelProperty(value = "场景id")
    private String sceneId;

    /**
     * api地址
     */
    @ApiModelProperty(value = "api地址")
    private String apiUrl;

    /**
     * 参数集合
     */
    @ApiModelProperty(value = "参数集合")
    private String params;

    /**
     * api访问方式
     */
    @ApiModelProperty(value = "api访问方式")
    private Integer apiMethod;

    /**
     * api请求头
     */
    @ApiModelProperty(value = "api请求头")
    private String apiHeader;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    private Date addTime;

    /**
     * 是否被删除
     */
    @TableLogic
    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;
    /**
     * api类型
     */
    @ApiModelProperty(value = "api类型")
    private Integer apiType;
    /**
     * api-cookie
     */
    @ApiModelProperty(value = "api-cookie")
    private String apiCookie;
    /**
     * 请求的与实体对应的MIME信息
     */
    @ApiModelProperty(value = "请求的与实体对应的MIME信息")
    private Integer contentType;

}
