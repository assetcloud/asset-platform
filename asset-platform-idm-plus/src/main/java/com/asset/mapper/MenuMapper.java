package com.asset.mapper;

import com.asset.bean.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByRoles(List<UserRole> roleIds);

    int batchAddPlatMenuRole(List<PlatMenuRole> records);

    List<PlatMenu> selectAll();

    List<PlatMenu> selectAll4SAdmin();
}
