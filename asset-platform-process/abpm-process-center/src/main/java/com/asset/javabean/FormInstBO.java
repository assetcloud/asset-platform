package com.asset.javabean;

import com.asset.entity.FormInstDO;
import com.asset.entity.ProcNodeDO;
import com.asset.service.FlowableService;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.service.ProcModelService;
import com.asset.utils.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户从数据库中发现的当前任务节点的信息
 * 包含当前待执行用户ID，当前用户待执行用户的taskType
 * 用于过滤器筛选
 */
@Data
public class FormInstBO extends FormInstDO {
    String curUserId;
    Integer curTaskType;
    String procModelId;
    String[] candidateUser;
    String[] candidateGroup;
    String nodeId;
    Integer ifJointSign;
    String sceneId;


    public FormInstBO() {
    }

//    @Autowired
//    ProcInstService procInstService;
//    @Autowired
//    ProcModelService procModelService;
//    @Autowired
//    FlowableService flowableService;
//    @Autowired
//    FormModelService formModelService;

    public FormInstBO (FormInstDO doo,
                       String curUserId,
                       Integer curTaskType,
                       ProcNodeDO nodeDO,
                       String sceneId){
        this.formInstValue = doo.getFormInstValue();
        this.formModelId = doo.getFormModelId();
        this.procInstId = doo.getProcInstId();
        this.executionId = doo.getExecutionId();
        this.taskId = doo.getTaskId();
        this.executor = doo.getExecutor();
        this.formInstSheetStr = doo.getFormInstSheetStr();
        this.executeTime = doo.getExecuteTime();
        this.id = doo.getId();
        this.nodeType = doo.getNodeType();
        this.status = doo.getStatus();

//        this.procModelId = procInstService.getProcModelId(procInstId);
//        this.nodeId = flowableService.getNodeId(taskId);
//        ProcNodeDO nodeDO = procModelService.getNodeDO(procModelId, nodeId);
        this.curTaskType = curTaskType;
        this.curUserId = curUserId;

        this.candidateUser = nodeDO.getCandidateUser().split("\\|");
        this.candidateGroup = nodeDO.getCandidateGroup().split("\\|");
        this.ifJointSign = nodeDO.getIfJointSign();

        this.sceneId = sceneId;
    }

    public FormInstVO transToVO(String committer) {
        FormInstVO voo = new FormInstVO();
        voo.setFormInstValue(getFormInstValue());
        voo.setFormModelId(getFormModelId());
        voo.setProcInstId(getProcInstId());
        voo.setExecutionId(getExecutionId());
        voo.setTaskId(getTaskId());
        voo.setExecutor(getExecutor());
        voo.setFormInstSheetStr(getFormInstSheetStr());
        voo.setExecuteTime(getExecuteTime());
        voo.setId(getId());
        voo.setNodeType(getNodeType());
        voo.setStatus(getStatus());

        voo.title = committer +"发起的表单";
        if(getNodeType() == Constants.AS_NODE_APPLY)
            voo.content = "经办节点";
        else if(getNodeType() == Constants.AS_NODE_APPROVE)
            voo.content = "审批节点";
        else if(getNodeType() == Constants.AS_NODE_CC)
            voo.content = "抄送节点";
        else
            voo.content = "其他节点";

        return voo;
    }
}
