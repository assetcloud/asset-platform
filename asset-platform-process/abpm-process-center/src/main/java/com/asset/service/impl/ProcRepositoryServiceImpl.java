package com.asset.service.impl;

import com.asset.dao.ProcRepositoryMapper;
import com.asset.entity.ProcDefInfo;
import com.asset.service.IProcRepositoryService;
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
public class ProcRepositoryServiceImpl implements IProcRepositoryService {

    @Autowired
    ProcRepositoryMapper procRepositoryMapper;

    @Override
    public PageGrids getProcDefPages(Integer pageNum, Integer pageSize, String id_, String name_) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<ProcDefInfo> list = procRepositoryMapper.selectProcDefPages(id_,name_);
        PageInfo<ProcDefInfo> pageInfo = new PageInfo<ProcDefInfo>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }
}
