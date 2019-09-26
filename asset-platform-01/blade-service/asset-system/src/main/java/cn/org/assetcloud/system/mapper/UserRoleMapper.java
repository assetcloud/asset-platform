package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
}
