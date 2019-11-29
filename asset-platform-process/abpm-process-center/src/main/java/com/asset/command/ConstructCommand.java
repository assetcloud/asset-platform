package com.asset.command;

import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsTaskDO;
import com.asset.javabean.form.FormSheet;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.service.ProcNodeService;
import com.asset.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 构建一些比较复杂的对象
 */
@Component
public class ConstructCommand {
    @Autowired
    ProcInstService procInstService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ProcNodeService procNodeService;

    /**
     * 一个任务执行完毕，写入数据库
     */
    public AsTaskDO constructCompleteTaskRecord(FormSheet formSheet,
                                   String procInstId,
                                   String editor,
                                   String formModelId,
                                   String formInstValue,
                                   String taskId) {
        String executionID = procInstService.getExecutionId(procInstId);
        String formSheetStr = JSONObject.toJSONString(formSheet);
        AsTaskDO taskDO = AsTaskDO.builder()
                .formModelId(formModelId)
                .procInstId(procInstId)
                .executionId(executionID)
                .id(taskId)
                .executeTime(new Date())
                .executor(editor)
                .formInstValue(formInstValue)
                .formInstSheet(formSheetStr).build();
        String procModelID = formModelService.getProcModelID(formModelId);

        if (procModelID.equals(Constants.REGISTER_PROC_ID) ||
                procModelID.equals(Constants.SCENE_SELECT_PROC_ID))
            taskDO.setNodeType(Constants.AS_NODE_APPLY);
        else {
            String firstNodeId = procNodeService.getFirstNodeId(procModelID);
            taskDO.setNodeType(procNodeService.getNodeType(procModelID, firstNodeId));
        }
        return taskDO;
    }

    /**
     * 一个任务尚未被执行，只是提前被写入数据库
     */
    public AsTaskDO constructUncompleteTaskRecord(FormSheet formSheet,
                                                String procInstId,
                                                String formModelId,
                                                String formInstValue,
                                                String taskId) {
        String executionID = procInstService.getExecutionId(procInstId);
        String formSheetStr = JSONObject.toJSONString(formSheet);
        AsTaskDO taskDO = AsTaskDO.builder()
                .formModelId(formModelId)
                .procInstId(procInstId)
                .executionId(executionID)
                .id(taskId)
                .formInstValue(formInstValue)
                .formInstSheet(formSheetStr).build();
        String procModelID = formModelService.getProcModelID(formModelId);

        if (procModelID.equals(Constants.REGISTER_PROC_ID) ||
                procModelID.equals(Constants.SCENE_SELECT_PROC_ID))
            taskDO.setNodeType(Constants.AS_NODE_APPLY);
        else {
            String firstNodeId = procNodeService.getFirstNodeId(procModelID);
            taskDO.setNodeType(procNodeService.getNodeType(procModelID, firstNodeId));
        }
        return taskDO;
    }


}
