package com.asset.service.test;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Students {
    ArrayList<Student> students;

    public Students(){
        students = new ArrayList<>();
        students.add(new Student(1));
        students.add(new Student(2));
        students.add(new Student(3));
    }
}
