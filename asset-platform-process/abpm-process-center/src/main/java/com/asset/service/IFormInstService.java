package com.asset.service;

import com.asset.entity.CommitFormInstDO;
import com.asset.javabean.AdminTaskVO;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public interface IFormInstService {
    public List<CommitFormInstDO> listComFormInst(String userID,
                                                  Integer taskType,
                                                  String curSelectSceneId,
                                                  String sectionId);

    ArrayList<AdminTaskVO> listTaskInfo(String procInstId);
}
