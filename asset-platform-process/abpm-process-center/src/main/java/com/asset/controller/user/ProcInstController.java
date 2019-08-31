package com.asset.controller.user;

import com.asset.javabean.AdminProcInstVO;
import com.asset.service.ProcInstService;
import com.asset.utils.ProcUtils;
import com.asset.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YBY
 * @time 190723
 * @version 1.0_190723
 */
@RestController
@Api(tags = "终端：辅助流程管理（包含获取当前表单模型对应的实例信息)")
public class ProcInstController {
    @Autowired
    ProcInstService procInstService;

    /**
     * 在执行上面的流程测试的时候需要保证现有的流程实例都已经跑完了，否则会造成错误
     */
    @RequestMapping(value = "/proc_inst/insts/complete",method = RequestMethod.POST)
    public R completeAll()
    {
        ProcUtils.completeAll();
        return R.success("执行结束");
    }

    /**
     * 获取正在运行的与当前form_model_id相关的流程实例信息
     * 由form_model_id得到proc_model_id，再去as_proc_inst表中去找
     */
    @ApiOperation(value = "获取当前表单模型下正在运行的实例")
    @GetMapping(value = "/proc_inst/insts" )
    public R<List<AdminProcInstVO>> getProcInsts(@ApiParam(value = "当前选中的表单模型Id") @RequestParam("form_model_id") String formModelId){
        List<AdminProcInstVO> procInsts = procInstService.getProcInsts(formModelId);
        return R.data(procInsts);
    }

}
