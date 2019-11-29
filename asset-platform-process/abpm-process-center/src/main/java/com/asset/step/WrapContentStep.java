package com.asset.step;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsTaskDO;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.AsTaskVO;
import com.asset.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class WrapContentStep {
    public void wrapContent(AsTaskDO curTask, AsTaskVO taskVO, String formValue){
        //content那一项内容应该是 根据需求显示表单模板中的一些字段信息（这个字段信息的动态设置应该是放在as_form_model表中），如果没有对这个进行设置的话，就显示这个任务的类型
        //现在暂时写死
        String zichanyijiaoFormModelId = "660d7d6f-d83f-11e9-a9e8-0242ac120006";
        if (curTask.getFormModelId().equals(zichanyijiaoFormModelId)) {
            JSONObject jsonObject = JSON.parseObject(formValue);
            String assetName = (String) jsonObject.get("input_1566366027958");
            String assetNum = (String) jsonObject.get("input_1566366061170");
//                String committerForContent = (String) jsonObject.get("input_1568450723142");
            String committerForContent = "金伟刚";
//                String committerSection = (String) jsonObject.get("input_1568450690311");
            String committerSection = (String) jsonObject.get("input_1568450690311");
            String taker = (String) jsonObject.get("input_1566958355545");
            String takerSection = (String) jsonObject.get("input_1566960206187");
            taskVO.setContent("资产名称：" + assetName + " 数量：" + assetNum + " 申请人：" + committerForContent + " 所在部门：" + committerSection + " 接收人：" + taker + " 所在部门：" + takerSection);
        } else {
            if (curTask.getNodeType() == Constants.AS_NODE_APPLY)
                taskVO.setContent("经办节点");
            else if (curTask.getNodeType() == Constants.AS_NODE_APPROVE)
                taskVO.setContent("审批节点");
            else if (curTask.getNodeType() == Constants.AS_NODE_CC)
                taskVO.setContent("抄送节点");
            else
                taskVO.setContent("其他节点");
        }
    }
}
