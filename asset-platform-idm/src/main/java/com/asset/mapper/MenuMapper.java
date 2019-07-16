package com.asset.mapper;

import com.asset.bean.Application;
import com.asset.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    int addMenuRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    Menu getByPath(String applicationId);

    int batchInsert(List<Menu> list);

    int batchInsertMenuRole(List<Menu> list);

    List<Menu> findAppMenuByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Menu> findFormMenuByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Menu> getMenusByUserAndScene(@Param("userId")String userId, @Param("sceneId")String sceneId);

    List<Menu> getMenusByUser(@Param("userId")String userId);

    List<Menu> getMenusByRole(@Param("roleId")Long roleId);

    List<Menu> getAppMenusByUser(@Param("userId")String userId);

    List<Menu> getFormMenusByApp(@Param("userId")String userId, @Param("appId")String appId, @Param("sceneId")String sceneId);

    int updateFormInfo(@Param("formModelId")String formModelId, @Param("sceneId")String sceneId);

}
