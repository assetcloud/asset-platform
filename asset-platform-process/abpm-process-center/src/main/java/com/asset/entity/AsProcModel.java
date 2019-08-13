package com.asset.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author YBY
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "as_proc_model")
public class AsProcModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
    /**
     * 设计页面统计拖入的节点数目
     */
    private Integer nodeNum;

    private String seqCondition;

    public AsProcModel() {
    }

    private AsProcModel(Builder builder) {
        setId(builder.id);
        setNodeNum(builder.nodeNum);
        setSeqCondition(builder.seqCondition);
    }


    public static final class Builder {
        private String id;
        private Integer nodeNum;
        private String seqCondition;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder nodeNum(Integer val) {
            nodeNum = val;
            return this;
        }

        public Builder seqCondition(String val)
        {
            seqCondition = val;
            return this;
        }

        public AsProcModel build() {
            return new AsProcModel(this);
        }
    }
}
