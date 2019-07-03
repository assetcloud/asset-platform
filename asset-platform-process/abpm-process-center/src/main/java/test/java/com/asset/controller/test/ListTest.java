package com.asset.controller.test;

import com.asset.FlowableApplication;
import com.asset.dao.TestMapper;
import com.asset.entity.AsProcModel;
import com.asset.javabean.ActType;
import com.asset.javabean.TestJava;
import com.asset.rec.AsProcModelRec;
import com.asset.service.AsProcModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class ListTest {

    @Autowired
    TestMapper testMapper;
    @Autowired
    AsProcModelService procModelService;

    @Test
    public void testList(){
        List<TestJava> tasks =new ArrayList<>();
        tasks.add(new TestJava("67591ea7-9191-11e9-b1b3-2e15a8856301","1"));
        tasks.add(new TestJava("6d1e049e-9191-11e9-b1b3-2e15a8856301","2"));
        tasks.add(new TestJava("6d1f1611-9191-11e9-b1b3-2e15a8856301","3"));
        ArrayList<String> strings = (ArrayList<String>) testMapper.getString(tasks);
    }

    @Test
    public void testJson(){
        AsProcModelRec rec = new AsProcModelRec();
        rec.setProcModelId("123456789");
        rec.addActType(new ActType("userTask1",1));
        rec.addActType(new ActType("userTask2",2));
        AsProcModel model = new AsProcModel(rec);

//        procModelService.createFormModel(rec);



//        List<ActType> gameList = JSONObject.parseArray(model.getAsJson(), ActType.class);
    }
}
