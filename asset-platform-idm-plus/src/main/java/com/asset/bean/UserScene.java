package com.asset.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
    @TableField("role_id")
    private Long roleId;
    @TableField("node_id")
    private Long nodeId;
    @TableField("node_principal")
    private Integer nodePrincipal;

    public UserScene() {
    }

    public UserScene(String sceneId, String userId, Long roleId) {
        this.sceneId = sceneId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserScene(String sceneId, String userId, Long roleId, Long nodeId, Integer nodePrincipal) {
        this.sceneId = sceneId;
        this.userId = userId;
        this.roleId = roleId;
        this.nodeId = nodeId;
        this.nodePrincipal = nodePrincipal;
    }
}
