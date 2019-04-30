package com.asset.controller;

import com.asset.service.ProcRepositoryService;
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
public class ProcRepositoryController {

    @Autowired
    ProcRepositoryService procRepositoryService;

    /**
     * 流程定义页面
     * @return
     */
    @RequestMapping("/procDefIndex")
    public String procDefIndex() {
        return "pages/procdef/procdef_list";
    }

    /**
     * 查询流程定义分页数据
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    @RequestMapping("/repository/queryProcDef")
    @ResponseBody
    public PageGrids queryProcDef(@RequestParam("page") Integer pageNum,
                                  @RequestParam("rows") Integer pageSize,
                                  @RequestParam String id_,
                                  @RequestParam String name_) {
        if(pageNum==null ||pageNum<=0){
            pageNum = 1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = procRepositoryService.getProcDefPages(pageNum,pageSize,id_,name_);
        return pageGrids;
    }

    /**
     * 流程定义页面
     * @return
     */
    @RequestMapping("/bizRulesIndex")
    public String bizRulesIndex() {
        return "pages/bizrules/bizrules_list";
    }
}
