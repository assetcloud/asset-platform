package com.nfq.test;

import com.nfq.entity.Student;
import com.nfq.mapper.StudentMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void testInsert(){
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper studentMapper = context.getBean("studentMapper", StudentMapper.class);
        Student student = new Student(5,"zxs",23);
       int count  =  studentMapper.insert(student);
       System.out.println(count);

    }
    public static void main(String[] args) throws  Exception{
        testInsert();
    }
}
