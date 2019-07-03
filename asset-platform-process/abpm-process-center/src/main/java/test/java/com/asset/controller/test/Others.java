package com.asset.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.asset.FlowableApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlowableApplication.class)
public class Others {

    @Test
    public void testJson(){
        User u1 = new User("Wang");
        User u2 = new User("Li");
        User u3 = new User("Ye");
        ArrayList<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        HashMap<String, Serializable> map = new HashMap<String, Serializable>();
        map.put("list",list);

        Object json = JSONObject.toJSON(map);
        System.out.println(json.toString());
    }

    @Test
    public void testXiegang(){
        String a = "{\"list\":[{\"type\":\"input\",\"name\":\"单行文本\",\"icon\":\"icon-input\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"dataType\":\"string\",\"pattern\":\"\",？？？\"placeholder\":\"\",提示\"remoteFunc\":\"func_1559093913000_64187\"},\"key\":\"1559093913000_64187\",\"model\":\"input_1559093913000_64187\",\"rules\":[{\"type\":\"string\",\"message\":\"单行文本格式不正确\"}]},{\"type\":\"textarea\",\"name\":\"多行文本\",\"icon\":\"icon-diy-com-textarea\",\"options\":{\"width\":\"100%\",\"defaultValue\":\"\",\"required\":false,\"disabled\":false,\"pattern\":\"\",\"placeholder\":\"\",\"remoteFunc\":\"func_1559093915000_54488\"},\"key\":\"1559093915000_54488\",\"model\":\"textarea_1559093915000_54488\",\"rules\":[]},{\"type\":\"number\",\"name\":\"计数器\",\"icon\":\"icon-number\",\"options\":{\"width\":\"\",\"required\":false,\"defaultValue\":0,\"min\":0,\"max\":0,\"step\":1,\"disabled\":false,\"controlsPosition\":\"\",\"remoteFunc\":\"func_1559093917000_59875\"},\"key\":\"1559093917000_59875\",\"model\":\"number_1559093917000_59875\",\"rules\":[]},{\"type\":\"radio\",\"name\":\"单选框组\",\"icon\":\"icon-radio-active\",\"options\":{\"inline\":false,\"defaultValue\":\"\",\"showLabel\":false,\"options\":[{\"value\":\"选项1\",\"label\":\"选项1\"},{\"value\":\"选项2\",\"label\":\"选项2\"},{\"value\":\"选项3\",\"label\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093919000_99333\"},\"key\":\"1559093919000_99333\",\"model\":\"radio_1559093919000_99333\",\"rules\":[]},{\"type\":\"checkbox\",\"name\":\"多选框组\",\"icon\":\"icon-check-box\",\"options\":{\"inline\":false,\"defaultValue\":[],\"showLabel\":false,\"options\":[{\"value\":\"选项1\"},{\"value\":\"选项2\"},{\"value\":\"选项3\"}],\"required\":false,\"width\":\"\",\"remote\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093939000_1748\"},\"key\":\"1559093939000_1748\",\"model\":\"checkbox_1559093939000_1748\",\"rules\":[]},{\"type\":\"time\",\"name\":\"时间选择器\",\"icon\":\"icon-time\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"isRange\":false,\"arrowControl\":true,\"format\":\"HH:mm:ss\",\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093942000_49623\"},\"key\":\"1559093942000_49623\",\"model\":\"time_1559093942000_49623\",\"rules\":[]},{\"type\":\"date\",\"name\":\"日期选择器\",\"icon\":\"icon-date\",\"options\":{\"defaultValue\":\"\",\"readonly\":false,\"disabled\":false,\"editable\":true,\"clearable\":true,\"placeholder\":\"\",\"startPlaceholder\":\"\",\"endPlaceholder\":\"\",\"type\":\"date\",\"format\":\"yyyy-MM-dd\",\"timestamp\":false,\"required\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093945000_4839\"},\"key\":\"1559093945000_4839\",\"model\":\"date_1559093945000_4839\",\"rules\":[]},{\"type\":\"select\",\"name\":\"下拉选择框\",\"icon\":\"icon-select\",\"options\":{\"defaultValue\":\"\",\"multiple\":false,\"disabled\":false,\"clearable\":false,\"placeholder\":\"\",\"required\":false,\"showLabel\":false,\"width\":\"\",\"options\":[{\"value\":\"下拉框1\"},{\"value\":\"下拉框2\"},{\"value\":\"下拉框3\"}],\"remote\":false,\"filterable\":false,\"remoteOptions\":[],\"props\":{\"value\":\"value\",\"label\":\"label\"},\"remoteFunc\":\"func_1559093951000_24131\"},\"key\":\"1559093951000_24131\",\"model\":\"select_1559093951000_24131\",\"rules\":[]},{\"type\":\"rate\",\"name\":\"评分\",\"icon\":\"icon-icon-test\",\"options\":{\"defaultValue\":0,\"max\":5,\"disabled\":false,\"allowHalf\":false,\"required\":false,\"remoteFunc\":\"func_1559093947000_88214\"},\"key\":\"1559093947000_88214\",\"model\":\"rate_1559093947000_88214\",\"rules\":[]},{\"type\":\"switch\",\"name\":\"开关\",\"icon\":\"icon-switch\",\"options\":{\"defaultValue\":false,\"required\":false,\"disabled\":false,\"remoteFunc\":\"func_1559093953000_73568\"},\"key\":\"1559093953000_73568\",\"model\":\"switch_1559093953000_73568\",\"rules\":[]},{\"type\":\"slider\",\"name\":\"滑块\",\"icon\":\"icon-slider\",\"options\":{\"defaultValue\":0,\"disabled\":false,\"required\":false,\"min\":0,\"max\":100,\"step\":1,\"showInput\":false,\"range\":false,\"width\":\"\",\"remoteFunc\":\"func_1559093955000_23561\"},\"key\":\"1559093955000_23561\",\"model\":\"slider_1559093955000_23561\",\"rules\":[]},{\"type\":\"imgupload\",\"name\":\"图片\",\"icon\":\"icon-tupian\",\"options\":{\"defaultValue\":[],\"size\":{\"width\":100,\"height\":100},\"width\":\"\",\"tokenFunc\":\"funcGetToken\",\"token\":\"\",\"domain\":\"http://pfp81ptt6.bkt.clouddn.com/\",\"disabled\":false,\"length\":8,\"multiple\":false,\"isQiniu\":false,\"isDelete\":false,\"min\":0,\"isEdit\":false,\"action\":\"https://jsonplaceholder.typicode.com/photos/\",\"remoteFunc\":\"func_1559093958000_41914\"},\"key\":\"1559093958000_41914\",\"model\":\"imgupload_1559093958000_41914\",\"rules\":[]}],\"config\":{\"labelWidth\":100,\"labelPosition\":\"top\",\"size\":\"small\"}}";
        System.out.println(a);
    }




}
