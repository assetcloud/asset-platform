package com.asset.javabean;

import lombok.Data;

import java.util.ArrayList;

/**
 * 用来表示一个实例中的一条执行流
 */
@Data
public class ProcExecution {
    ArrayList<ProcNode> procNodes;   //这条执行流里面的这些节点
    String executionId; //是当前分支下真实的执行序列ID,与当前执行序列第一个元素的executionId相同
    String name;  //是用来区分多条执行序列的

    public ProcExecution() {
    }

    public ProcExecution(String name) {
        procNodes = new ArrayList<>();
        this.name = name;
    }

    public void add(ProcNode e){
        procNodes.add(e);
    }

    /**
     * 传入的executionId是否是在当前执行序列上的
     * @param executionId
     * @return
     */
    public boolean match(String executionId)
    {
        if(executionId==null)
        {
            this.executionId = executionId;
            return true;
        }
        else
        {
            if(!this.executionId.equals(executionId))
                return false;  //说明当前遍历到的元素不在当前的执行序列上
            else
                return true;
        }

    }
}
