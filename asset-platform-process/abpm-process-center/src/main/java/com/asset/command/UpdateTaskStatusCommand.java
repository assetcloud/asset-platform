package com.asset.command;

import com.asset.entity.AsTaskDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.AsExecution;
import com.asset.javabean.AsSimpleTask;
import com.asset.javabean.AsTask;
import com.asset.mapper.AsTaskMapper;
import com.asset.service.ProcInstService;
import com.asset.service.ProcNodeService;
import com.asset.step.TranslateStep;
import com.asset.step.UpdateFormInstStep;
import com.asset.utils.Constants;
import com.asset.utils.ProcUtils;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UpdateTaskStatusCommand {
    @Autowired
    AsTaskMapper asTaskMapper;
    @Autowired
    TranslateStep translateStep;
    @Autowired
    ProcInstService procInstService;
    @Autowired
    ProcNodeService procNodeService;
    @Autowired
    UpdateFormInstStep updateFormInstStep;


    /**
     * 更新那些被回滚的任务节点的状态码
     * 适用于不包含并行分支的情况
     * @param simpleTask
     * @param rollbackActId
     * @return
     */
    public void updateRollbackTaskStatus(AsSimpleTask simpleTask,String rollbackActId){
        //回滚之后，需获取当前任务到上一个经办节点之间的那些任务实例formInst，将其状态值修改为“已回滚”
        String procInstId = simpleTask.getProcInstId();

        List<HistoricActivityInstance> historicActs = ProcUtils.getHistoricActsDesc(procInstId);
        for (int i = 0; i < historicActs.size(); i++) {
            //从后往前遍历，只到遍历到回滚点处，就不用更新状态值了
            if (historicActs.get(i).getActivityId().equals(rollbackActId))
                break;
            //只对类型为userTask的进行更新状态值，因为as_form_inst表中只存在类型为userTask的任务
            if (!historicActs.get(i).getActivityType().equals("userTask"))
                continue;


            AsTaskDO updateDO = AsTaskDO.builder()
                    .status(Constants.FORM_INST_ROLLED)
                    .build();

            UpdateWrapper<AsTaskDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .eq(AsTaskDO::getId, historicActs.get(i).getTaskId());

            int update = asTaskMapper.update(updateDO, updateWrapper);
            if (update == Constants.DATABASE_FAILED)
                throw new DatabaseException("更新任务状态值失败！");
        }

    }


    /**
     * 更新那些被回滚的任务节点的状态码
     * 适用于包含并行分支的情况,包含并行分支的情况 需要对“可回滚列表”中所有的execution更新状态值
     * @param rollbackActId
     * @return
     */
    public void updateRollbackTaskStatus(String rollbackActId, HashMap<String, AsExecution> executions){
        //回滚之后，需获取当前任务到上一个经办节点之间的那些任务实例formInst，将其状态值修改为“已回滚”
        //这里不能对之前执行过的节点进行更新，而是只针对这个execution上的进行状态更新，因为有可能会波及到“不可回滚列表“
        for(AsExecution curExecution:executions.values())
        {
            //只对“可回滚列表”中所有的execution上的task进行状态更新
            if(curExecution.getRollbackEnable())
            {
                //判断当前execution是不是逆序排列的，如果不是的话，需要先变为逆序排列
                curExecution.checkDesc();
                ArrayList<AsTask> execution = curExecution.getExecution();

                for (int i = 0; i < execution.size(); i++) {
                    AsTask curTask = execution.get(i);
                    //从后往前遍历，只到遍历到回滚点处，当前回滚点还要把状态值更新了，然后回滚点前面的就不用更新状态值了
                    if (curTask.getActId().equals(rollbackActId))
                    {
                        updateFormInstStep.updateFormInstStatus(curTask, Constants.FORM_INST_ROLLED);
                        break;
                    }
                    //只对类型为userTask的进行更新状态值，因为as_form_inst表中只存在类型为userTask的任务
                    if (!curTask.isUserTask())
                        continue;

                    updateFormInstStep.updateFormInstStatus(curTask, Constants.FORM_INST_ROLLED);


                }
            }

        }
    }





}
