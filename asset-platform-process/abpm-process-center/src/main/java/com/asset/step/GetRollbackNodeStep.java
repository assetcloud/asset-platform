package com.asset.step;

import com.asset.service.ProcInstService;
import com.asset.service.ProcNodeService;
import com.asset.utils.Constants;
import com.asset.utils.ProcUtils;
import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRollbackNodeStep {

    @Autowired
    ProcInstService procInstService;
    @Autowired
    ProcNodeService procNodeService;

    /**
     * 获取流程实例中的上一个经办节点,注意这里不能获取并行网关中的经办节点！因为流程回滚不能回滚到并行网关中的经办节点处
     * 1、用comingflow那个操作获取上一个节点
     * 2、查看它的node——type是不是经办节点类型
     * 3、如果不是，回到1；如果是，返回该节点
     *
     * @param procInstId 当前待执行的Task所属的流程实例Id
     * @return
     */
    public String getNormalProcRollbackId(String procInstId) {
        List<HistoricActivityInstance> historicActs = ProcUtils.getHistoricActsDesc(procInstId);
        String procModelId = procInstService.getProcModelId(procInstId);
        for (HistoricActivityInstance instance : historicActs) {
            String actId = instance.getActivityId();
            if (procNodeService.getNodeType(procModelId, actId) == Constants.AS_NODE_APPLY) ;
            return actId;
        }
        return "";
    }

}
