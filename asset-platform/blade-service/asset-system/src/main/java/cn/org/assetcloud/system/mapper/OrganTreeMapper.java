package cn.org.assetcloud.system.mapper;

import cn.org.assetcloud.system.entity.OrganScene;
import cn.org.assetcloud.system.entity.OrganTree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface OrganTreeMapper extends BaseMapper<OrganTree> {

    int deleteByPrimaryKey(String id);

    int insertSelective(OrganTree record);

    OrganTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrganTree record);

    int updateByPrimaryKey(OrganTree record);

    OrganTree getNodeByName(String unitName);

    List<OrganTree> getMainTree();

    List<OrganTree> selectAll();

    List<OrganTree> recursiveSelect(@Param("id") String id);

    List<OrganTree> recursiveSelectWithScene(@Param("parentId") String parentId, @Param("sceneId") String sceneId);

    List<OrganScene> getTreeByScene(@Param("id") String id);

    List<OrganScene> getNodeByScene(@Param("id") String id, @Param("nodeId") String nodeId);

    int batchUpdate(List<OrganTree> organTrees);

    List<OrganTree> searchNode(@Param("unitName") String unitName);

    int batchInsert(List<OrganScene> nodes);

    OrganTree getTopNode();

    List<OrganTree> getByName(String unitName);

    int batchInsertNode(List<OrganTree> list);
}
