package com.asset.javabean;

import com.asset.entity.FormInstDO;
import com.asset.service.FormInstService;
import com.asset.service.ProcInstService;
import com.asset.utils.Constants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class FormInstVO extends FormInstDO {

    @Autowired
    ProcInstService procInstService;

    //返回给前台用来展示的表单标题
    String title;
    //用来展示的表单内容
    String content;

    public FormInstVO(FormInstDO doo,String committer) {
        this.formInstValue = doo.getFormInstValue();
        this.formModelId = doo.getFormModelId();
        this.procInstId = doo.getProcInstId();
        this.executionId = doo.getExecutionId();
        this.taskId = doo.getTaskId();
        this.executor = doo.getExecutor();
        this.formInstSheetStr = doo.getFormInstSheetStr();
        this.executeTime = doo.getExecuteTime();
        this.id = doo.getId();
        this.nodeType = doo.getNodeType();
        this.status = doo.getStatus();

        this.title = committer +"发起的表单";
        if(doo.getNodeType() == Constants.AS_NODE_APPLY)
            this.content = "经办节点";
        else if(doo.getNodeType() == Constants.AS_NODE_APPROVE)
            this.content = "审批节点";
        else if(doo.getNodeType() == Constants.AS_NODE_CC)
            this.content = "抄送节点";
        else
            this.content = "其他节点";
    }

    public FormInstVO() {
    }
}
