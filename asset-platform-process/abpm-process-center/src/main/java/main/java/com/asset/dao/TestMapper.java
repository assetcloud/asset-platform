package com.asset.dao;

import com.asset.javabean.TestJava;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {


    public List<String> getString(List<TestJava> tasks);
}
