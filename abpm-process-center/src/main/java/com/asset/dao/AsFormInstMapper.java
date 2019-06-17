package com.asset.dao;


import com.asset.entity.AsFormInst;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsFormInstMapper {
    int insert(AsFormInst record);
    int insertSelective(AsFormInst record);
    List<String> getProcInstIDs(String userID);

    /**
     * 根据流程实例ID，返回对应的表单实例信息
     * @param procInstIDs
     * @return
     */
    List<AsFormInst> getFormInsts(List<String> procInstIDs);
}
