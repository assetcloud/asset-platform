package com.asset.vo;

import com.asset.bean.ApiDataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ApiDataSourceVO extends ApiDataSource {

    private static final long serialVersionUID = 1L;

    /**
     * api访问方式
     */
    private String apiMethodName;
    /**
     * api类型
     */
    private String apiTypeName;
    /**
     * 请求的与实体对应的MIME信息
     */
    private String contentTypeName;
}
