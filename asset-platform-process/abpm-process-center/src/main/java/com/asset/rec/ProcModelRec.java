package com.asset.rec;

import com.asset.javabean.ActTypeRec;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProcModelRec {
    String proc_model_id;
    List<ActTypeRec> data = new ArrayList<>();
}
