package com.asset.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.asset.entity.AsTaskDO;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.AsTaskVO;
import com.asset.javabean.LoginUser;
import com.asset.service.FormModelService;
import com.asset.service.ProcInstService;
import com.asset.step.WrapContentStep;
import com.asset.utils.Constants;
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
    @Autowired
    WrapContentStep wrapContentStep;

   public ArrayList<AsTaskVO> toVO(List<AsTaskDO> asRunningTasks, LoginUser loginUser){
       ArrayList<AsTaskVO> asTaskVOS = new ArrayList<>();

       for (int i = 0; i < asRunningTasks.size(); i++) {
           AsTaskDO curTask = asRunningTasks.get(i);

           Date commitTime = procInstService.getCommitTime(curTask.getProcInstId());   // 这个指的是流程实例发起的时间
           String formName = formModelService.getFormName(curTask.getFormModelId());
           String formValue = curTask.getFormInstValue();

           AsTaskVO taskVO = new AsTaskVO.Builder()
                   .commitTime(commitTime.getTime())
                   .title(formName)   //title应该是对应的表单模型的名称
                   .id(curTask.getId())
                   .build();
           BeanUtils.copyProperties(curTask, taskVO);

           //对content属性进行处理
           wrapContentStep.wrapContent(curTask,taskVO,formValue);

           asTaskVOS.add(taskVO);
       }

       return asTaskVOS;
   }

}
