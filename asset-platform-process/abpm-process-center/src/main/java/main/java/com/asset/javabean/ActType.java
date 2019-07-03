package com.asset.javabean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ActType {
    @JSONField(name = "act_id")
    String actID;

    @JSONField(name = "type")
    int actType;

    public ActType(String actID, int actType) {
        this.actID = actID;
        this.actType = actType;
    }
}
