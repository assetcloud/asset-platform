package com.asset.service;

import com.asset.entity.AsTempletDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-17
 */
public interface IAsTempletService extends IService<AsTempletDO> {
    public List<AsTempletDO> listAll();
}
