package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.ResourceRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by hjhu on 2019/5/28.
 */

@Mapper
public interface ResourceRoleMapper extends BaseMapper<ResourceRole> {

    int deleteResourceByRid(@Param("rid") Integer rid);

    int addResource(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
