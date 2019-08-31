package com.asset.mapper;

import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrganTreeMapper extends BaseMapper<OrganTree> {

    int deleteByPrimaryKey(String id);

    int insert(OrganTree record);

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

    //TODO：批量新增节点到组织场景关联表
    int batchInsert(List<OrganScene> nodes);

    OrganTree getTopNode();

    List<OrganTree> getByName(String unitName);

    int batchInsertNode(List<OrganTree> list);
}
