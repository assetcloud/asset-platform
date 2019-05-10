package com.asset.dao;

import com.asset.entity.Organization;
import com.asset.entity.ProcDefInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lichao
 */
@Repository
public interface OrganizationMapper {

    /**
     * 获得组织架构数据
     * @return List<Organization>
     */
    List<Organization> getOrganizationList();
}
