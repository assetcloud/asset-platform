package com.asset.javabean;

import lombok.Data;

import java.util.ArrayList;

@Data
public class AsExecution {
    ArrayList<AsTask> executions;
    String exeId;  //注意这个不是代表的真的executionId，只是一个标示

    public AsExecution(String exeId) {
        executions = new ArrayList<>();
        this.exeId = exeId;
    }

    //创建一个新的AsExecution
    public AsExecution(AsExecution oldExe) {
        this.executions = (ArrayList<AsTask>) oldExe.getExecutions().clone();
        this.exeId = oldExe.getExeId();
    }

    public void add(AsTask val)
    {
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
            if(executions.get(i).equals(taskId))
                return true;
        }
        return false;
    }


}
