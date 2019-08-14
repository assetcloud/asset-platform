package com.asset.dao;

import com.asset.entity.ActAuthority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormAuthorityMapper {
    int insert(ActAuthority record);

    int insertSelective(ActAuthority record);

    Integer getAuthority(String procModelId, String actId, String itemKey);

    int updateAuthority(@Param("procModelId") String procModelId,
                        @Param("actId") String actId,
                        @Param("formItemKey") String formItemKey,
                        @Param("authority") Integer authority);
}
