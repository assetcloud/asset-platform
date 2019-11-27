package com.asset.javabean;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

@Data
public class AsExecution {
    ArrayList<AsTask> executions;
    String exeId;  //注意这个代表真实的executionId，每出现一个新的分支，那么就会有一个新的executionId，然后被赋值在这里，但是，主execution会被一直继承下来！

    public AsExecution() {
        executions = new ArrayList<>();
    }

    //创建一个新的AsExecution
    public AsExecution(AsExecution oldExe) {
        this.executions = (ArrayList<AsTask>) oldExe.getExecutions().clone();
    }

    //这里往列表中新加Task的时候，需要判断当前Task的executionId是不是和当前AsExecution的exeId是不是相同的
    public void add(AsTask val)
    {
        if(val.getExecutionId().equals(exeId))
            if(!containTask(val.getTaskId()))
                executions.add(val);
    }

    /**
     * 当前执行序列是否包含该TaskId
     * @param taskId
     * @return
     */
    public boolean containTask(String taskId)
    {
        for(int i=0;i<executions.size();i++)
        {
            if(executions.get(i).getTaskId().equals(taskId))
                return true;
        }
        return false;
    }

    public void initExeId(String executionId)
    {
        if(StringUtils.isEmpty(this.exeId))
            exeId = executionId;
    }


}
