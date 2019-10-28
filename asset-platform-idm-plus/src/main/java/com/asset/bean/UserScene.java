package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("as_user_scene")
public class UserScene {

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("scene_id")
    private String sceneId;
    @TableField("user_id")
    private String userId;

    @TableLogic(value = "1", delval = "0")
    private Integer status;

    @TableField("node_id")
    private String nodeId;

    @TableField("node_principal")
    private Integer nodePrincipal;

    public UserScene() {
    }

    public UserScene(String sceneId, String userId) {
        this.sceneId = sceneId;
        this.userId = userId;
    }

    public UserScene(String sceneId, String userId, String nodeId, Integer nodePrincipal) {
        this.sceneId = sceneId;
        this.userId = userId;
        this.nodeId = nodeId;
        this.nodePrincipal = nodePrincipal;
    }

    public UserScene(String sceneId, String userId, String nodeId, Integer nodePrincipal, Integer status) {
        this.sceneId = sceneId;
        this.userId = userId;
        this.nodeId = nodeId;
        this.nodePrincipal = nodePrincipal;
        this.status = status;
    }
}
