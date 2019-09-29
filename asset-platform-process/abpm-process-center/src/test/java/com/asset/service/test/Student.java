package com.asset.service.test;

import lombok.Data;

@Data
public class Student {
    int i;

    public Student(int name){
        this.i = name;
    }
}
