package com.asset.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserRole implements Serializable {

    private Integer id;

    private String uid;

    private Long roleId;

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
