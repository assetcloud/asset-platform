package com.asset.mapper;

import com.asset.entity.ProcTaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lichao
 */
@Mapper
public interface ProcTaskMapper {

    /**
     * 待办任务查询分页数据
     * @param  id_
     * @param  assignee
     * @return List<ProcTaskInfo>
     */
    List<ProcTaskInfo> selectToDoTaskPages(@Param("id_") String id_, @Param("assignee") String assignee);
    /**
     * 已结任务查询分页数据
     * @param  id_
     * @param  assignee
     * @return List<ProcTaskInfo>
     */
    List<ProcTaskInfo> selectCompleteTaskPages(@Param("id_") String id_, @Param("assignee") String assignee);
}
