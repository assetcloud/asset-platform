package com.asset.dao;

import com.asset.entity.ProcDefInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lichao
 */
@Repository
public interface ProcRepositoryMapper {

    /**
     * 查询流程定义数据
     * @param id_
     * @param name_
     * @return List<ProcDefInfo>
     */
    List<ProcDefInfo> selectProcDefPages(@Param("id_") String id_, @Param("name_") String name_);
}
