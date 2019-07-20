package com.asset.service;

import com.asset.dao.ActTypeMapper;
import com.asset.entity.ActType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcModelService {

    @Autowired
    ActTypeMapper actTypeMapper;

    public int saveActType(ActType actType) {
        return actTypeMapper.insert(actType);
    }
}
