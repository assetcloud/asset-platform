package com.asset.mapper;

import com.asset.bean.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByRoles(List<UserRole> roleIds);

    int batchAddMenuRole(List<MenuRole> records);

    List<Menu> selectAll();

    List<Menu> selectAll4SAdmin();
}
