package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.Role;
import cn.org.assetcloud.system.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjhu
 * @since 2019-07-17
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<UserRole> getRoles(@Param("userId") String userId);
}
