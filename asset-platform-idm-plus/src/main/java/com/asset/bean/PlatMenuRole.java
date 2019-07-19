package com.asset.bean;

import lombok.Data;

@Data
public class PlatMenuRole {

    private long id;
    private long menuId;
    private long roleId;
    private String sceneCode;

    public PlatMenuRole() {
    }

    public PlatMenuRole(long menuId, long roleId, String sceneCode) {
        this.menuId = menuId;
        this.roleId = roleId;
        this.sceneCode = sceneCode;
    }
}
