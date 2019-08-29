package com.asset.service.impl;

import com.asset.dao.ProcRuntimeMapper;
import com.asset.entity.ProcInstInfo;
import com.asset.service.IProcRuntimeService;
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
public class ProcRuntimeServiceImpl implements IProcRuntimeService {

    @Autowired
    ProcRuntimeMapper procRuntimeMapper;

    @Override
    public PageGrids getProcInstPages(Integer pageNum, Integer pageSize, String id_, String procDefId) {

        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<ProcInstInfo> list = procRuntimeMapper.selectProcInstPages(id_,procDefId);
        PageInfo<ProcInstInfo> pageInfo = new PageInfo<ProcInstInfo>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }
}
