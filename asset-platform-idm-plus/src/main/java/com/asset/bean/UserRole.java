package com.asset.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("as_user_role")
public class UserRole implements Serializable {

    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    private String uid;
    @TableField("role_id")
    private Integer roleId;
    @TableField("created_time")
    private Date createdTime;

    private Integer status;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", roleId=" + roleId +
                ", createdTime=" + createdTime +
                ", status=" + status +
                '}';
    }
}
