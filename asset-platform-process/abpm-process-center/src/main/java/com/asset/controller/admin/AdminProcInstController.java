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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
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
    public void genProcessDiagram(HttpServletResponse httpServletResponse,
                                  @Param("proc_inst_id") String procInstId) throws Exception {
        asProcInstService.getProcDiagram(httpServletResponse,procInstId);
    }

    /**
     * 修改流程实例状态——激活
     * @param procInstId
     */
    @PostMapping(value = "/proc_inst/activate")
    public void activateProcInst(@Param("proc_inst_id")String procInstId){
        asProcInstService.activateProcInst(procInstId);
    }

    /**
     * 修改流程实例状态——挂起
     * @param procInstId
     */
    @PostMapping(value = "/proc_inst/suspend")
    public void suspendProcInst(@Param("proc_inst_id")String procInstId){
        asProcInstService.suspendProcInst(procInstId);
    }

    /**
     * 删除流程实例
     * @param procInstId
     */
    @DeleteMapping(value = "/proc_inst/{proc_inst_id}")
    public void deleteProcInst(@PathVariable("proc_inst_id")String procInstId){
        asProcInstService.deleteProcInst(procInstId);
    }

    /**
     * 展示流程实例信息
     * @return
     */
    @GetMapping(value = "/proc_inst/list")
    public RespBean show(@ApiIgnore @RequestParam Map<String, Object> role, Query query){
        QueryWrapper<AsProcInst> queryWrapper = Condition.getQueryWrapper(role, AsProcInst.class);
        PageHelper.startPage(query.getPage(),query.getSize());
        PageInfo<AdminProcInstVO> list = new PageInfo<>(asProcInstService.listAdminProcInstInfo(queryWrapper));
        return RespBean.ok("",list);
    }


}
