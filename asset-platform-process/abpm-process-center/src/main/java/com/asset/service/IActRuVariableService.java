package com.asset.service;

import com.asset.entity.ActRuVariableDO;
import com.asset.javabean.ActRuVariableBO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-12
 */
public interface IActRuVariableService extends IService<ActRuVariableDO> {

    /**
     * 往act_ru_variable表中存储流程运行中变量
     */
    void saveRunVariable(ActRuVariableBO boo);
}
