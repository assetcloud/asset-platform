package com.asset.dao;

import com.asset.entity.ActAuthority;
import org.springframework.stereotype.Repository;

@Repository
public interface FormAuthorityMapper {
    int insert(ActAuthority record);

    int insertSelective(ActAuthority record);

    Integer getAuthority(String procModelId, String actId, String itemKey);

}
