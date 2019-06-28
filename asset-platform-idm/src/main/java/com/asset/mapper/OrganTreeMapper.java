package com.asset.mapper;

import com.asset.bean.OrganTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrganTreeMapper {

    int deleteByPrimaryKey(String id);

    int insert(OrganTree record);

    int insertSelective(OrganTree record);

    OrganTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrganTree record);

    int updateByPrimaryKey(OrganTree record);

    OrganTree getNodeByName(String unitName);

    List<OrganTree> getMainTree();

    List<OrganTree> recursiveSelect(@Param("id") String id);

    List<OrganTree> recursiveSelectWithScene(@Param("parentId") String parentId, @Param("sceneId") String sceneId);

    List<OrganTree> getTreeByScene(@Param("id") String id);

    int batchUpdate(List<OrganTree> organTrees);
}
