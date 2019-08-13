package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("as_resource")
public class Resource extends Model<Resource> implements Cloneable{

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @NotNull
    @NotBlank
    private String code;

    @NotNull
    @NotBlank
    private String name;

    @TableField("icon_cls")
    private String iconCls;

    @NotNull
    @NotBlank
    private String path;

    private Integer level;

    private Integer sort;

    @NotNull
    @NotBlank
    private Integer category;

    private String remark;
    /**
     * 表单分组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 场景id
     */
    @TableField("scene_id")
    private String sceneId;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField("add_time")
    private Date addTime;

    @TableField("remove_time")
    private Date removeTime;

    @TableField(exist = false)
    private List<FormGroup> group;

    @TableField(exist = false)
    private List<Resource> children = new ArrayList<>();

    public Resource(String code, String name, String iconCls, String path, Integer level, Integer sort, Integer category) {
        this.code = code;
        this.name = name;
        this.iconCls = iconCls;
        this.path = path;
        this.level = level;
        this.sort = sort;
        this.category = category;
    }

    public Resource() {
    }


    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
