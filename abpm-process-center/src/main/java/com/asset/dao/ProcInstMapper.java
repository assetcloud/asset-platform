package com.asset.dao;

import org.springframework.stereotype.Repository;

/**
 * 流程实例相关的存取
 */
@Repository
public interface ProcInstMapper {
    String getExecutionID(String task_id);
}
