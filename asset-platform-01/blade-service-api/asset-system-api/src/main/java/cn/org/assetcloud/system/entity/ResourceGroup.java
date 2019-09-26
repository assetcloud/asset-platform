package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springblade.core.mp.base.BaseEntity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("as_resource_group")
@ApiModel(value = "ResourceGroup对象", description = "ResourceGroup对象")
public class ResourceGroup extends Model<ResourceGroup> implements Serializable {

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
