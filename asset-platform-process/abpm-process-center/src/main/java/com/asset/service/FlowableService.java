package com.asset.service;

import com.asset.dao.FlowableMapper;
import com.asset.entity.CommitFormInstDO;
import com.asset.entity.FlowableTaskDO;
import com.asset.utils.ProcDiagramGenerator;
import com.asset.utils.ProcUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
