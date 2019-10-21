package com.asset.controller.test;

import com.asset.FlowableApplication;
import com.asset.utils.ProcUtils;
import org.checkerframework.checker.units.qual.A;
import org.flowable.common.engine.impl.history.HistoryLevel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.test.HistoryTestHelper;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class AdminProcInstController {

    @Autowired
    static ModelService modelService;

    static ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static RepositoryService repositoryService = engine.getRepositoryService();
    static RuntimeService runtimeService = engine.getRuntimeService();
    static TaskService taskService = engine.getTaskService();
    static HistoryService historyService = engine.getHistoryService();

//    @Test
//    public void testSetActivityIntoSynchronizingParallelGatewayFirst() {
//        String procInstId = "";
//
//        runtimeService.createChangeActivityStateBuilder()
//                .processInstanceId()
//                .moveActivityIdTo("task1", "parallelJoin")
//                .changeState();
//    }

}
