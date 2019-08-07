package com.asset.utils;

import com.asset.exception.FormException;
import com.asset.javabean.FormInstBO;
import com.asset.service.FlowableService;
import com.asset.service.FormInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 如果当前表单任务节点包含了会签功能，就需要对当前节点是不是已经被当前用户执行过了进行筛选
 */
@Component
public class DuplicateFilter implements Filter{

    private static DuplicateFilter duplicateFilter;

    @Autowired
    FormInstService formInstService;
    @Autowired
    FlowableService flowableService;

    //重点三：初始化
    @PostConstruct
    public void init() {
        duplicateFilter= this;
        duplicateFilter.formInstService= this.formInstService;
        duplicateFilter.flowableService= this.flowableService;
    }


    @Override
    public ArrayList<FormInstBO> filtrate(List<FormInstBO> formInstBOList) {
        for(int i=0;i<formInstBOList.size();i++){
            FormInstBO boo = formInstBOList.get(i);
            //先去as_form_inst表中看 当前登录用户有没有执行过相同proc_inst_id的任务，有的话，取出这一行的task_id
            String taskId = duplicateFilter.formInstService.getAlreadyCompleteTask(boo.getCurUserId(),boo.getProcInstId());
            //如果没有找到，说明没有重复执行的情况
            if(taskId==null)
                continue;

            //如果找到了还需要继续看是不是同一个流程模型中的同一个节点,如果是的话，就说明当前用户已经处理过当前的节点了，不能再执行了
            String nodeId = duplicateFilter.flowableService.getNodeId(taskId);
            if (nodeId.equals(boo.getNodeId())){
                formInstBOList.remove(i);
                i--;
            }
        }
        return (ArrayList<FormInstBO>) formInstBOList;
    }

    @Override
    public FormInstBO filtrate(FormInstBO formInstBO) throws FormException {
        //先去as_form_inst表中看 当前登录用户有没有执行过相同proc_inst_id的任务，有的话，取出这一行的task_id
        String taskId = duplicateFilter.formInstService.getAlreadyCompleteTask(formInstBO.getCurUserId(),
                formInstBO.getProcInstId());

        //如果没有找到，说明没有重复执行的情况
        if(taskId==null)
            return formInstBO;

        //如果找到了还需要继续看是不是同一个流程模型中的同一个节点,如果是的话，就说明当前用户已经处理过当前的节点了，不能再执行了
        String nodeId = duplicateFilter.flowableService.getNodeId(taskId);
        if (nodeId.equals(formInstBO.getNodeId()))
            throw new FormException("当前用户权限不够，无法获取当前任务节点信息！");

        return formInstBO;
    }
}
