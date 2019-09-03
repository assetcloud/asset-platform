package com.asset.dao;


import com.asset.entity.FormInstDO;
import com.asset.javabean.TaskBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FormInstMapper {
    int insert(FormInstDO record);
    int insertSelective(FormInstDO record);

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
    List<FormInstDO> getFormInstsByTaskIds(String[] procTaskIds);

    /**
     * 从as_form_inst根据流程实例信息，返回对应的表单实例信息
     * @param taskBOs
     * @return
     */
    List<FormInstDO> getFormInstsByTasks(List<TaskBO> taskBOs);


    FormInstDO getFormInst(String procTaskId);

    String getFormModelId(String formInstId);

    /**
     * 审批
     * @param inst
     */
    void approveFormInst(FormInstDO inst);

    /**
     * 添加当前执行的经办节点表单信息
     * @param inst
     */
    void handleFormInst(FormInstDO inst);

    /**
     * 已阅
     * @param inst
     */
    int readFormInst(FormInstDO inst);


    String getTaskId(String taskId);

    /**
     * 在as_form_inst表中插入一条新的表单实例数据，但是executor、executeTime为空，formInstValue、formSheet需要被后续更新
     * 注：nodeType此时就需要被插入正确值，status需要被指定为0
     * @param formInst
     */
    void saveUnCompleteFormInst(FormInstDO formInst);

    String getProcInstId(String taskId);

    List<String> getAlreadyCompleteTask(@Param("curUserId") String curUserId,
                                  @Param("procInstId") String procInstId);

    List<FormInstDO> listFormInsts();

    String getFormModelIdByTaskId(String taskId);
}
