package com.asset.service;


import com.asset.utils.PageGrids;

/**
 * @author lichao
 */
public interface ProcTaskService {

    /**
     * 流程待办任务查询分页数据
     * @param pageNum
     * @param pageSize
     * @param id_
     * @param assignee
     * @return PageGrids
     */
    PageGrids getToDoTaskPages(Integer pageNum, Integer pageSize, String id_, String assignee);
    /**
     * 流程待办任务查询分页数据
     * @param pageNum
     * @param pageSize
     * @param id_
     * @param assignee
     * @return PageGrids
     */
    PageGrids getCompletePages(Integer pageNum, Integer pageSize, String id_, String assignee);
}
