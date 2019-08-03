package com.asset.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * as_form_proc表
 * @author YBY
 */
@Repository
public interface LinkFormProcMapper {
    String getBindFormModelId(String procModelId);

    void bindFormAndProc(@Param("formModelId") String formModelId,
                         @Param("procModelId") String procModelId);
}
