package com.asset.entity;

import java.sql.Blob;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 模板中所属的流程模型信息（除了flowable设计的内容之外还包含为了设计界面额外增加的字段——node_num和seq_condition）
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AsTempletDeModelDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String name;

    private String modelKey;

    private String description;

    private String modelComment;

    private String modelEditorJson;

    private Blob thumbnail;

    private Integer modelType;

    private String tenantId;

    private Integer nodeNum;

    private String seqCondition;

    public AsTempletDeModelDO() {
    }
}
