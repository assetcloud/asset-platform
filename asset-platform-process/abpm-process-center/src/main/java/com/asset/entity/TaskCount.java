package com.asset.entity;

import lombok.Data;

@Data
public class TaskCount {
    int taskType;
    int count;

    public TaskCount() {
    }

    public TaskCount(int taskType, int count) {
        this.taskType = taskType;
        this.count = count;
    }
}

