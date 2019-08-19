package com.asset.dao;

import com.asset.entity.AsTempletDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YBY
 * @since 2019-08-17
 */
@Mapper
public interface AsTempletMapper extends BaseMapper<AsTempletDO> {
    List<AsTempletDO> selectAll();
}
