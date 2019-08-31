package com.asset.dao;

import com.asset.entity.AsTempletDeModelDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 模板中所属的流程模型信息（除了flowable设计的内容之外还包含为了设计界面额外增加的字段——node_num和seq_condition） Mapper 接口
 * </p>
 *
 * @author YBY
 * @since 2019-08-19
 */
public interface AsTempletDeModelMapper extends BaseMapper<AsTempletDeModelDO> {

}
