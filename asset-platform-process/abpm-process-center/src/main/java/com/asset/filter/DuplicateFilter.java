package com.asset.filter;

import com.asset.entity.AsFormInstDO;
import com.asset.exception.FormException;
import com.asset.javabean.FormInstBO;
import com.asset.service.FlowableService;
import com.asset.service.FormInstService;
import com.asset.service.FormModelService;
import com.asset.service.ProcNodeService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 如果当前表单任务节点包含了会签功能，就需要对当前节点是不是已经被当前用户执行过了进行筛选
 */
@Component
public class DuplicateFilter{

    private static DuplicateFilter duplicateFilter;

    @Autowired
    FormInstService formInstService;
    @Autowired
    FlowableService flowableService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ProcNodeService procNodeService;

    //重点三：初始化
    @PostConstruct
    public void init() {
        duplicateFilter= this;
        duplicateFilter.formInstService= this.formInstService;
        duplicateFilter.flowableService= this.flowableService;
        duplicateFilter.procNodeService= this.procNodeService;
    }


    public ArrayList<FormInstBO> filtrate(List<FormInstBO> formInstBOList) {
        loop1:for(int i=0;i<formInstBOList.size();i++){
            FormInstBO boo = formInstBOList.get(i);
            //先判断当前任务对应的node是不是会签节点,如果是，再去考虑对当前任务进行重复执行筛选
            if(duplicateFilter.procNodeService.ifJointSign(boo.getProcModelId(),boo.getNodeId()))
            {
                //判断当前用户在as_form_inst表中是不是已经有记录（相同proc_inst_id），取出关于该用户的所有记录
                //然后对这些记录进行进一步处理，需要判断这些记录（任务）对应的node_id是不是当前的node_id
                //如果是，才代表当前用户已经执行过当前会签任务了
                List<AsFormInstDO> doList = duplicateFilter.formInstService.getExecutorId(boo.getProcInstId(),boo.getCurUserId());
                for(int k = 0; k<doList.size(); k++)
                {
                    //当前用户已经执行过了当前会签任务
                    String tempNodeId = duplicateFilter.flowableService.getNodeId(doList.get(k).getTaskId());

                    if(tempNodeId.equals(boo.getNodeId()))
                    {
                        formInstBOList.remove(i);
                        i--;
                        continue loop1;
                    }
                }



//                //先去as_form_inst表中看 当前登录用户有没有执行过相同proc_inst_id的任务，有的话，取出这一行的task_id
//                List<String> taskId = duplicateFilter.formInstService.getAlreadyCompleteTask(boo.getCurUserId(),boo.getProcInstId());
//                //如果没有找到，说明没有重复执行的情况
//                if( taskId==null || taskId.size() == 0)
//                    continue;
//
//                for (int m = 0;m<taskId.size();m++){
//                    //如果找到了还需要继续看是不是同一个流程模型中的同一个节点,如果是的话，就说明当前用户已经处理过当前的节点了，不能再执行了
//                    String nodeId = duplicateFilter.flowableService.getNodeId(taskId.get(m));
//                    if (nodeId.equals(boo.getNodeId())){
//                        formInstBOList.remove(i);
//                        i--;
//                    }
//                }

            }

        }
        return (ArrayList<FormInstBO>) formInstBOList;
    }


    public FormInstBO shareLinkFiltrate(FormInstBO formInstBO) throws FormException {
        //点击外链不需要对工作场景进行筛选
//        //对工作场景先做一波筛选，当前取出的实例如果工作场景不匹配，那么就代表不是当前工作场景
//        String instSceneId = formModelService.getSceneIdByProcModelId(formInstBO.getProcModelId());
//        if (!instSceneId.equals(formInstBO.getSceneId()))
//            throw new FormException("当前用户权限或者工作场景权限不够，无法获取当前任务节点信息！");

        //先去as_form_inst表中看 当前登录用户有没有执行过相同proc_inst_id的任务，有的话，取出这一行的task_id
        List<String> taskId = duplicateFilter.formInstService.getAlreadyCompleteTask(formInstBO.getCurUserId(),
                formInstBO.getProcInstId());

        //如果没有找到，说明没有重复执行的情况
        if( taskId==null || taskId.size() == 0)
            return formInstBO;

        for (int m = 0;m<taskId.size();m++){
            //如果找到了还需要继续看是不是同一个流程模型中的同一个节点,如果是的话，就说明当前用户已经处理过当前的节点了，不能再执行了
            String nodeId = duplicateFilter.flowableService.getNodeId(taskId.get(m));
            if (nodeId.equals(formInstBO.getNodeId()))
                throw new FormException("当前用户权限或者工作场景权限不够，无法获取当前任务节点信息！");
        }

        return formInstBO;
    }
}
