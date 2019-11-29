package com.asset.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.FormInstBO;
import com.asset.javabean.FormInstVO;
import com.asset.javabean.LoginUser;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.utils.Constants;
import org.flowable.form.api.FormModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VOTranslateCommand {

    @Autowired
    ProcInstService procInstService;
    @Autowired
    FormModelService formModelService;

   public ArrayList<FormInstVO> toVO(List<AsRunningTask> asRunningTasks, LoginUser loginUser){
       ArrayList<FormInstVO> formInstVOs = new ArrayList<>();
       for (int i = 0; i < asRunningTasks.size(); i++) {
           AsRunningTask task = asRunningTasks.get(i);
//            String committer = procInstService.getCommitter(filtrate3.get(i).getTaskId());
           String formInstValue = task.getFormValue();
           Date commitTime = procInstService.getCommitTime(task.getProcInstId());   // 这个指的是流程实例发起的时间
           FormInstVO voo = new FormInstVO.Builder()
                   .commitTime(commitTime.getTime())
                   .title(formModelService.getFormName(task.getFormModelId()))   //title应该是对应的表单模型的名称
                   .build();
           BeanUtils.copyProperties(task, voo);
           voo.setFormInstValue(formInstValue);

           //content那一项内容应该是 根据需求显示表单模板中的一些字段信息（这个字段信息的动态设置应该是放在as_form_model表中），如果没有对这个进行设置的话，就显示这个任务的类型
           //现在暂时写死
           String zichanyijiaoFormModelId = "660d7d6f-d83f-11e9-a9e8-0242ac120006";
           if (task.getFormModelId().equals(zichanyijiaoFormModelId)) {
               JSONObject jsonObject = JSON.parseObject(formInstValue);
               String assetName = (String) jsonObject.get("input_1566366027958");
               String assetNum = (String) jsonObject.get("input_1566366061170");
//                String committerForContent = (String) jsonObject.get("input_1568450723142");
               String committerForContent = "金伟刚";
//                String committerSection = (String) jsonObject.get("input_1568450690311");
               String committerSection = (String) jsonObject.get("input_1568450690311");
               String taker = (String) jsonObject.get("input_1566958355545");
               String takerSection = (String) jsonObject.get("input_1566960206187");
               voo.setContent("资产名称：" + assetName + " 数量：" + assetNum + " 申请人：" + committerForContent + " 所在部门：" + committerSection + " 接收人：" + taker + " 所在部门：" + takerSection);
           } else {
               if (task.getNodeType() == Constants.AS_NODE_APPLY)
                   voo.setContent("经办节点");
               else if (task.getNodeType() == Constants.AS_NODE_APPROVE)
                   voo.setContent("审批节点");
               else if (task.getNodeType() == Constants.AS_NODE_CC)
                   voo.setContent("抄送节点");
               else
                   voo.setContent("其他节点");
           }

//            FormInstVO voo = filtrate3.get(i).transToVO(procInstService.getCommitter(formInstDOs.get(i).getTaskId()));
           formInstVOs.add(voo);
       }

       return formInstVOs;
   }

}
