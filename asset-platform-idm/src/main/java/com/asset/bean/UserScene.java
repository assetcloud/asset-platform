package com.asset.bean;

import lombok.Data;

@Data
public class UserScene {

    private Long id;

    private String sceneId;

    private String userId;

    private Integer status;
    /**
     * 不同场景下的角色（可有多个）
     */
    private Long roleId;

    public UserScene() {
    }

    public UserScene(String sceneId, String userId, Long roleId) {
        this.sceneId = sceneId;
        this.userId = userId;
        this.roleId = roleId;
    }
}
