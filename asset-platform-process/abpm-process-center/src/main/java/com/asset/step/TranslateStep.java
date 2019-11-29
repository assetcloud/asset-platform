package com.asset.step;

import com.asset.service.FormInstService;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.utils.ProcUtils;
import org.flowable.form.api.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 一些可以快速获取某些值的方法
 */
@Component
public class TranslateStep {
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    FormInstService formInstService;

    public String procInstIdToFormModelId(String procInstId)
    {
        String procModelId = procInstService.getProcModelId(procInstId);
        return formModelService.getFormModelId(procModelId);
    }


    public String taskIdToProcModelId(String taskId)
    {
        String procInstId = formInstService.getProcInstId(taskId);
        return procInstService.getProcModelId(procInstId);
    }

}
