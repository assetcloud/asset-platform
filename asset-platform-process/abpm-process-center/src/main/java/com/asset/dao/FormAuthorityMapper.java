package com.asset.dao;

import com.asset.entity.FormAuthorityDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormAuthorityMapper {
    int insert(FormAuthorityDO record);

    int insertSelective(FormAuthorityDO record);

    Integer getAuthority(String procModelId, String actId, String itemKey);

    int updateAuthority(@Param("procModelId") String procModelId,
                        @Param("actId") String actId,
                        @Param("formItemKey") String formItemKey,
                        @Param("authority") Integer authority);

    List<FormAuthorityDO> selectList(String procModelId);
}
