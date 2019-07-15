package com.asset.dao;

import com.asset.entity.ActType;
import org.springframework.stereotype.Repository;

/**
 * @author YBY
 * @time 190715
 * @version 1.0_190715
 */
@Repository
public interface ActTypeMapper {
    int insert(ActType record);

    int insertSelective(ActType record);

    Integer getActType(String procModelId, String actId);
}
