package com.asset.step;

import com.asset.javabean.AsSimpleTask;
import com.asset.mapper.FlowableMapper;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class SelectSimpleTaskStep {
    @Autowired
    FlowableMapper flowableMapper;

    public List<AsSimpleTask> selectSimpleTaskByProcInstId(String procInstId){
        return flowableMapper.selectSimpleTasksByProcInstId(procInstId);
    }

    public List<AsSimpleTask> selectSimpleTasksByTaskType(int taskType) {
        int nodeType1 = -1;
        int nodeType2 = -1;

        if (taskType == Constants.TASK_TO_DO) {
            nodeType1 = Constants.AS_NODE_APPLY;
            nodeType2 = Constants.AS_NODE_APPROVE;
        }
        else if(taskType == Constants.TASK_TOBE_READ){
            nodeType1 = Constants.AS_NODE_CC;
        }

        return flowableMapper.selectSimpleTasksByTaskType(nodeType1,nodeType2);
    }
}
