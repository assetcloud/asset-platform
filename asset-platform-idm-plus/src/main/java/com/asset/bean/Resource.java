package com.asset.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_resource")
public class Resource extends Model<Resource> implements Cloneable{

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("parent_id")
    private Long parentId;
    private String code;
    private String name;
    @TableField("icon_cls")
    private String iconCls;
    private String path;
    private Integer level;
    private Integer sort;
    private Integer category;
    private String remark;
    /**
     * 表单分组id
     */
    @TableField("group_id")
    private String groupId;
    /**
     * 表单分组名称
     */
    @TableField("group_name")
    private String groupName;
    /**
     * 场景id
     */
    @TableField("scene_id")
    private String sceneId;
    @TableField("is_deleted")
    private Integer isDeleted;
    @TableField("add_time")
    private Date addTime;
    @TableField("remove_time")
    private Date removeTime;

    @TableField(exist = false)
    private List<FormGroup> group;
    @TableField(exist = false)
    private List<Resource> children;

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
