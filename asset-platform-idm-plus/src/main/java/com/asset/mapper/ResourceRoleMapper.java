package com.asset.mapper;

import com.asset.bean.ResourceRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by hjhu on 2019/5/28.
 */

@Component
public interface ResourceRoleMapper extends BaseMapper<ResourceRole> {

    int deleteResourceByRid(@Param("rid") Integer rid);

    int addResource(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
