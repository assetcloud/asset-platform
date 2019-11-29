package com.asset.command;

import com.asset.entity.ActRuExecution;
import com.asset.entity.ActRuTask;
import com.asset.mapper.ActRuExecutionMapper;
import com.asset.mapper.ActRuTaskMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteProcCommand {
    @Autowired
    ActRuExecutionMapper executionMapper;
    @Autowired
    ActRuTaskMapper taskMapper;

    /**
     * 因为找不到flowable停止单条execution的API接口，所以这里采用直接删除数据库中相应字段的方法删除某个execution
     * 先去act_ru_execution表中找到对应该execution的taskId，然后去删除act_ru_task表中的记录，接着去act_ru_execution表删除该记录
     * @param exeId
     */
    public void deleteExecution(String exeId) {
        //删act_ru_task表中该execution下的所有待完成任务
        QueryWrapper<ActRuTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ActRuTask::getExecutionId, exeId);
        int flag = taskMapper.delete(queryWrapper);

        //删act_ru_execution表中该execution
        QueryWrapper<ActRuExecution> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda()
                .eq(ActRuExecution::getId, exeId);
        int flag2 = executionMapper.delete(queryWrapper2);
    }
}
