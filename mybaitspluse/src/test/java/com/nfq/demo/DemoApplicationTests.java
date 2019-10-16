package com.nfq.demo;

import com.nfq.demo.entity.Student;
import com.nfq.demo.mapper.StudentMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<Student> userList = studentMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }

}
