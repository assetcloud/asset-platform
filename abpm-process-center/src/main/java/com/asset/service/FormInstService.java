package com.asset.service;

import com.asset.entity.AsFormInst;
import com.asset.rec.FormInstCommitRec;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YBY
 * @time 190610_1059
 * @version 1.0
 */
@Service
public interface FormInstService {

    public int commitFormInst(FormInstCommitRec rec);

    List<AsFormInst> getFormInsts(String userID, Integer form_status);
}
