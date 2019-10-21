package com.asset.service.test;

import com.asset.FlowableApplication;
import com.asset.entity.FormInstDO;
import com.asset.exception.FormException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FlowableApplication.class)
public class AuthorityService {

    @Autowired
    com.asset.service.AuthorityService authorityService;

    @Test
    public void handleFormSheetAuthority() throws Exception {
        FormInstDO formInstDO = new FormInstDO();
        formInstDO.setProcInstId("4ab5e4f0-acea-11e9-a0bb-2e15a8856301");
        formInstDO.setFormInstSheetStr("{\"config\":{\"labelPosition\":\"top\",\"labelWidth\":100,\"size\":\"small\"},\"list\":[{\"icon\":\"icon-radio-active\",\"key\":\"1561686342000_47132\",\"model\":\"radio_1561686342000_47132\",\"name\":\"单选框组\",\"options\":{\"defaultValue\":\"\",\"inline\":false,\"options\":[{\"value\":\"选项1\",\"label\":\"选项1\"},{\"value\":\"选项2\",\"label\":\"选项2\"},{\"value\":\"选项3\",\"label\":\"选项3\"}],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remote\":false,\"remoteFunc\":\"func_1561686342000_47132\",\"remoteOptions\":[],\"required\":false,\"showLabel\":false,\"width\":\"\"},\"rules\":[],\"type\":\"radio\"}]}");
        FormInstDO formInstDO1 = authorityService.handleFormSheetAuthority(formInstDO);

    }
}
