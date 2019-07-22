package com.asset.bean;

import lombok.Data;

@Data
public class MenuRole {

    private long id;
    private long menuId;
    private long roleId;

    public MenuRole() {
    }

    public MenuRole(long menuId, long roleId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
