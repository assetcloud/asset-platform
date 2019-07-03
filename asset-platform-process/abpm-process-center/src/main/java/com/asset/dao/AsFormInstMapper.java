package com.asset.dao;


import com.asset.entity.AsFormInst;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsFormInstMapper {
    int insert(AsFormInst record);
    int insertSelective(AsFormInst record);

    /**
     * 根据表单实例ID获取对应的流程实例ID
     * @param formInstId
     * @return
     */
    String getProcInstID(String formInstId);


    /**
     * 从as_form_inst根据流程实例ID，返回对应的表单实例信息
     * @param procTaskIds 这里的procTaskIds是当前该用户执行到的任务节点TaskID
     * @return
     */
    List<AsFormInst> getFormInsts(String[] procTaskIds);

    String getFormModelId(String formInstId);

    /**
     * 审批
     * @param inst
     */
    void approveFormInst(AsFormInst inst);

    /**
     * 经办
     * @param inst
     */
    void handleFormInst(AsFormInst inst);

    /**
     * 已阅
     * @param inst
     */
    void readFormInst(AsFormInst inst);


    String getTaskId(String taskId);
}
