package com.asset.javabean;

import lombok.Data;

@Data
public class TestJava {
    String taskID;
    String id;

    public TestJava(String taskID, String id) {
        this.taskID = taskID;
        this.id = id;
    }
}
