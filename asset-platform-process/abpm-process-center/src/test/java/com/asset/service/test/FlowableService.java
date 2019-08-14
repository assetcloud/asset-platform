package com.asset.service.test;

import com.asset.FlowableApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class FlowableService {

    @Autowired
    com.asset.service.FlowableService flowableService;



}
