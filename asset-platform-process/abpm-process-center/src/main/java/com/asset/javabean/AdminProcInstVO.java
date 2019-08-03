package com.asset.javabean;

import lombok.Data;

/**
 * 用来在控制端显示的流程实例信息
 * @author YBY
 */
@Data
public class AdminProcInstVO {
    String procInstId;
    String procInstName;
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
