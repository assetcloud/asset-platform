package com.asset.dao;

import com.asset.entity.AsFormInst;
import com.asset.entity.TaskInst;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowableMapper {

    /**
     * 取出AsFormInst对象中的taskId值去act_hi_actinst表中找到对应的ACT_ID_字段的值，
     * 用于去别的表找这个ACT是什么类型的
     * @param asFormInsts
     * @return 对应的ACT_ID_字段的值
     */
    public List<TaskInst> getActIDs(List<AsFormInst> asFormInsts);

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
    List<TaskInst> getTaskInfos(String userID);
}
