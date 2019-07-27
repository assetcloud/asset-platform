package com.asset.service;

import com.asset.dao.FlowableMapper;
import com.asset.entity.FlowableTaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YBY
 */
@Service
public class FlowableService {
    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    ProcInstService procInstService;

    public List<FlowableTaskDO> listCurTasks(String userID) {
        return flowableMapper.getTaskInfos(userID);
    }

    public String getNodeId(String taskId){
        return flowableMapper.getNodeId(taskId);
    }
//
//    /**
//     * 找到在taskId之前 相同执行链（executionId）上按时间排序所有被执行的流程节点
//     * 注意着
//     * @param taskId
//     * @return
//     */
//    public String[] getAllExcutedNodes(String taskId) {
//        String executionId = procInstService.getExecutionId(taskId);
//        return flowableMapper.getAllExecutedNodeIds(executionId);
//    }
}
