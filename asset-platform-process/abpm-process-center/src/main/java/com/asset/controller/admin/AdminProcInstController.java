package com.asset.controller.admin;

import com.asset.entity.AsProcInst;
import com.asset.javabean.AdminProcInstVO;
import com.asset.javabean.RespBean;
import com.asset.service.impl.AsProcInstServiceImpl;
import com.asset.utils.Condition;
import com.asset.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
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
    AsProcInstServiceImpl asProcInstService;

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
    public RespBean activateProcInst(@RequestParam("proc_inst_id")String procInstId){
        try {
            asProcInstService.activateProcInst(procInstId);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("成功激活实例： "+ procInstId);
    }

    /**
     * 修改流程实例状态——挂起
     * @param procInstId
     */
    @ApiOperation(value = "挂起流程实例")
    @PostMapping(value = "/proc_inst/suspend")
    public RespBean suspendProcInst(@RequestParam("proc_inst_id")String procInstId){
        try {
            asProcInstService.suspendProcInst(procInstId);
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
        return RespBean.ok("成功挂起实例： "+procInstId);
    }

    /**
     * 删除流程实例
     * @param procInstId
     */
    @ApiOperation(value = "删除运行中的流程实例")
    @DeleteMapping(value = "/proc_inst/{proc_inst_id}")
    public RespBean deleteProcInst(@PathVariable("proc_inst_id")String procInstId){
        asProcInstService.deleteProcInst(procInstId);
        return RespBean.ok("成功激活实例： "+procInstId);
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
    public RespBean show(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        QueryWrapper<AsProcInst> queryWrapper = Condition.getQueryWrapper(role, AsProcInst.class);
        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminProcInstVO> list = new PageInfo<>(asProcInstService.listAdminProcInstInfo(queryWrapper));
        return RespBean.ok("",list);
    }


}
