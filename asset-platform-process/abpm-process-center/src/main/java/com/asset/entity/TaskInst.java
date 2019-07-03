package com.asset.entity;

import lombok.Data;

@Data
public class TaskInst {
    String taskId;
    String actId;
    String procInstId;

    public TaskInst() {
    }
}
