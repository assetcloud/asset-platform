package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@TableName(value = "as_role_group")
public class RoleGroup {

    @TableField("id")
    @TableId(type= IdType.AUTO)
    private Long id;

    @TableField("role_group_name")
    private String roleGroupName;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField("add_time")
    private Date addTime;

    @TableField("scene_code")
    private String sceneCode;

    @TableField(exist = false)
    private List<SceneRole> sceneRoles;

    public RoleGroup(String roleGroupName, Integer isDeleted, Date addTime, String sceneCode) {
        this.roleGroupName = roleGroupName;
        this.isDeleted = isDeleted;
        this.addTime = addTime;
        this.sceneCode = sceneCode;
    }

    public RoleGroup() {
    }
}
