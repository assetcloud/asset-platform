package com.asset.mapper;

import com.asset.bean.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjhu
 * @since 2019-07-18
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    int addResourceRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    Resource getByPath(String applicationId);

    int batchInsert(List<Resource> list);

    int batchInsertResourceRole(List<Resource> list);

    List<Resource> findAppResourceByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Resource> findFormResourceByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Resource> getResourcesByUserAndScene(@Param("userId")String userId, @Param("sceneId")String sceneId);

    List<Resource> getResourcesByUser(String userId, String sceneId);

    List<Resource> getResourcesByRole(Long roleId, String sceneId);

    List<Resource> getAppResourcesByUser(String userId, String sceneId);

    List<Resource> getFormResourcesByApp(String userId, Long appResourceId, String sceneId);

    List<Resource> getFuncResourceByForm(String userId, Long formResourceId, String sceneId);

    int updateFormInfo(String formModelId, String sceneId);

    int updateFuncInfo(String formModelId, String sceneId);

    List<Resource> getFormByName(String formName, String sceneId, Long parentId);

    List<Resource> getFormByPath(String formModelId);
}
