package com.asset.mapper;

import com.asset.bean.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceMapper {

    List<Resource> getAllResource();

    List<Resource> getResourcesByUserId(int userId);

    List<Resource> resourceTree();

    List<Integer> getResourcesByRid(int roleId);

    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}
