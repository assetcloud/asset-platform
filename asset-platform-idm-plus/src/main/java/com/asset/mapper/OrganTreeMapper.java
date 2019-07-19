package com.asset.mapper;

import com.asset.bean.OrganScene;
import com.asset.bean.OrganTree;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrganTreeMapper extends BaseMapper<OrganTree> {

    int deleteByPrimaryKey(String id);

    Integer insert(OrganTree record);

    int insertSelective(OrganTree record);

    OrganTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrganTree record);

    int updateByPrimaryKey(OrganTree record);

    OrganTree getNodeByName(String unitName);

    List<OrganTree> getMainTree();

    List<OrganTree> selectAll();

    List<OrganTree> recursiveSelect(@Param("id") String id);

    List<OrganTree> recursiveSelectWithScene(@Param("parentId") String parentId, @Param("sceneId") String sceneId);

    List<OrganTree> getTreeByScene(@Param("id") String id);

    int batchUpdate(List<OrganTree> organTrees);

    List<OrganTree> searchNode(@Param("unitName") String unitName);

    //TODO：批量新增节点到组织场景关联表
    int batchInsert(List<OrganScene> nodes);

    OrganTree getTopNode();

    List<OrganTree> getByName(String unitName);

    int batchInsertNode(List<OrganTree> list);
}
