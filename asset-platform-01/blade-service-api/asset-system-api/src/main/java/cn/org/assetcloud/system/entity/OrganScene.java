package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@TableName("as_organ_scene")
@ApiModel(value = "ApiModel对象", description = "ApiModel对象")
public class OrganScene {
	/**
	 * id
	 */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 部门id
	 */
    private String nodeId;
	/**
	 * 父部门id
	 */
    private String parentId;
	/**
	 * 场景id
	 */
    private String sceneId;
	/**
	 * 状态
	 */
    @TableLogic(value = "1", delval = "0")
    private Integer status;
	/**
	 * 部门名称
	 */
    private String unitName;
	/**
	 * 子部门列表
	 */
    @TableField(exist = false)
    private List<OrganScene> children;
}
