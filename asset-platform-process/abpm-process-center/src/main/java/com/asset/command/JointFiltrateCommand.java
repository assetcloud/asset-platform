package com.asset.command;

import com.asset.entity.AsTaskDO;
import com.asset.javabean.AsRunningTask;
import com.asset.javabean.LoginUser;
import com.asset.service.FlowableService;
import com.asset.service.FormInstService;
import com.asset.service.FormModelService;
import com.asset.service.ProcNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JointFiltrateCommand {

    @Autowired
    FormInstService formInstService;
    @Autowired
    FlowableService flowableService;
    @Autowired
    FormModelService formModelService;
    @Autowired
    ProcNodeService procNodeService;

    public void filtrate(List<AsRunningTask> runningTasks, LoginUser loginUser) {
        loop1:for(int i=0;i<runningTasks.size();i++){
            AsRunningTask task = runningTasks.get(i);
            //先判断当前任务对应的node是不是会签节点,如果是，再去考虑对当前任务进行重复执行筛选
            if(procNodeService.ifJointSign(task.getProcModelId(),task.getNodeId()))
            {
                //判断当前用户在as_form_inst表中是不是已经有记录（相同proc_inst_id），取出关于该用户的所有记录
                //然后对这些记录进行进一步处理，需要判断这些记录（任务）对应的node_id是不是当前的node_id
                //如果是，才代表当前用户已经执行过当前会签任务了
                List<AsTaskDO> doList = formInstService.getExecutorId(task.getProcInstId(),loginUser.getUserId());
                for(int k = 0; k<doList.size(); k++)
                {
                    //当前用户已经执行过了当前会签任务
                    String tempNodeId = flowableService.getNodeId(doList.get(k).getId());

                    if(tempNodeId.equals(task.getNodeId()))
                    {
                        runningTasks.remove(i);
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
    }


}
