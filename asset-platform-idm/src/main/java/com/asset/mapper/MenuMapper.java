package com.asset.mapper;

import com.asset.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    int addMenuRole(@Param("menuId") Long menuId, @Param("roleId") Integer roleId);

    Menu getByPath(String applicationId);

    int batchInsert(List<Menu> list);

    int batchInsertMenuRole(List<Menu> list);

    List<Menu> getMenusByRole(@Param("roleId")Integer roleId);

//    List<Menu> getMenusByRole2(@Param("roleId")Integer roleId);
}
