package com.asset.bean;

import com.asset.vo.ResourceVO;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.checkerframework.checker.units.qual.A;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
    @TableLogic
    private Integer isDeleted;

    @NotNull(message = "应用id不能为空")
    @TableField("app_id")
    private Long appId;

    @TableField("scene_id")
    private String sceneId;

    @TableField("add_time")
    private String addTime;
}
