package com.asset.entity;

import java.io.Serializable;
import java.util.Date;

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
 * @since 2019-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_proc_inst")
public class AsProcInstDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;

    private String procModelId;

    private String procDefId;

    private String procDeployId;

    private Date commitTime;

    private String committer;

    private String formInstAllValue;

    /**
     * 流程实例状态 0：激活 1：被挂起 2：被删除 3：已完成
     */
    private Integer status;

    public AsProcInstDO() {
    }

    private AsProcInstDO(Builder builder) {
        setId(builder.id);
        setProcModelId(builder.procModelId);
        setProcDefId(builder.procDefId);
        setProcDeployId(builder.procDeployId);
        setCommitTime(builder.commitTime);
        setCommitter(builder.committer);
        setFormInstAllValue(builder.formInstAllValue);
        setStatus(builder.status);
    }


    public static final class Builder {
        private String id;
        private String procModelId;
        private String procDefId;
        private String procDeployId;
        private Date commitTime;
        private String committer;
        private String formInstAllValue;
        private Integer status;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }


        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder procDefId(String val) {
            procDefId = val;
            return this;
        }

        public Builder procDeployId(String val) {
            procDeployId = val;
            return this;
        }

        public Builder commitTime(Date val) {
            commitTime = val;
            return this;
        }

        public Builder committer(String val) {
            committer = val;
            return this;
        }

        public Builder formInstAllValue(String val) {
            formInstAllValue = val;
            return this;
        }

        public Builder status(Integer val) {
            status = val;
            return this;
        }

        public AsProcInstDO build() {
            return new AsProcInstDO(this);
        }
    }
}
