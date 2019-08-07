package com.asset.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.Scope;
import lombok.Data;
import lombok.Generated;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@ApiModel(value = "场景对象")
public class Scene implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    @TableField("id")
    private String id;

    @NotBlank(message = "场景名称不能为空")
    @NotNull(message = "场景名称不能为空")
    @TableField("scene_name")
    private String sceneName;

    private String remark;

    @TableField("is_deleted")
    private int isDeleted;

    @TableField("add_time")
    private Date addTime;

    private int status;

    private String img;

    public Scene(String sceneName, String remark, String img){
        this.sceneName = sceneName;
        this.remark = remark;
        this.img = img;
        this.addTime = new Date();
        this.isDeleted = 0;
        this.status = 1;
    }

    public Scene(){

    }
}
