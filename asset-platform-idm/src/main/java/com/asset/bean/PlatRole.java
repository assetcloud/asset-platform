package com.asset.bean;

import lombok.Data;

@Data
public class PlatRole {

    private Long id;

    private String sceneCode;

    private String roleName;

    private Integer sort;

    private String roleAlias;

    private Integer isDeleted;

    public PlatRole() {
    }

    public PlatRole(String sceneCode, String roleName, String roleAlias) {
        this.sceneCode = sceneCode;
        this.roleName = roleName;
        this.roleAlias = roleAlias;
        this.sort = 0;
        this.isDeleted = 0;
    }
}
