package com.asset.entity;

import com.alibaba.fastjson.JSONObject;
import com.asset.rec.AsProcModelRec;

public class AsProcModel {
    private String procModelId;

    private String asJson;

    public AsProcModel(AsProcModelRec rec) {
        procModelId = rec.getProcModelId();
        asJson = JSONObject.toJSONString(rec.getActTypes());
    }

    public String getProcModelId() {
        return procModelId;
    }

    public void setProcModelId(String procModelId) {
        this.procModelId = procModelId == null ? null : procModelId.trim();
    }

    public String getAsJson() {
        return asJson;
    }

    public void setAsJson(String asJson) {
        this.asJson = asJson == null ? null : asJson.trim();
    }
}
