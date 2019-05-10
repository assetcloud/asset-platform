package com.asset.service;


import com.asset.utils.PageGrids;

/**
 * @author lichao
 */
public interface OrganizationService {


    /**
     *
     * @param pageNum
     * @param pageSize
     * @return PageGrids
     */
    PageGrids getOrganizationList(Integer pageNum, Integer pageSize);
}
