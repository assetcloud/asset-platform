package com.asset.service;


import com.asset.utils.PageGrids;

/**
 * @author lichao
 */
public interface ProcRepositoryService {


    /**
     * 流程定义查询分页
     * @param pageNum
     * @param pageSize
     * @param id_
     * @param name_
     * @return
     */
    PageGrids getProcDefPages(Integer pageNum, Integer pageSize, String id_, String name_);
}
