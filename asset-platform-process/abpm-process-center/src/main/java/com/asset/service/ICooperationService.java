package com.asset.service;

import com.asset.dto.CoopCommitFormProcDTO;
import org.dom4j.DocumentException;

public interface ICooperationService {
    String commitFormProc(CoopCommitFormProcDTO dto) throws DocumentException;

    Integer getFormProcStatus(String procInstId);
}
