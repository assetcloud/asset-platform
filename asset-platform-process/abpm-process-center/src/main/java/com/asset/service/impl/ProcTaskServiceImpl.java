package com.asset.service.impl;

import com.asset.dao.ProcTaskMapper;
import com.asset.entity.ProcTaskInfo;
import com.asset.service.IProcTaskService;
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
public class ProcTaskServiceImpl implements IProcTaskService {

    @Autowired
    ProcTaskMapper procTaskMapper;

    @Override
    public PageGrids getToDoTaskPages(Integer pageNum, Integer pageSize, String id_, String assignee) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<ProcTaskInfo> list = procTaskMapper.selectToDoTaskPages(id_,assignee);
        PageInfo<ProcTaskInfo> pageInfo = new PageInfo<ProcTaskInfo>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }

    @Override
    public PageGrids getCompletePages(Integer pageNum, Integer pageSize, String id_, String assignee) {
        PageGrids pageGrids = new PageGrids();
        PageHelper.startPage(pageNum,pageSize);
        List<ProcTaskInfo> list = procTaskMapper.selectCompleteTaskPages(id_,assignee);
        PageInfo<ProcTaskInfo> pageInfo = new PageInfo<ProcTaskInfo>(list);
        //总页数
        pageGrids.setTotal(pageInfo.getTotal());
        pageGrids.setRows(list);
        return pageGrids;
    }
}
