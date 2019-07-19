package com.asset.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "as_role_group")
public class RoleGroup {

    @TableField("id")
    @TableId(type=IdType.AUTO)
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

}
