package com.asset.service.test;

import com.asset.FlowableApplication;
import org.dom4j.DocumentException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class ProcInstService {
    @Autowired
    com.asset.service.ProcInstService procInstService;

    /**
     * 具体的逻辑 是取出这一项，转换成实体类，与提交的form_inst_value（也被转换为实体类）作对比，把没有的内容加到实体类上，最后转成str，更新as_proc_inst中的form_inst_all_value值
     */
    @Test
    public void updateFormValueForAll() {
        String formInstValueStr = "{\"TEST12121212\":[]}";
        String procInstId = "4ab5e4f0-acea-11e9-a0bb-2e15a8856301";
        procInstService.updateFormValueForAll(formInstValueStr,procInstId);
    }

    @Test
    public void createProcInstance()
    {
        String procModelId = "7c1a1c00-b032-11e9-b1e4-2e15a8856301";
        try {
            procInstService.createProcInstance(procModelId);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
