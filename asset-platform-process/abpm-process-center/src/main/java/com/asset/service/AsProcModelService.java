package com.asset.service;

import com.asset.dao.ActTypeMapper;
import com.asset.entity.ActType;
import com.asset.rec.ProcModelRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsProcModelService {

    @Autowired
    ActTypeMapper actTypeMapper;

    public int saveActType(ActType actType) {
        return actTypeMapper.insert(actType);
    }
}
