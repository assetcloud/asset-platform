package com.asset.service;

import com.asset.entity.AsTempletDeModelDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 模板中所属的流程模型信息（除了flowable设计的内容之外还包含为了设计界面额外增加的字段——node_num和seq_condition） 服务类
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
public interface IAsTempletDeModelService extends IService<AsTempletDeModelDO> {

}
