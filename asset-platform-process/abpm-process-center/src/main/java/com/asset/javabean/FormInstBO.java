package com.asset.javabean;

import com.asset.entity.FormInstDO;
import com.asset.entity.ProcNodeDO;
import com.asset.utils.Constants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

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

        if(!StringUtils.isEmpty(nodeDO.getCandidateUser()))
            this.candidateUser = nodeDO.getCandidateUser().split("\\|");
        if(!StringUtils.isEmpty(nodeDO.getCandidateGroup()))
            this.candidateGroup = nodeDO.getCandidateGroup().split("\\|");
        this.ifJointSign = nodeDO.getIfJointSign();

        this.sceneId = sceneId;
    }

    private FormInstBO(Builder builder) {
        setId(builder.id);
        setFormModelId(builder.formModelId);
        setProcInstId(builder.procInstId);
        setExecutionId(builder.executionId);
        setTaskId(builder.taskId);
        setExecuteTime(builder.executeTime);
        setExecutor(builder.executor);
        setFormInstSheetStr(builder.formInstSheetStr);
        setStatus(builder.status);
        setFormInstValue(builder.formInstValue);
        setNodeType(builder.nodeType);
        setApproveResult(builder.approveResult);
        setCurUserId(builder.curUserId);
        setCurTaskType(builder.curTaskType);
        setProcModelId(builder.procModelId);
        setCandidateUser(builder.candidateUser);
        setCandidateGroup(builder.candidateGroup);
        setNodeId(builder.nodeId);
        setIfJointSign(builder.ifJointSign);
        setSceneId(builder.sceneId);
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


    public static final class Builder {
        private String id;
        private String formModelId;
        private String procInstId;
        private String executionId;
        private String taskId;
        private Date executeTime;
        private String executor;
        private String formInstSheetStr;
        private int status;
        private String formInstValue;
        private Integer nodeType;
        private Integer approveResult;
        private String curUserId;
        private Integer curTaskType;
        private String procModelId;
        private String[] candidateUser;
        private String[] candidateGroup;
        private String nodeId;
        private Integer ifJointSign;
        private String sceneId;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder formModelId(String val) {
            formModelId = val;
            return this;
        }

        public Builder procInstId(String val) {
            procInstId = val;
            return this;
        }

        public Builder executionId(String val) {
            executionId = val;
            return this;
        }

        public Builder taskId(String val) {
            taskId = val;
            return this;
        }

        public Builder executeTime(Date val) {
            executeTime = val;
            return this;
        }

        public Builder executor(String val) {
            executor = val;
            return this;
        }

        public Builder formInstSheetStr(String val) {
            formInstSheetStr = val;
            return this;
        }

        public Builder status(int val) {
            status = val;
            return this;
        }

        public Builder formInstValue(String val) {
            formInstValue = val;
            return this;
        }

        public Builder nodeType(Integer val) {
            nodeType = val;
            return this;
        }

        public Builder approveResult(Integer val) {
            approveResult = val;
            return this;
        }

        public Builder curUserId(String val) {
            curUserId = val;
            return this;
        }

        public Builder curTaskType(Integer val) {
            curTaskType = val;
            return this;
        }

        public Builder procModelId(String val) {
            procModelId = val;
            return this;
        }

        public Builder candidateUser(String[] val) {
            candidateUser = val;
            return this;
        }

        public Builder candidateGroup(String[] val) {
            candidateGroup = val;
            return this;
        }

        public Builder nodeId(String val) {
            nodeId = val;
            return this;
        }

        public Builder ifJointSign(Integer val) {
            ifJointSign = val;
            return this;
        }

        public Builder sceneId(String val) {
            sceneId = val;
            return this;
        }

        public FormInstBO build() {
            return new FormInstBO(this);
        }
    }
}
