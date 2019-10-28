package com.asset.controller.admin;

import com.asset.entity.AsProcInstDO;
import com.asset.javabean.AdminProcInstVO;
import com.asset.service.impl.AsProcInstService;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.asset.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "控制端：流程实例管理")
public class AdminProcInstController {

//    @Autowired
//    AdminProcInstService adminProcInstService;
    @Autowired
AsProcInstService asProcInstService;

    /**
     * 获取对应的流程实例的流程执行图
     * @param httpServletResponse
     * @param procInstId
     * @throws Exception
     */
    @GetMapping(value = "/proc_inst/diagram")
    @ApiOperation(value = "获取流程实例对应的流转图")
    public void genProcessDiagram(HttpServletResponse httpServletResponse,
                                  @ApiParam(value = "流程实例Id",required = true) @RequestParam("proc_inst_id") String procInstId) throws Exception {
        asProcInstService.getProcDiagram(httpServletResponse,procInstId);
    }

    /**
     * 修改流程实例状态——激活
     * @param procInstId
     */
    @ApiOperation(value = "激活流程实例")
    @PostMapping(value = "/proc_inst/activate")
    public R activateProcInst(@RequestParam("proc_inst_id")String procInstId){
        try {
            asProcInstService.activateProcInst(procInstId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.success("成功激活实例： "+ procInstId);
    }

    /**
     * 修改流程实例状态——挂起
     * @param procInstId
     */
    @ApiOperation(value = "挂起流程实例")
    @PostMapping(value = "/proc_inst/suspend")
    public R suspendProcInst(@RequestParam("proc_inst_id")String procInstId){
        try {
            asProcInstService.suspendProcInst(procInstId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.success("成功挂起实例： "+procInstId);
    }

    /**
     * 删除流程实例
     * @param procInstId
     */
    @ApiOperation(value = "删除运行中的流程实例")
    @DeleteMapping(value = "/proc_inst/{proc_inst_id}")
    public R deleteProcInst(@PathVariable("proc_inst_id")String procInstId){
        asProcInstService.deleteProcInst(procInstId);
        return R.success("删除实例： "+procInstId);
    }

    /**
     * 展示流程实例信息
     * @return
     */
    @ApiOperation(value = "获取流程实例列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", defaultValue = "1", required = true ,paramType = "query", dataType = "integer"),
            @ApiImplicitParam(name = "size", value = "数据量大小", defaultValue = "10",required = true , paramType = "query", dataType = "integer")
    })
    @GetMapping(value = "/proc_inst/list")
    public R show(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        Page page = PageHelper.startPage(query.getPage(), query.getSize());
        QueryWrapper<AsProcInstDO> queryWrapper = Condition.getQueryWrapper(role, AsProcInstDO.class);
//        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminProcInstVO> list = new PageInfo<>(asProcInstService.listAdminProcInstInfo(queryWrapper));
        list.setTotal(page.getTotal());
        return R.data(list);
    }


}
