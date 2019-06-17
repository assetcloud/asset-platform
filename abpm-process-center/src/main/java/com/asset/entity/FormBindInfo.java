package com.asset.entity;

import lombok.Data;

/**
 * @author yby
 * @time 190603 1553
 * @version 1.0_190603 1553
 */
@Data
public class FormBindInfo {
    String formModelID;
    String procModelID;

    public FormBindInfo(String formModelID, String procModelID) {
        this.formModelID = formModelID;
        this.procModelID = procModelID;
    }

}
