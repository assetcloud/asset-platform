package com.asset.step;

import com.asset.entity.AsTaskDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.AsTask;
import com.asset.mapper.AsTaskMapper;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFormInstStep {

    @Autowired
    AsTaskMapper asTaskMapper;

    public void updateFormInstStatus(AsTask curTask,int newStatus){
        AsTaskDO updateDO = AsTaskDO.builder()
                .status(newStatus)
                .build();
        UpdateWrapper<AsTaskDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(AsTaskDO::getId, curTask.getTaskId());
        int update = asTaskMapper.update(updateDO, updateWrapper);
        if (update == Constants.DATABASE_FAILED)
            throw new DatabaseException("更新任务状态值失败！");
    }

}
