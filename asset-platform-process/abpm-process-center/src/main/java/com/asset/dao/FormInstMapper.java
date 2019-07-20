package com.asset.dao;


import com.asset.entity.FormInst;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormInstMapper {
    int insert(FormInst record);
    int insertSelective(FormInst record);

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
    List<FormInst> getFormInsts(String[] procTaskIds);

    String getFormModelId(String formInstId);

    /**
     * 审批
     * @param inst
     */
    void approveFormInst(FormInst inst);

    /**
     * 经办
     * @param inst
     */
    void handleFormInst(FormInst inst);

    /**
     * 已阅
     * @param inst
     */
    void readFormInst(FormInst inst);


    String getTaskId(String taskId);
}
