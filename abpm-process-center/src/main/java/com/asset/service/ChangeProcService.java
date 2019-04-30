package com.asset.service;

import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;

public interface ChangeProcService {


    void updateProcDef(ProcessInstance oldProcInstance, Execution oldProcExe);
}
