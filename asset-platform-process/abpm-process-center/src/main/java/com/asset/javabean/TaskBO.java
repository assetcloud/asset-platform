package com.asset.javabean;

import com.asset.entity.FlowableTaskDO;
import lombok.Data;

/**
 * 表示流程模型中一个节点的信息
 * @author YBY
 */
@Data
public class TaskBO {
    String taskId;
    String actId;
    String procInstId;
    Integer nodeType;

    public TaskBO() {
    }

    public TaskBO(FlowableTaskDO cur, Integer nodeType) {
        this.actId = cur.getActId();
        this.taskId = cur.getTaskId();
        this.procInstId = cur.getProcInstId();
        this.nodeType = nodeType;
    }
}
