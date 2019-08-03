package com.asset.service;

import com.asset.dao.LinkFormProcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作as_form_proc表
    @author YBY
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FormProcService {
    @Autowired
    LinkFormProcMapper linkFormProcMapper;

    public String getBindFormModelId(String procModelId) {
        return linkFormProcMapper.getBindFormModelId(procModelId);
    }

    public void bindFormAndProc(String formModelId, String procModelId) {
        linkFormProcMapper.bindFormAndProc(formModelId,procModelId);
    }
}
