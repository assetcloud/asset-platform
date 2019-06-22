package com.asset.dao;


import com.asset.entity.AsFormInst;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsFormInstMapper {
    int insert(AsFormInst record);
    int insertSelective(AsFormInst record);

    /**
     * 从act_hi_actinst中找代办人是当前userID的且ACT_TYPE_为userTask，END_TIME_为空的（这代表当前节点任务
     * 已经流转到这个用户，但是还没完成
     * @param userID
     * @return
     */
    List<String> getProcInstIDs(String userID);

    /**
     * 从as_form_inst根据流程实例ID，返回对应的表单实例信息
     * @param procInstIDs
     * @return
     */
    List<AsFormInst> getFormInsts(List<String> procInstIDs);
}
