package com.asset.dao;

import com.asset.entity.AsTempletProcNodeDO;
import com.asset.entity.ProcNodeDO;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcNodeMapper extends BaseMapper<ProcNodeDO> {

    int insert(ProcNodeDO record);

    int insertSelective(ProcNodeDO record);

    Integer getNodeType(String procModelId, String nodeId);

    String getCandidateUsers(String procModelId, String nodeId);

    String getCandidateGroups(String procModelId, String nodeId);

    Integer[] getIfJointSign(String procModelId);

    List<ProcNodeDO> listNodes(String procModelId);

    ProcNodeDO getNodeDO(@Param("procModelId") String procModelId,
                                 @Param("nodeId")  String nodeId);

    int updateSelective(ProcNodeDO nodeDO);

    List<ProcNodeDO> selectNodes(String procModelId);


}
