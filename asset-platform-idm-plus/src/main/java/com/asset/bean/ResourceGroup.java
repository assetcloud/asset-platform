package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_resource_group")
@ApiModel(value = "资源分组DO对象", description = "资源分组DO对象")
public class ResourceGroup extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField("scene_id")
    private String sceneId;

    @TableField("add_time")
    private String addTime;
}
