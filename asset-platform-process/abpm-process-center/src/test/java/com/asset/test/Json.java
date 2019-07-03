package com.asset.test;

import com.alibaba.fastjson.JSONArray;
import com.asset.FlowableApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class Json {

    @Test
    public void testJson(){
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        try{
            int curActType = map.get("sasa");
        }catch (NullPointerException e)
        {
            System.out.println("请检查");
        }

    }
}
