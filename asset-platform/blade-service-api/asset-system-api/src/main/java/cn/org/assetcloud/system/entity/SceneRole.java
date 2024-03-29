package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@TableName(value = "as_scene_role")
@Data
@ApiModel(value = "SceneRole对象", description = "SceneRole对象类")
public class SceneRole {

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("role_name_zh")
    private String roleNameZh;

    @TableField("role_description")
    private String roleDescription;

    @TableLogic(value = "1", delval = "0")
    private Boolean status;

    @TableField("applicable_unit_level")
    private String applicableUnitLevel;

    @TableField("product_code")
    private String productCode;

    @TableField("enable_time")
    private Date enableTime;

    @TableField("disable_time")
    private Date disableTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("created_time")
    private Date createdTime;

    @TableField("group_id")
    private Long groupId;

    @TableField("role_default")
    private Integer roleDefault;

    @TableField("role_type")
    private Integer roleType;

    @TableField("scene_code")
    private String sceneCode;

    @TableField(exist = false)
    private Integer checked = 0;

    public SceneRole(String sceneId, String sceneDefaultCh, String sceneDefault) {
        this.sceneCode = sceneId;
        this.roleName = sceneDefault;
        this.roleNameZh = sceneDefaultCh;
    }

    public SceneRole() {
    }
}
