package com.asset.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2019-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("as_templet_proc_node")
public class AsTempletProcNodeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String id;

    /**
     * 注意这里绑定的是流程模型资源的模板Id，资源导入之后还需要获取这个流程模型的Id
     */
    private String templetProcModelId;

    private String nodeId;

    /**
     * 1经办，2审批，3抄送
     */
    private Integer nodeType;

    /**
     * 处理人
     */
    private String candidateUser;

    /**
     * 处理组，这里应该设置成json数据，可以包含复杂数据，比如排除掉某个组里的某些人或者某个组里的某些小组（这个暂时不做，先留个接口在这里）
     */
    private String candidateGroup;

    /**
     * 处理该节点事项的限时，目前暂时只做审批节点
超过限时后，转交其他人处理或者直接流到下一节点


     */
    private LocalDateTime limitTime;

    /**
     * 节点如果超时之后的处理方式：
0：交由系统管理员处理
1：自动到下一节点

     */
    private Integer overtimeStrategy;

    /**
     * 这是设置节点多人审批的策略，用json格式存储实现复杂规则
     */
    private String signStrategy;

    /**
     * 这是设置节点多人处理经办节点的策略，用json格式存储实现复杂规则
     */
    private String todoStrategy;

    /**
     * 判断是否是多人会签节点
0：不是会签
1：是会签，串行
2：是会签，并行
     */
    private Integer ifJointSign;

    private AsTempletProcNodeDO(Builder builder) {
        setId(builder.id);
        setTempletProcModelId(builder.templetProcModelId);
        setNodeId(builder.nodeId);
        setNodeType(builder.nodeType);
        setCandidateUser(builder.candidateUser);
        setCandidateGroup(builder.candidateGroup);
        setLimitTime(builder.limitTime);
        setOvertimeStrategy(builder.overtimeStrategy);
        setSignStrategy(builder.signStrategy);
        setTodoStrategy(builder.todoStrategy);
        setIfJointSign(builder.ifJointSign);
    }


    public static final class Builder {
        private String id;
        private String templetProcModelId;
        private String nodeId;
        private Integer nodeType;
        private String candidateUser;
        private String candidateGroup;
        private LocalDateTime limitTime;
        private Integer overtimeStrategy;
        private String signStrategy;
        private String todoStrategy;
        private Integer ifJointSign;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder templetProcModelId(String val) {
            templetProcModelId = val;
            return this;
        }

        public Builder nodeId(String val) {
            nodeId = val;
            return this;
        }

        public Builder nodeType(Integer val) {
            nodeType = val;
            return this;
        }

        public Builder candidateUser(String val) {
            candidateUser = val;
            return this;
        }

        public Builder candidateGroup(String val) {
            candidateGroup = val;
            return this;
        }

        public Builder limitTime(LocalDateTime val) {
            limitTime = val;
            return this;
        }

        public Builder overtimeStrategy(Integer val) {
            overtimeStrategy = val;
            return this;
        }

        public Builder signStrategy(String val) {
            signStrategy = val;
            return this;
        }

        public Builder todoStrategy(String val) {
            todoStrategy = val;
            return this;
        }

        public Builder ifJointSign(Integer val) {
            ifJointSign = val;
            return this;
        }

        public AsTempletProcNodeDO build() {
            return new AsTempletProcNodeDO(this);
        }
    }
}
