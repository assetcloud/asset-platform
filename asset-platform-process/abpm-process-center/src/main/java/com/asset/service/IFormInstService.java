package com.asset.service;

import com.asset.entity.CommitFormInstDO;

import java.util.List;

public interface IFormInstService {
    public List<CommitFormInstDO> listComFormInst(String userID,
                                                  Integer taskType,
                                                  String curSelectSceneId,
                                                  String sectionId);
}
