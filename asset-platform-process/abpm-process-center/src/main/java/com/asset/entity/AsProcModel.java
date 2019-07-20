package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.dto.ProcModelRec;
import lombok.Data;

@Data
public class AsProcModel {
    private String procModelId;

    private String asJson;

    public AsProcModel() {
    }

    public AsProcModel(ProcModelRec rec) {
        procModelId = rec.getProc_model_id();
        asJson = JSONObject.toJSONString(rec.getData());
    }

}
