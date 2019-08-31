package com.asset.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;


@Data
@ApiModel(value = "SceneRoleVO对象", description = "SceneRoleVO对象")
public class SceneRoleVO {

    /**
     * id
     */
    private Long id;

    /**
     * 角色代号
     */
    private String roleName;

    /**
     * 角色中文名称
     */
    private String roleNameZh;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 所属场景
     */
    private String sceneName;

    /**
     * 角色名称
     */
    private String roleType;

    /**
     * 角色描述
     */
    private String roleDescription;
}
