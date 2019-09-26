package cn.org.assetcloud.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("as_user_role")
@ApiModel(value = "UserRole对象", description = "UserRole对象类")
public class UserRole implements Serializable {
	/**
	 * id
	 */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;
	/**
	 * 用户id
	 */
    private String uid;
	/**
	 * 角色id
	 */
    @TableField("role_id")
    private Integer roleId;
	/**
	 * 创建时间
	 */
    @TableField("created_time")
    private Date createdTime;
	/**
	 * 数据状态
	 */
    private Integer status;
}
