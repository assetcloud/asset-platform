package com.asset.dao;


import com.asset.entity.AsProcInst;
import com.asset.entity.ProcInstDO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcInstMapper {
    int deleteByPrimaryKey(String procInstId);

    int insert(ProcInstDO record);

    int insertSelective(ProcInstDO record);

    ProcInstDO selectByPrimaryKey(String procInstId);

    int updateByPrimaryKeySelective(ProcInstDO record);

    int updateByPrimaryKey(ProcInstDO record);

    String getProcModelId(String procInstId);

    String getFormInstAllValue(String procInstId);

    void updateFormValueForAll(@Param("procInstId") String procInstId,
                               @Param("newOriginalValue") String newOriginalValue);

    String getDefId(String procInstId);

    String getCommitter(String procInstId);

    List<ProcInstDO> listProcInsts();

    Integer getStatus(String id);

    List<ProcInstDO> getProcInsts(String procModelId);
}
