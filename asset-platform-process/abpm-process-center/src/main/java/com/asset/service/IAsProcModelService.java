package com.asset.service;

import com.asset.entity.AsProcModel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-07
 */
public interface IAsProcModelService extends IService<AsProcModel> {
    public void saveProcNodeNum(String procModelId,Integer procNodeNum)throws Exception;

    int getProcNodeNum(String procModelId);
}
