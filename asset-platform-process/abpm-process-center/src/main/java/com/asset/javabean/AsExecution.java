package com.asset.javabean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

@Data
public class AsExecution {
    ArrayList<AsTask> execution;
    String exeId;  //注意这个代表真实的executionId，每出现一个新的分支，那么就会有一个新的executionId，然后被赋值在这里，但是，主execution会被一直继承下来！
    Boolean rollbackEnable;  //该执行序列是否可以回滚

    public AsExecution() {
        execution = new ArrayList<>();
        rollbackEnable = false;
    }

    //创建一个新的AsExecution
    public AsExecution(AsExecution oldExe) {
        this.execution = (ArrayList<AsTask>) oldExe.getExecution().clone();
        rollbackEnable = false;
    }

    //这里往列表中新加Task的时候，需要判断当前Task的executionId是不是和当前AsExecution的exeId是不是相同的
    public void add(AsTask val)
    {
        if(val.getExecutionId().equals(exeId))
            if(!containTask(val.getTaskId()))
                execution.add(val);
    }

    /**
     * 当前执行序列是否包含该TaskId
     * @param taskId
     * @return
     */
    public boolean containTask(String taskId)
    {
        for(int i = 0; i< execution.size(); i++)
        {
            if(execution.get(i).getTaskId().equals(taskId))
                return true;
        }
        return false;
    }

    public void initExeId(String executionId)
    {
        if(StringUtils.isEmpty(this.exeId))
            exeId = executionId;
    }

    /**
     * @param exeId 这个参数是可回滚的AsTaskStack的exeId属性，如果与当前匹配代表就可以回滚
     */
    public void initRollbackEnable(String exeId)
    {
        if(this.exeId.equals(exeId))
            this.rollbackEnable = true;
    }


    /**
     * 判断当前执行序列是不是逆序排列的，如果不是，需要先进行逆序排列
     */
    public void checkDesc() {
        //如果第一个元素是开始元素，说明是正序排列，需要进行翻转
        if(execution.get(0).isStartEvent())
            Collections.reverse(execution);
    }
}
