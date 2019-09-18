package com.asset.utils;

import com.asset.service.FormInstService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成流程实例、流程模型的流程示意图
 *
 * @author YBY
 */
@Service
public class ProcDiagramGenerator {

    @Autowired
    ModelService modelService;
    @Autowired
    FormInstService formInstService;

    static ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static RepositoryService repositoryService = engine.getRepositoryService();
    static RuntimeService runtimeService = engine.getRuntimeService();
    static TaskService taskService = engine.getTaskService();
    static HistoryService historyService = engine.getHistoryService();

    static Logger logger = LoggerFactory.getLogger(ProcDiagramGenerator.class);

    public InputStream genProcessDiagramInputStream(HttpServletResponse httpServletResponse, String procInstId) {
        //获得当前活动的节点
        String processDefinitionId = "";
        if (ProcUtils.isFinished(procInstId)) {// 如果流程已经结束，则得到结束节点
            HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            processDefinitionId = pi.getProcessDefinitionId();
        } else {
            // 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            processDefinitionId = pi.getProcessDefinitionId();
        }
        List<String> highLightedActivities = new ArrayList<String>();

        //获得活动的节点
        List<HistoricActivityInstance> highLightedActivityList = historyService.createHistoricActivityInstanceQuery().
                processInstanceId(procInstId).orderByHistoricActivityInstanceStartTime().asc().list();

        for (HistoricActivityInstance tempActivity : highLightedActivityList) {
            String activityId = tempActivity.getActivityId();
            String taskId = tempActivity.getTaskId();
            //这边还需要增加对这个节点是不是被终止看一下情况
            if (tempActivity.getActivityType().equals("userTask") &&
                    formInstService.isFormInstExecuted(taskId))
                highLightedActivities.add(activityId);
        }

        List<String> flows = new ArrayList<>();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessEngineConfiguration engconf = engine.getProcessEngineConfiguration();

        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in = diagramGenerator.generateDiagram(bpmnModel,
//                "bmp",
//                highLightedActivities,
//                flows,
//                engconf.getActivityFontName(),
//                engconf.getLabelFontName(),
//                engconf.getAnnotationFontName(),
//                engconf.getClassLoader(),
//                1.0,
//                true);
        InputStream in = diagramGenerator.generateDiagram(bpmnModel,
                "PNG",
                highLightedActivities,
                flows,
                "宋体",
                "宋体",
                "宋体",
                engconf.getClassLoader(),
                1.0,
                false);
        return in;
    }

}
