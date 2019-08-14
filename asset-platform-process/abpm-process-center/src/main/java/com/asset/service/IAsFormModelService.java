package com.asset.service;

import com.asset.entity.AsFormModel;
import com.asset.javabean.AdminFormModelVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-03
 */
public interface IAsFormModelService extends IService<AsFormModel> {

    public List<AdminFormModelVO> listAdminFormModelInfo(QueryWrapper<AsFormModel> queryWrapper);


}
