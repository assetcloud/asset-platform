package com.asset.controller;

import com.asset.service.IProcTaskService;
import com.asset.utils.Constants;
import com.asset.utils.PageGrids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lichao
 */
@Controller
@ApiIgnore
public class ProcTaskController {

    private Integer pageNum = 1;

    @Autowired
    IProcTaskService IProcTaskService;

    /**
     * 待办任务页面
     * @return
     */
    @RequestMapping("/todoTaskIndex")
    public String todoTaskIndex() {
        return "pages/task/todotask_list";
    }

    /**
     * 查询待办数据
     * @param  pageNum
     * @param  pageSize
     * @param  id_
     * @param  assignee
     * @return PageGrids
     */
    @RequestMapping("/repository/queryToDoTask")
    @ResponseBody
    public PageGrids queryToDoTask(@RequestParam("page") Integer pageNum,
                                   @RequestParam("rows") Integer pageSize,
                                   @RequestParam String id_,
                                   @RequestParam String assignee) {
        if(pageNum == null ||pageNum <= 0){
            pageNum = this.pageNum;
        }
        if(pageSize == null||pageSize <= 0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = IProcTaskService.getToDoTaskPages(pageNum,pageSize,id_,assignee);
        return pageGrids;
    }

    /**
     * 已完成任务页面
     * @return
     */
    @RequestMapping("/completeTaskIndex")
    public String completeTaskIndex() {
        return "pages/task/completetask_list";
    }

    /**
     * 查询待办数据
     * @param  pageNum
     * @param  pageSize
     * @param  id_
     * @param  assignee
     * @return PageGrids
     */
    @RequestMapping("/repository/queryCompleteTask")
    @ResponseBody
    public PageGrids queryCompleteTask(@RequestParam("page") Integer pageNum,
                                   @RequestParam("rows") Integer pageSize,
                                   @RequestParam String id_,
                                   @RequestParam String assignee) {
        if(pageNum == null ||pageNum <= 0){
            pageNum = this.pageNum;
        }
        if(pageSize == null||pageSize <= 0){
            pageSize = Constants.PageSize;
        }
        PageGrids pageGrids = IProcTaskService.getToDoTaskPages(pageNum,pageSize,id_,assignee);
        return pageGrids;
    }
}
