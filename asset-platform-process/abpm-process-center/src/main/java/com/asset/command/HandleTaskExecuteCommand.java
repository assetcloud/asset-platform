package com.asset.command;

import com.alibaba.fastjson.JSONObject;
import com.asset.dto.FormInstRecApprove;
import com.asset.dto.FormInstRecApproval;
import com.asset.dto.FormInstRecReadle;
import com.asset.entity.AsTaskDO;
import com.asset.mapper.AsTaskMapper;
import com.asset.service.ProcInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理Task执行
 */
@Component
public class HandleTaskExecuteCommand {

    @Autowired
    AsTaskMapper asTaskMapper;
    @Autowired
    ProcInstService procInstService;

    /**
     * 处理审批任务
     */
    public void handleApproveTask(FormInstRecApprove recBase) {
        String formInstSheet = JSONObject.toJSONString(recBase.getForm_inst_sheet());
        AsTaskDO inst = AsTaskDO.builder()
                .id(recBase.getTask_id())
                .approveResult(recBase.getApprove_result())
                .executor(recBase.getEditor())
                .procInstId(recBase.getProc_inst_id())
                .formInstValue(recBase.getForm_inst_value())
                .formInstSheet(formInstSheet)
                .build();
        asTaskMapper.updateById(inst);
        procInstService.updateFormValueForAll(recBase.getForm_inst_value(), inst.getProcInstId());
    }

    /**
     * 处理待办任务
     */
    public void handleApprovalTask(FormInstRecApproval recBase) {
        String formInstSheet = JSONObject.toJSONString(recBase.getForm_inst_sheet());
        AsTaskDO inst = AsTaskDO.builder()
                .id(recBase.getTask_id())
                .procInstId(recBase.getProc_inst_id())
                .formInstValue(recBase.getForm_inst_value())
                .formInstSheet(formInstSheet)
                .executor(recBase.getEditor())
                .build();
        asTaskMapper.updateById(inst);
        procInstService.updateFormValueForAll(recBase.getForm_inst_value(), inst.getProcInstId());
    }


    /**
     * 处理待阅任务
     */
    public void handleReadTask(FormInstRecReadle recBase) {
        AsTaskDO inst = AsTaskDO.builder()
                .id(recBase.getTask_id())
                .executor(recBase.getEditor())
                .build();
        asTaskMapper.updateById(inst);
    }

}
