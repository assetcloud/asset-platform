package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_resource_role")
@ApiModel(value = "ResourceRole对象", description = "ResourceRole对象")
public class ResourceRole extends Model<ResourceRole> implements Cloneable{

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("menu_id")
    private Long menuId;

    @TableField("role_id")
    private Long roleId;

}
