package com.asset.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
    private Long roleId;
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
