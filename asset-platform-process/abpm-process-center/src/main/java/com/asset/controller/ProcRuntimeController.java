package com.asset.controller;

import com.asset.service.ProcRuntimeService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lichao
 */
@Controller
public class ProcRuntimeController {

    private Integer pageNum = 1;

    @Autowired
    ProcRuntimeService procRuntimeService;

    /**
     * 流程实例页面
     * @return
     */
    @RequestMapping("/procInstIndex")
    public String procInstIndex() {
        return "pages/procinst/procinst_list";
    }

    /**
     *
     * @param  pageNum
     * @param  pageSize
     * @param  id_
     * @param  proc_def_id_
     * @return PageGrids
     */
    @RequestMapping("/repository/queryProcInst")
    @ResponseBody
    public PageGrids queryProcInst(@RequestParam("page") Integer pageNum,
                                   @RequestParam("rows") Integer pageSize,
                                   @RequestParam String id_,
                                   @RequestParam String proc_def_id_) {
        if(pageNum==null ||pageNum<=0){
            pageNum = this.pageNum;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = procRuntimeService.getProcInstPages(pageNum,pageSize,id_,proc_def_id_);
        return pageGrids;
    }

}
