package com.asset.controller;

import com.asset.javabean.AdminProcInstVO;
import com.asset.javabean.AdminProcTaskVO;
import com.asset.javabean.RespBean;
import com.asset.service.AdminProcInstService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class AdminProcInstController {

    @Autowired
    AdminProcInstService adminProcInstService;

    /**
     * 获取对应的流程实例的流程执行图
     * @param httpServletResponse
     * @param procInstId
     * @throws Exception
     */
    @GetMapping(value = "/proc_inst/diagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse,
                                  @Param("proc_inst_id") String procInstId) throws Exception {
        adminProcInstService.getProcDiagram(httpServletResponse,procInstId);
    }

    @PostMapping(value = "/proc_inst/activate")
    public void activateProcInst(@Param("proc_inst_id")String procInstId){
        adminProcInstService.activateProcInst(procInstId);
    }

    @PostMapping(value = "/proc_inst/suspend")
    public void suspendProcInst(@Param("proc_inst_id")String procInstId){
        adminProcInstService.suspendProcInst(procInstId);
    }

    @DeleteMapping(value = "/proc_inst/{proc_inst_id}")
    public void deleteProcInst(@PathVariable("proc_inst_id")String procInstId){
        adminProcInstService.deleteProcInst(procInstId);
    }

    @GetMapping(value = "/proc_inst/list")
    public RespBean show(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<AdminProcInstVO> list = new PageInfo<>(adminProcInstService.getProcInsts());
        return RespBean.ok("",list);
    }

    @GetMapping(value = "/proc_task/list")
    public RespBean showTasks(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<AdminProcTaskVO> list = new PageInfo<>(adminProcInstService.getProcTasks());
        return RespBean.ok("",list);
    }
}
