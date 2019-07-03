package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.ActType;
import com.asset.rec.ProcModelRec;
import lombok.Data;

import java.util.List;

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
