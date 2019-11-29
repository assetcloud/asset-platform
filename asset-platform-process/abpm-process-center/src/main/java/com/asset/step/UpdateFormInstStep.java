package com.asset.step;

import com.asset.entity.AsFormInstDO;
import com.asset.exception.DatabaseException;
import com.asset.javabean.AsTask;
import com.asset.mapper.AsFormInstMapper;
import com.asset.utils.Constants;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFormInstStep {

    @Autowired
    AsFormInstMapper asFormInstMapper;

    public void updateFormInstStatus(AsTask curTask,int newStatus){
        AsFormInstDO updateDO = new AsFormInstDO.Builder()
                .status(newStatus)
                .build();
        UpdateWrapper<AsFormInstDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(AsFormInstDO::getTaskId, curTask.getTaskId());
        int update = asFormInstMapper.update(updateDO, updateWrapper);
        if (update == Constants.DATABASE_FAILED)
            throw new DatabaseException("更新任务状态值失败！");
    }

}
