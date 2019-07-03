package com.asset.service;

import com.asset.dao.AsProcModelMapper;
import com.asset.entity.AsProcModel;
import com.asset.rec.ProcModelRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsProcModelService {

    @Autowired
    AsProcModelMapper procModelMapper;
    /**
     * 流程处理中间层，负责为Flowable流程引擎增加功能性节点，这里创建、修改都用同一个接口
     * @param rec
     */
    public int createFormModel(ProcModelRec rec) {
        AsProcModel model = new AsProcModel(rec);
        procModelMapper.editProcModel(model);
        return 0;
    }
}
