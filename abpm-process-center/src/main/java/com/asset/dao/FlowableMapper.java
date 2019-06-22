package com.asset.dao;

import com.asset.entity.AsFormInst;
import com.asset.entity.ProcInst;
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
    public List<ProcInst> getActIDs(List<AsFormInst> asFormInsts);
}
