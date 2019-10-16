package com.asset.apiBean;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * RemoteUnitInfo对象
 * @author hjhu
 */
@Data
@ApiModel(value = "RemoteUnitInfo实体", description = "远程组织信息实体")
public class RemoteUnitInfo {

    private String unitCode;

    private String parentUnitCode;

    private String unitName;

    private int status;

    private List<RemoteUserInfo> remoteUserInfoList;
}
