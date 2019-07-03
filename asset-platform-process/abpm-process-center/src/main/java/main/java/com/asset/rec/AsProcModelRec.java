package com.asset.rec;

import com.alibaba.fastjson.annotation.JSONField;
import com.asset.javabean.ActType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AsProcModelRec {
    @JSONField(name = "proc_model_id")
    String procModelId;

    @JSONField(name = "data")
    List<ActType> actTypes = new ArrayList<>();


    public void addActType(ActType actType){
        actTypes.add(actType);
    }
}
