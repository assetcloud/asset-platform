package com.asset.mapper;

import com.asset.bean.Role;
import com.asset.bean.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
@Component
public interface RoleMapper extends BaseMapper<Role> {

    List<UserRole> getRoles(@Param("userId")String userId);
}
