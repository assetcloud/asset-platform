package com.asset.javabean;

import lombok.Data;

/**
 * 控制端前台显示的表单流程任务节点的信息
 * @author YBY
 */
@Data
public class AdminTaskVO {
    String taskId;
    String procInstId;
    String executor;
    Integer nodeType;
    //绑定的表单模型的ID，点击之后，会显示当前节点的formSheet以及对应的formValue(当前节点要填写的表单项内容是可以填写的，但是你重新输了也没有用)
    String formModelId;

    String formInstId;      //对应的表单实例ID
    String instName;  //显示在前台用于指示当前任务节点所属的流程是哪个
    long executorTime;

    public AdminTaskVO() {
    }
}
