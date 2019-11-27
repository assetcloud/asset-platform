package com.asset.service;

import com.asset.entity.ActHiActinst;
import com.asset.entity.ActRuTask;
import com.asset.javabean.AsTask;
import com.asset.mapper.ActHiActinstMapper;
import com.asset.mapper.ActRuTaskMapper;
import com.asset.mapper.FlowableMapper;
import com.asset.entity.CommitFormInstDO;
import com.asset.entity.FlowableTaskDO;
import com.asset.utils.ProcDiagramGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author YBY
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FlowableService {
    static Logger logger = LoggerFactory.getLogger(ProcDiagramGenerator.class);

    @Autowired
    FlowableMapper flowableMapper;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    ActRuTaskMapper actRuTaskMapper;
    @Autowired
    ActHiActinstMapper actHiActinstMapper;


    public List<FlowableTaskDO> listCurTasks(String userID) {
        return flowableMapper.getTaskInfos(userID);
    }

    public String getNodeId(String taskId){
        return flowableMapper.getNodeId(taskId);
    }

    public String getModelName(String procModelId) {
        return flowableMapper.getModelName(procModelId);
    }

    public String getProcInstId(String taskId) {
        return flowableMapper.getProcInstId(taskId);
    }

    public String getModelEditorJson(String procModelId) {
        return flowableMapper.selectModelEditorJson(procModelId);
    }

    public int updateModelEditorJson(String procModelId,String newModelEditorJson) {
        return flowableMapper.updateModelEditorJson(procModelId,newModelEditorJson);
    }



    //nfq:2019/10/11
    public List<CommitFormInstDO> listComFormInst1(String userID, String curSelectSceneId){
        return flowableMapper.listComFormInst1(userID,curSelectSceneId);
    }

    public ActHiActinst selectTaskByExeId(String executionId) {
        ActHiActinst actHiActinst = actHiActinstMapper.selectUnCompleteTaskByExeId(executionId);
        return actHiActinst;
    }

    public List<ActHiActinst> selectTaskByInstId(String instId) {
        List<ActHiActinst> actHiActinsts = actHiActinstMapper.selectUnCompleteTaskByInstId(instId);
        return actHiActinsts;
    }
}


