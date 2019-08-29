package com.asset.service;


import com.asset.utils.PageGrids;

/**
 * @author lichao
 */
public interface IProcRuntimeService {

    /**
     * 流程实例查询分页
     * @param pageNum
     * @param pageSize
     * @param id_
     * @param procDefId
     * @return
     */
    PageGrids getProcInstPages(Integer pageNum, Integer pageSize, String id_, String procDefId);
}
