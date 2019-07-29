package com.asset.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("as_user_scene")
public class UserScene {

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("scene_id")
    private String sceneId;
    @TableField("user_id")
    private String userId;

    private Integer status;
    /**
     * 不同场景下的角色（可有多个）
     */
//    @TableField("role_id")
//    private Long roleId;
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
