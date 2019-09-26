package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("as_user_scene")
@ApiModel(value = "UserScene对象", description = "UserScene对象类")
public class UserScene {
	/**
	 * id
	 */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 场景id
	 */
	@TableField("scene_id")
    private String sceneId;
	/**
	 * 用户id
	 */
	@TableField("user_id")
    private String userId;
	/**
	 * 数据状态
	 */
    @TableLogic(value = "1", delval = "0")
    private Integer status;
	/**
	 * 部门id
	 */
    @TableField("node_id")
    private String nodeId;
	/**
	 * 部门负责人
	 */
    @TableField("node_principal")
    private Integer nodePrincipal;

	/**
	 * constructors
	 */
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
