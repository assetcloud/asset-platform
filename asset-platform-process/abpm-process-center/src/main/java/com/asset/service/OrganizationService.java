package com.asset.service;

import com.asset.dao.OrganizationMapper;
import com.asset.entity.Organization;
import com.asset.utils.PageGrids;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lichao
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrganizationService {

    @Autowired
    OrganizationMapper organizationMapper;

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
