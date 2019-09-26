package cn.org.assetcloud.system.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_role")
@ApiModel(value = "平台角色对象", description = "平台角色对象类")
public class Role {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名
     */
    @TableField("role_name_zh")
    private String roleNameZh;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 角色别名
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 是否已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;
}
