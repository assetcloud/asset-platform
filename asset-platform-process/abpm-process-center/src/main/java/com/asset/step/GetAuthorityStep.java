package com.asset.step;

import com.asset.mapper.FormAuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAuthorityStep {

    @Autowired
    FormAuthorityMapper formAuthorityMapper;

    public Integer getCurAuthority(String procModelId, String actId, String itemKey) {
        return formAuthorityMapper.getAuthority(procModelId,actId,itemKey);
    }
}
