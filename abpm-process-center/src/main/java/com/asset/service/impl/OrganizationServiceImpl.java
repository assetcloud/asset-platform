package com.asset.service.impl;

import com.asset.dao.OrganizationMapper;
import com.asset.dao.ProcRepositoryMapper;
import com.asset.entity.Organization;
import com.asset.entity.ProcDefInfo;
import com.asset.service.OrganizationService;
import com.asset.service.ProcRepositoryService;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lichao
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public PageGrids getOrganizationList(Integer pageNum, Integer pageSize) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<Organization> list = organizationMapper.getOrganizationList();
        PageInfo<Organization> pageInfo = new PageInfo<Organization>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }
}
