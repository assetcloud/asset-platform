package com.asset.mapper;

import com.asset.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lichao
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 获得组织架构数据
     * @return List<Organization>
     */
    List<Organization> getOrganizationList();
}
