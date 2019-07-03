package com.asset.rec;

import com.asset.javabean.ActType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProcModelRec {
    String proc_model_id;

    List<ActType> data = new ArrayList<>();


    public void addActType(ActType actType){
        data.add(actType);
    }
}
