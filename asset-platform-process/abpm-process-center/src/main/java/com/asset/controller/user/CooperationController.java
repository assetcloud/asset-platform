package com.asset.controller.user;

import com.asset.dto.CoopCommitFormProcDTO;
import com.asset.service.impl.CooperationService;
import com.asset.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 专门用于协作的一个controller,负责对外提供接口
 */
    @Api(tags = "对外开放协作API列表")
    @RequestMapping(value = "/coop")
    @RestController
    public class CooperationController {

        @Autowired
        CooperationService cooperationService;

    @ApiOperation(value = "调用表单流程引擎",notes = "用于第三方调用业务中台的表单流程引擎")
    @PostMapping(value = "/form_proc/commit")
    public R commitFormProc(@RequestBody CoopCommitFormProcDTO dto) {
        String procInstId = null;
        try {
            procInstId = cooperationService.commitFormProc(dto);
        } catch (DocumentException e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.data(procInstId);
    }

    @ApiOperation(value = "获取业务中台实例运行情况",notes = "获取之前通过业务中台发起的表单流程的运行情况，-1——运行出错，0——该流程还没开始运行，1——运行中，2——实例执行结束，且审批节点全部同意，" +
            "3——实例因为中途有审批人点击拒绝导致实例执行结束")
    @GetMapping("/form_proc/status")
    public R getFormProcStatus(
            @ApiParam(value = "通过业务中台发起表单流程成功后返回的实例Id",required = true)
            @RequestParam(value = "proc_inst_id")String procInstId){
        int status = cooperationService.getFormProcStatus(procInstId);
        return R.data(status);
    }
}
