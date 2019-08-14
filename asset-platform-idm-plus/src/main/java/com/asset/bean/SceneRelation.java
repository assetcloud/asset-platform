package com.asset.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hjhu
 * @since 2019-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_scene_relation")
public class SceneRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private String uid;

    /**
     * 业务角色id
     */
    private Long rid;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    public SceneRelation(String uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public SceneRelation() {
    }
}
