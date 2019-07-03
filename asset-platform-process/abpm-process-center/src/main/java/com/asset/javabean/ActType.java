package com.asset.javabean;

import lombok.Data;

@Data
public class ActType {
    String act_id;

    int act_type;

    public ActType() {
    }

    public ActType(String act_id, int act_type) {
        this.act_id = act_id;
        this.act_type = act_type;
    }
}
