package com.asset.mapper;

import com.asset.entity.AsTaskDO;
import com.asset.entity.CommitFormInstDO;
import com.asset.entity.FlowableTaskDO;
import com.asset.javabean.AsSimpleTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlowableMapper {

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

    List<AsSimpleTask> selectSimpleTasksByProcInstId(String procInstId);

    List<AsSimpleTask> selectSimpleTasksByTaskType(@Param("taskType1") Integer nodeType1,
                                                   @Param("taskType2")  Integer nodeType2);

    AsSimpleTask selectSimpleTasksByTaskId(String taskId);

}
