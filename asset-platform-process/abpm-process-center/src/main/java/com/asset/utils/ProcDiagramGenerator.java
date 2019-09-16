package com.asset.utils;

import org.apache.commons.io.IOUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于生成流程实例、流程模型的流程示意图
 * @author YBY
 */
public class ProcDiagramGenerator {
    @Autowired
    static ModelService modelService;

    static ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static RepositoryService repositoryService = engine.getRepositoryService();
    static RuntimeService runtimeService = engine.getRuntimeService();
    static TaskService taskService = engine.getTaskService();
    static HistoryService historyService = engine.getHistoryService();

    static Logger logger = LoggerFactory.getLogger(ProcDiagramGenerator.class);

    public static InputStream genProcessDiagramInputStream(HttpServletResponse httpServletResponse, String procInstId) {
        //获得当前活动的节点
        String processDefinitionId = "";
        if (ProcUtils.isFinished(procInstId)) {// 如果流程已经结束，则得到结束节点
            HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            processDefinitionId=pi.getProcessDefinitionId();
        } else {
            // 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
            processDefinitionId=pi.getProcessDefinitionId();
        }
        List<String> highLightedActivities = new ArrayList<String>();

        //获得活动的节点
        List<HistoricActivityInstance> highLightedActivityList = historyService.createHistoricActivityInstanceQuery().
                processInstanceId(procInstId).orderByHistoricActivityInstanceStartTime().asc().list();

        for(HistoricActivityInstance tempActivity : highLightedActivityList){
            String activityId = tempActivity.getActivityId();
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
