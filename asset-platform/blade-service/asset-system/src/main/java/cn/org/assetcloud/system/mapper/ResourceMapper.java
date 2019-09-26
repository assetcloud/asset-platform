package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.Resource;
import cn.org.assetcloud.system.entity.SceneRelation;
import cn.org.assetcloud.system.vo.ResourceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    int addResourceRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

    Resource getByPath(String applicationId);

    int batchInsert(List<Resource> list);

    int batchInsertResourceRole(List<Resource> list);

    List<Resource> findAppResourceByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Resource> findFormResourceByName(@Param("appName") String appName, @Param("sceneId") String sceneId);

    List<Resource> getAppResourceByUser(List<SceneRelation> relations);

    /**
     * 通过角色获取所有资源
     * @param relations
     * @return
     */
    List<Resource> getResourcesByUser(List<SceneRelation> relations);

    /**
     * 通过角色获取所属权限
     * @param roleId
     * @param sceneId
     * @return
     */
    List<Resource> getResourcesByRole(Long roleId, String sceneId);

    List<Resource> getAppResourcesByUser(String userId, String sceneId);

    List<Resource> getFormResourcesByApp(List<SceneRelation> relations, Long appResourceId);

    List<Resource> getFuncResourceByForm(List<SceneRelation> relations, Long formResourceId);

    int updateFormInfo(String formModelId, String sceneId);

    int updateFuncInfo(String formModelId, String sceneId);

    List<Resource> getFormByName(String formName, String sceneId, Long parentId);

    List<Resource> getFormByPath(String formModelId);

    List<ResourceVO> tree(@Param("sceneId") String sceneId);

    List<Resource> formList(Long appId, List<Long> roleIds);
}
