package com.asset.service.impl;

import com.asset.dao.UpdateProcMapper;
import com.asset.service.ProcChangeService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 用于流程变更的Service，包括migration迁移、回滚rollback
 * @author yby
 * @time 190523前某一天
 */
@Service
public class ProcChangeServiceImpl implements ProcChangeService {

    @Autowired
    UpdateProcMapper mapper;

    /**
     * 对一条流程实例进行迁移，就是把它的def_id变成一个新值
     * @param oldProcInstance
     * @param oldProcExe
     */
    @Override
    public void migrateProc(ProcessInstance oldProcInstance, Execution oldProcExe) {
        String oldProcInstanceID = oldProcInstance.getProcessInstanceId();
        String oldDefID = oldProcInstance.getProcessDefinitionId();


        String defName = oldProcInstance.getProcessDefinitionKey();

//        String defName = mapper.getDefName(oldProcInstanceID);
        //这边应该是由这个model名字来获取defName,下面有错误，需要修改
        ArrayList<String> defNameList = mapper.getDefIDList(defName);
        String newDefID = defNameList.get(0);

        //修改act_ru_task表中的流程定义ID
        mapper.updateProc1(newDefID,oldProcInstanceID);
        //修改act_ru_execution表中的流程定义ID
        mapper.updateProc2(newDefID,oldProcInstanceID);
    }
}
