package org.springblade.system.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  菜单实体类
 * </p>
 *
 * @author hjhu
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父级菜单
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单别名
     */
    private String alias;
    /**
     * 请求地址
     */
    private String path;
    /**
     * 菜单资源
     */
    private String source;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单类型
     */
    private Integer category;
    /**
     * 操作按钮类型
     */
    private Integer action;
    /**
     * 是否打开新页面
     */
    @TableField("is_open")
    private Integer isOpen;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 添加时间
     */
    @TableField("add_time")
    private Date addTime;

    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
