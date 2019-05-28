package com.asset.service;

import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;

/**
 * 用于流程变更的Service，包括migration迁移、回滚rollback
 * @author yby
 * @time 190523前某一天
 */
public interface ProcChangeService {

    void migrateProc(ProcessInstance oldProcInstance, Execution oldProcExe);
}
