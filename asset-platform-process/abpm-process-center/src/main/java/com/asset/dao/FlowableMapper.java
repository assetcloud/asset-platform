package com.asset.dao;

import com.asset.entity.CommitFormInstDO;
import com.asset.entity.FlowableTaskDO;
import com.asset.entity.FormInstDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowableMapper {

    /**
     * 取出AsFormInst对象中的taskId值去act_hi_actinst表中找到对应的ACT_ID_字段的值，
     * 用于去别的表找这个ACT是什么类型的
     * @param formInstDOS
     * @return 对应的ACT_ID_字段的值
     */
    public List<FlowableTaskDO> getActIDs(List<FormInstDO> formInstDOS);

    /**
     * 获取当前流程实例要执行的任务节点的ACT_ID
     * @param procInstID
     * @return
     */
    public String getCurActId(String procInstID);


    /**
     * 获取流转到该用户的所有ACT_ID、TASK_ID、PROC_INST_ID信息
     * @param userID
     * @return
     */
    List<FlowableTaskDO> getTaskInfos(String userID);

    String getNodeId(String taskId);

    String getModelName(String modelId);

    String getProcInstId(String taskId);

    String selectModelEditorJson(String procModelId);

    Integer updateModelEditorJson(@Param("procModelId") String procModelId,
                               @Param("newModelEditorJson") String newModelEditorJson);

    List<CommitFormInstDO> listComFormInst1(@Param("userID") String userID,
                                            @Param("curSelectSceneId") String curSelectSceneId);

//    /**
//     * 找到在taskId之前 相同执行链（executionId）上按时间排序所有被执行的流程节点
//     * @param executionId
//     * @return
//     */
//    String[] getAllExecutedNodeIds(String executionId);
}
