package com.asset.javabean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用来在控制端显示的流程实例信息
 * @author YBY
 */
@Data
@ApiModel
public class AdminProcInstVO {
    String procInstId;
    String procInstName;
    @ApiModelProperty(value = "0——激活状态，1——被挂起，2——被删除，3——已完成")
    Integer status;
    long commitTime;
    String committer;
    String bindFormModelId;

    public AdminProcInstVO() {
    }



}
//    @Autowired
//    ProcInstService procInstService;
//    @Autowired
//    FlowableService flowableService;
//    @Autowired
//    FormProcServiceImpl formProcService;
//
//    public AdminProcInstVO(ProcInstDO procInstDO) {
//        this.procInstId = procInstDO.getProcInstId();
//        String procModelId = procInstService.getProcModelId(procInstId);
//        this.procInstName = flowableService.getModelName(procModelId);
//        if(ProcUtils.isFinished(procInstId))
//            status = Constants.PROC_INST_FINISHED;
//        else{
//            status = procInstDO.getStatus();
//        }
//        commitTime = procInstDO.getCommitTime().getTime();
//        committer = procInstDO.getCommitter();
//
//        bindFormModelId = formProcService.getBindFormModelId(procModelId);
//    }
