package com.asset.mapper;

import com.asset.bean.PlatMenu;
import com.asset.bean.PlatMenuRole;
import com.asset.bean.PlatRole;
import com.asset.bean.UserScene;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 平台级菜单Mapper
 * Mapper接口
 * @author hjhu
 */
@Component
public interface PlatMenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PlatMenu record);

    int insertSelective(PlatMenu record);

    PlatMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatMenu record);

    int updateByPrimaryKey(PlatMenu record);

    List<PlatMenu> getPlatMenusByRoles(List<UserScene> roleIds);

    int batchAddPlatMenuRole(List<PlatMenuRole> records);

    /**
     * 获取所有可用菜单
     * @return List<PlatMenu>
     */
    List<PlatMenu> selectAll();

    /**
     * 获取组织管理员所有可用菜单
     * @return List<PlatMenu>
     */
    List<PlatMenu> selectAll4SAdmin();
}
