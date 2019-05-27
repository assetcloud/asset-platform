package com.asset.utils;


import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 这个工具类用于查询所有运行的流程实例以及其他队流程实例的操作
 * @author yby
 * @time 190522 1456
 * @version 1.0_190522 1456
 */

public class ProcInstUtils {

    static Logger logger = LoggerFactory.getLogger(ProcInstUtils.class);
    static ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    static RepositoryService repositoryService = engine.getRepositoryService();
    static RuntimeService runtimeService = engine.getRuntimeService();
    //查询当前执行到的任务
    static TaskService taskService = engine.getTaskService();
    static HistoryService historyService = engine.getHistoryService();

    /**
     * @return 返回所有运行的流程实例列表
     */
    public static List<ProcessInstance> getProcInstances() {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> lists = query.active().list();
        if(lists.size()==0)
        {
            logger.error("The size of instance list is 0!");
            return null;
        }
        return lists;
    }

    /**
     * 根据传入的instanceID得到流程实例
     * @param instID
     * @return
     */
    public static ProcessInstance getProcInstance(String instID) {
        ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery().active().processInstanceId(instID);
        List<ProcessInstance> lists = query.list();
        if(lists.size()==0)
        {
            logger.error("The size of instance list is 0!");
            return null;
        }
        logger.info("curInst is {},ID is {}",
                lists.get(0).getName(),
                lists.get(0).getProcessInstanceId());
        return lists.get(0);
    }

    /**
     * 获取当前流程实例的执行的task
     * @param instID
     * @return
     */
    public static Task getCurTask(String instID)
    {
        ProcessInstance instance = getProcInstance(instID);
        if (instance ==null)
        {
            logger.error("curInstance is null!");
            return null;
        }
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId()).list();
        if(tasks.size()==0)
        {
            logger.error("curTask is null!");
            return null;
        }
        logger.info("curTask is {}",tasks.get(0).getName());
        return tasks.get(0);
    }

    /**
     * 完成当前流程实例的任务
     * @param instID
     * @param formKey
     */
    public static void complete(String instID, Map<String, Object> formKey) {
        Task task = getCurTask(instID);
        if (task == null)
        {
            logger.error("curTask cannot be completed,curTask is null!");
            return;
        }
        taskService.complete(task.getId(),formKey);
        logger.info("{} completed",task.getName());
    }
}
