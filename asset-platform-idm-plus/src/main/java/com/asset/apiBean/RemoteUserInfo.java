package com.asset.apiBean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * RemoteUserInfo对象
 * @author hjhu
 */
@Data
@ApiModel(value = "RemoteUserInfo实体", description = "远程用户信息实体")
public class RemoteUserInfo {

    private String userName;

    private String accountName;

    private String mobilePhoneNumber;

    private String unitCode;

    private String userCode;

    private int status;
}
